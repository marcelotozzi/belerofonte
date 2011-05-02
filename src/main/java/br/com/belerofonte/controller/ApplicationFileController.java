package br.com.belerofonte.controller;

import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class ApplicationFileController {

	private ApplicationFileDAO applicationFileDAO;

	public ApplicationFileController(ApplicationFileDAO applicationFileDAO) {
		this.applicationFileDAO = applicationFileDAO;
	}

	public void upload(UploadedFile uploadedFile, ApplicationFile applicationFile) {
		this.applicationFileDAO.save(applicationFile);
	}

	public void deleteFile(long id) {
		this.applicationFileDAO.delete(this.applicationFileDAO.load(id));
	}
}