package br.com.belerofonte.service;

import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ApplicationFileService {

	private ApplicationFileDAO applicationFileDAO;

	public ApplicationFileService(ApplicationFileDAO applicationFileDAO) {
		this.applicationFileDAO = applicationFileDAO;
	}
	
	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {
		this.applicationFileDAO.save(applicationFile);
	}
}