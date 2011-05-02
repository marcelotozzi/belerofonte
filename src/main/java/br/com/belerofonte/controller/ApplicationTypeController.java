package br.com.belerofonte.controller;

import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.model.ApplicationType;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ApplicationTypeController {

	private ApplicationTypeDAO applicationTypeDAO;
	private Result result;

	public ApplicationTypeController(ApplicationTypeDAO applicationTypeDAO, Result result) {
		this.applicationTypeDAO = applicationTypeDAO;
		this.result = result;
	}

	@Post
	@Path("/admin/applicationType")
	public void create(ApplicationType applicationType) {
		this.applicationTypeDAO.save(applicationType);
		this.result.redirectTo(ApplicationTypeController.class).applicationTypes();
	}

	@Put
	@Path("/admin/applicationType")
	public void update(ApplicationType applicationType) {
		this.applicationTypeDAO.update(applicationType);
		this.result.redirectTo(ApplicationTypeController.class).applicationTypes();
	}

	@Delete
	@Path("/admin/applicationType/{id}")
	public void delete(long id) {
		this.applicationTypeDAO.remove(this.applicationTypeDAO.load(id));
		this.result.redirectTo(ApplicationTypeController.class).applicationTypes();
	}
	
	@Get
	@Path("/admin/applicationType/{id}")
	public void show(long id) {
		ApplicationType applicationType = this.applicationTypeDAO.load(id);
		this.result.include("applicationType", applicationType);
	}
	
	@Path("/admin/applicationType/edit/{id}")
	public ApplicationType edit(long id) {
		return this.applicationTypeDAO.load(id);
	}

	@Path("/admin/applicationType/register")
	public void form() {
	}
	
	@Path("/admin/applicationTypes")
	public void applicationTypes(){	
		this.result.include("applicationTypes", this.applicationTypeDAO.list());
	}
}