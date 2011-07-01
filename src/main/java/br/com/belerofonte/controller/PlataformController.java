package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
@InterceptResource
public class PlataformController {

	private PlataformDAO plataformDAO;
	private Result result;
	private Validator validator;

	public PlataformController(PlataformDAO plataformDAO, Result result, Validator validator) {
		this.plataformDAO = plataformDAO;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/admin/plataform")
	public void create(final Plataform plataform) {
		this.validator.checking(new Validations() {{
			 boolean plataformNameDoesNotExist = !plataformDAO.containsPlataformWithName(plataform.getName());
			 that(plataformNameDoesNotExist, "name", "plataform_name_already_exists");
			    
			 that(!plataform.getName().isEmpty(), "name", "plataform_name_not_reported");			   
		}});
		validator.onErrorRedirectTo(this).form();
		
		this.plataformDAO.save(plataform);	
		this.result.redirectTo(PlataformController.class).plataforms();
	}

	@Put
	@Path("/admin/plataform")
	public void update(final Plataform plataform) {
		this.validator.checking(new Validations() {{
			 boolean plataformNameDoesNotExist = !plataformDAO.containsPlataformWithName(plataform.getName());
			 that(plataformNameDoesNotExist, "name", "plataform_name_already_exists");
			    
			 that(!plataform.getName().isEmpty(), "name", "plataform_name_not_reported");			   
		}});
		validator.onErrorRedirectTo(this).edit(plataform.getId());
		
		this.plataformDAO.update(plataform);
		this.result.redirectTo(PlataformController.class).plataforms();
	}

	@Delete
	@Path("/admin/plataform/{id}")
	public void delete(Long id) {
		this.plataformDAO.remove(this.plataformDAO.load(id));
		this.result.redirectTo(PlataformController.class).plataforms();
	}
	
	@NoInterceptMethod
	@Get
	@Path("/plataform/{id}")
	public void show(Long id) {
		Plataform plataform = this.plataformDAO.load(id);
		this.result.include("plataform", plataform);
	}
	
	@Path("/admin/plataform/edit/{id}")
	public void edit(Long id) {
		Plataform plataform = this.plataformDAO.load(id);
		this.result.include("plataform", plataform);
	}

	@Path("/admin/plataform/register")
	public void form() {
	}
	
	@NoInterceptMethod
	@Path("/plataforms")
	public void plataforms(){		
		this.result.include("plataforms", this.plataformDAO.list());
	}
	
	@Path("/admin/plataforms.json")
	public void plataformsJson(){	
		this.result.use(Results.json()).from(this.plataformDAO.list(), "plataforms").serialize();
	}
}