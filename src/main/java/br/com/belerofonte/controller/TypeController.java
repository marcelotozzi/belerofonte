package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.model.ApplicationType;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
@InterceptResource
public class TypeController {

	private ApplicationTypeDAO applicationTypeDAO;
	private Result result;

	public TypeController(ApplicationTypeDAO applicationTypeDAO, Result result) {
		this.applicationTypeDAO = applicationTypeDAO;
		this.result = result;
	}

	@Post
	@Path("/admin/type")
	public void create(ApplicationType type) {
		this.applicationTypeDAO.save(type);
		this.result.redirectTo(TypeController.class).types();
	}

	@Put
	@Path("/admin/type")
	public void update(ApplicationType type) {
		this.applicationTypeDAO.update(type);
		this.result.redirectTo(TypeController.class).types();
	}

	@Delete
	@Path("/admin/type/{id}")
	public void delete(Long id) {
		this.applicationTypeDAO.remove(this.applicationTypeDAO.load(id));
		this.result.redirectTo(TypeController.class).types();
	}
	
	@Get
	@Path("/admin/type/{id}")
	public void show(Long id) {
		ApplicationType applicationType = this.applicationTypeDAO.load(id);
		this.result.include("type", applicationType);
	}
	
	@Path("/admin/type/edit/{id}")
	public void edit(Long id) {
		ApplicationType applicationType = this.applicationTypeDAO.load(id);
		this.result.include("type", applicationType);
	}

	@Path("/admin/type/register")
	public void form() {
	}
	
	@Path("/admin/types")
	public void types(){	
		this.result.include("types", this.applicationTypeDAO.list());
	}
	
	@Path("/admin/types.json")
	public void applicationTypesJson(){
		this.result.use(Results.json()).from(this.applicationTypeDAO.list(), "types").serialize();
	}
}