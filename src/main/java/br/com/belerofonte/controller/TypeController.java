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
	@Path("/admin/category/type")
	public void create(ApplicationType type) {
		this.applicationTypeDAO.save(type);
		this.result.redirectTo(TypeController.class).types();
	}

	@Put
	@Path("/admin/category/type")
	public void update(ApplicationType applicationType) {
		this.applicationTypeDAO.update(applicationType);
		this.result.redirectTo(TypeController.class).types();
	}

	@Delete
	@Path("/admin/category/type/{id}")
	public void delete(Long id) {
		this.applicationTypeDAO.remove(this.applicationTypeDAO.load(id));
		this.result.redirectTo(TypeController.class).types();
	}
	
	@Get
	@Path("/admin/category/type/{id}")
	public void show(Long id) {
		ApplicationType applicationType = this.applicationTypeDAO.load(id);
		this.result.include("applicationType", applicationType);
	}
	
	@Path("/admin/category/type/edit/{id}")
	public ApplicationType edit(Long id) {
		return this.applicationTypeDAO.load(id);
	}

	@Path("/admin/category/type/register")
	public void form() {
	}
	
	@Path("/admin/category/types")
	public void types(){	
		this.result.include("types", this.applicationTypeDAO.list());
	}
	
	@Path("/admin/category/types.json")
	public void applicationTypesJson(Long categoryId){
		this.result.use(Results.json()).from(this.applicationTypeDAO.list(), "types").serialize();
	}
}