package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;
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
public class PlataformController {

	private PlataformDAO plataformDAO;
	private Result result;

	public PlataformController(PlataformDAO plataformDAO, Result result) {
		this.plataformDAO = plataformDAO;
		this.result = result;
	}

	@Post
	@Path("/admin/plataform")
	public void create(Plataform plataform) {
		this.plataformDAO.save(plataform);	
		this.result.redirectTo(PlataformController.class).plataforms();
	}

	@Put
	@Path("/admin/plataform")
	public void update(Plataform plataform) {
		this.plataformDAO.update(plataform);
		this.result.redirectTo(PlataformController.class).plataforms();
	}

	@Delete
	@Path("/admin/plataform/{id}")
	public void delete(Long id) {
		this.plataformDAO.remove(this.plataformDAO.load(id));
		this.result.redirectTo(PlataformController.class).plataforms();
	}
	
	@Get
	@Path("/admin/plataform/{id}")
	public void show(Long id) {
		Plataform plataform = this.plataformDAO.load(id);
		this.result.include("plataform", plataform);
	}
	
	@Path("/admin/plataform/edit/{id}")
	public Plataform edit(Long id) {
		return this.plataformDAO.load(id);
	}

	@Path("/admin/plataform/register")
	public void form() {
	}
	
	@Path("/admin/plataforms")
	public void plataforms(){		
		this.result.include("plataforms", this.plataformDAO.list());
	}
	
	@Path("/admin/plataforms.json")
	public void plataformsJson(){	
		this.result.use(Results.json()).from(this.plataformDAO.list(), "plataforms").serialize();
	}
}