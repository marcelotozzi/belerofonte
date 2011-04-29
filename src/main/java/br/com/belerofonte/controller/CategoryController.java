package br.com.belerofonte.controller;

import br.com.belerofonte.dao.CategoryDAO;
import br.com.belerofonte.model.Category;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class CategoryController {

	private CategoryDAO categoryDAO;
	private Result result;

	public CategoryController(CategoryDAO categoryDAO, Result result) {
		this.categoryDAO = categoryDAO;
		this.result = result;
	}

	@Post
	@Path("/admin/category")
	public void create(Category category) {
		this.categoryDAO.save(category);
		this.result.redirectTo(CategoryController.class).categories();
	}

	@Put
	@Path("/admin/category")
	public void update(Category category) {
		this.categoryDAO.update(category);
		this.result.redirectTo(CategoryController.class).categories();
	}

	@Delete
	@Path("/admin/category/{id}")
	public void delete(long id) {
		this.categoryDAO.remove(this.categoryDAO.load(id));
		this.result.redirectTo(CategoryController.class).categories();
	}
	
	@Get
	@Path("/admin/category/{id}")
	public void show(long id) {
		Category category = this.categoryDAO.load(id);
		this.result.include("category", category);
	}
	
	@Path("/admin/category/edit/{id}")
	public Category edit(long id) {
		return this.categoryDAO.load(id);
	}

	@Path("/admin/category/register")
	public void form() {
	}
	
	@Path("/admin/categories")
	public void categories(){	
		this.result.include("categories", this.categoryDAO.list());
	}
}