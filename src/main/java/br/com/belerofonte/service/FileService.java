package br.com.belerofonte.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.infra.Downloader;
import br.com.belerofonte.infra.Uploader;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class FileService {

	private final ApplicationFileDAO applicationFileDAO;
	private final Account account;
	private final Uploader uploader;
	private final Downloader downloader;
	private static final Logger log = Logger.getLogger(FileService.class);

	public FileService(ApplicationFileDAO applicationFileDAO, Account account, Uploader uploader, 
			Downloader downloader) {
		this.applicationFileDAO = applicationFileDAO;
		this.account = account;
		this.uploader = uploader;
		this.downloader = downloader;
	}

	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {			
		try {		
			File fileDestiny = this.uploader.forUser(this.account.getUser()).copyFile(uploadedFile);
			
			ApplicationFile user = fillAtributes(uploadedFile, applicationFile, fileDestiny);
			
			this.applicationFileDAO.save(user);		
		} catch (FileNotFoundException e) {
			// TODO inserir log // lançar exception para controller
			log.error("Arquivo nao encontrado", e);
		} catch (IOException e) {
			// TODO inserir log // lançar exception para controller
			log.error("Problemas com IO", e);
		}
	}

	private ApplicationFile fillAtributes(UploadedFile uploadedFile, ApplicationFile applicationFile, File filedestiny) {
		applicationFile.setUploadDate(Calendar.getInstance());
		applicationFile.setNameOfFile(uploadedFile.getFileName());
		applicationFile.setContentType(uploadedFile.getContentType());
		applicationFile.setSizeOfFile(filedestiny.length());
		applicationFile.setNumberOfDownloads(0L);
		applicationFile.setUser(this.account.getUser());
		return applicationFile;
	}

	public Download searchAndDownloadFile(Long id) {			
		ApplicationFile appFile = this.applicationFileDAO.load(id);
		
		Download download = this.downloader.getFileTheUser(appFile); 
		
		this.increasingNumberOfDownloads(appFile);
		
		return download;
	}

	private void increasingNumberOfDownloads(ApplicationFile appFile) {
		appFile.setNumberOfDownloads(appFile.getNumberOfDownloads()+1);
		this.applicationFileDAO.update(appFile);
	}
}