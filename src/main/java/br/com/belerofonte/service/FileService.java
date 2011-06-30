package br.com.belerofonte.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.belerofonte.components.Account;
import br.com.belerofonte.components.PropertiesLoader;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class FileService {

	private final ApplicationFileDAO applicationFileDAO;
	private final PropertiesLoader loader;
	private final Account account;
	private static final Logger log = Logger.getLogger(FileService.class);

	public FileService(ApplicationFileDAO applicationFileDAO,
			PropertiesLoader loader, Account account) {
		this.applicationFileDAO = applicationFileDAO;
		this.loader = loader;
		this.account = account;
	}

	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {			
		File filedestiny = checksTheUsersDirectory(uploadedFile);
		//TODO refatorar geração de arquivo para um objeto
		try {
			IOUtils.copyLarge(uploadedFile.getFile(), new FileOutputStream(filedestiny));	
			
			this.applicationFileDAO.save(fillAtributes(uploadedFile, applicationFile, filedestiny));		
		} catch (FileNotFoundException e) {
			// TODO inserir log // lançar exception para controller
			//throw new IllegalStateException("Arquivo nao encontrado", e);
			log.error("Arquivo nao encontrado", e);
		} catch (IOException e) {
			// TODO inserir log // lançar exception para controller
			//throw new IllegalStateException("Arquivo nao encontrado", e);
			log.error("Problemas com IO", e);
		}
	}

	private File checksTheUsersDirectory(UploadedFile uploadedFile) {
		String dir = loader.getValue("folderFiles")
				+ this.account.getUser().getUsername()
				+ loader.getValue("appFolder");
		if(!new File(dir).exists()){
			new File(dir).mkdir();
		}
		File filedestiny = new File(dir, uploadedFile.getFileName());
		return filedestiny;
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
		
		File file = new File(loader.getValue("folderFiles") 
				+ appFile.getUser().getUsername() 
				+ loader.getValue("appFolder") 
				+ appFile.getNameOfFile());
		
		Download download = new FileDownload(file, appFile.getContentType(), appFile.getNameOfFile());
		
		this.increasingNumberOfDownloads(appFile);
		return download;
	}

	private void increasingNumberOfDownloads(ApplicationFile appFile) {
		appFile.setNumberOfDownloads(appFile.getNumberOfDownloads()+1);
		this.applicationFileDAO.update(appFile);
	}
}