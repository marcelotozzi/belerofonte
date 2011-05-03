package br.com.belerofonte.controller;

import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.ApplicationFileService;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class ApplicationFileController {

	private ApplicationFileDAO applicationFileDAO;
	private ApplicationFileService applicationFileService;

	public ApplicationFileController(ApplicationFileDAO applicationFileDAO, ApplicationFileService applicationFileService) {
		this.applicationFileDAO = applicationFileDAO;
		this.applicationFileService  = applicationFileService;
	}

	public void create(UploadedFile uploadedFile, ApplicationFile applicationFile) {
		this.applicationFileService.create(uploadedFile,applicationFile);
	}

	public void delete(long id) {
		this.applicationFileDAO.delete(this.applicationFileDAO.load(id));
	}

	public void update(ApplicationFile applicationFile) {
		this.applicationFileDAO.update(applicationFile);
	}
}