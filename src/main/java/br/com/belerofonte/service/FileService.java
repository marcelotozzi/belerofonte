package br.com.belerofonte.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;

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

	public FileService(ApplicationFileDAO applicationFileDAO,
			PropertiesLoader loader) {
		this.applicationFileDAO = applicationFileDAO;
		this.loader = loader;
	}

	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {			
		File filedestiny = new File(loader.getValue("folderFiles"), uploadedFile.getFileName());
		
		try {
			IOUtils.copyLarge(uploadedFile.getFile(), new FileOutputStream(filedestiny));	
			
			this.applicationFileDAO.save(fillAtributes(uploadedFile, applicationFile, filedestiny));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ApplicationFile fillAtributes(UploadedFile uploadedFile, ApplicationFile applicationFile, File filedestiny) {
		applicationFile.setUploadDate(Calendar.getInstance());
		applicationFile.setNameOfFile(uploadedFile.getFileName());
		applicationFile.setContentType(uploadedFile.getContentType());
		applicationFile.setSizeOfFile(filedestiny.length());
		applicationFile.setNumberOfDownloads(0L);
		return applicationFile;
	}

	public Download searchAndDownloadFile(Long id) {
		ApplicationFile appFile = this.applicationFileDAO.load(id);
		
		File file = new File(loader.getValue("folderFiles") + appFile.getNameOfFile());
		
		Download download = new FileDownload(file, appFile.getContentType(), appFile.getNameOfFile());
		
		this.increasingNumberOfDownloads(appFile);
		return download;
	}

	private void increasingNumberOfDownloads(ApplicationFile appFile) {
		appFile.setNumberOfDownloads(appFile.getNumberOfDownloads()+1);
		this.applicationFileDAO.update(appFile);
	}
}