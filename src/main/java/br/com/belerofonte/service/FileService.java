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
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class FileService {

	private ApplicationFileDAO applicationFileDAO;
	private PropertiesLoader loader;

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
}