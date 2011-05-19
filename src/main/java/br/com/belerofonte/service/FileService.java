package br.com.belerofonte.service;

import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class FileService {

	private ApplicationFileDAO applicationFileDAO;

	public FileService(ApplicationFileDAO applicationFileDAO) {
		this.applicationFileDAO = applicationFileDAO;
	}
	
	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {
		
		applicationFile.setNameOfFile(uploadedFile.getFileName());
		applicationFile.setContentType(uploadedFile.getContentType());
		
		this.applicationFileDAO.save(applicationFile);
	}
}