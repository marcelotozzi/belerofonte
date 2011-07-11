package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.model.ApplicationCategory;
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
public class CategoryController {

	private ApplicationCategoryDAO categoryDAO;
	private Result result;
	private Validator validator;

	public CategoryController(ApplicationCategoryDAO categoryDAO, Result result, Validator validator) {
		this.categoryDAO = categoryDAO;
		this.result = result;
		this.validator = validator;
	}

	@Post("/admin/category")
	public void create(final ApplicationCategory category) {
		this.validator.checking(new Validations() {{
			 boolean categoryNameDoesNotExist = !categoryDAO.containsCategoryWithName(category.getName());
			 that(categoryNameDoesNotExist, "name", "category_name_already_exists");
			    
			 that(!category.getName().isEmpty(), "name", "category_name_not_reported");			   
		}});
		validator.onErrorRedirectTo(this).form();
		
		this.categoryDAO.save(category);
		this.result.redirectTo(CategoryController.class).categories();
	}

	@Put("/admin/category")
	public void update(final ApplicationCategory category) {
		this.validator.checking(new Validations() {{
			 boolean categoryNameDoesNotExist = !categoryDAO.containsCategoryWithName(category.getName());
			 that(categoryNameDoesNotExist, "name", "category_name_already_exists");
			    
			 that(!category.getName().isEmpty(), "name", "category_name_not_reported");			   
		}});
		validator.onErrorRedirectTo(this).edit(category.getId());
		
		this.categoryDAO.update(category);
		this.result.redirectTo(CategoryController.class).categories();
	}

	@Delete("/admin/category/{id}")
	public void delete(Long id) {
		this.categoryDAO.remove(this.categoryDAO.load(id));
		this.result.redirectTo(CategoryController.class).categories();
	}

	@NoInterceptMethod
	@Get("/category/{id}")
	public void show(Long id) {
		ApplicationCategory category = this.categoryDAO.load(id);
		this.result.include("category", category);
	}
	
	@Path("/admin/category/edit/{id}")
	public void edit(Long id) {
		ApplicationCategory category = this.categoryDAO.load(id);
		this.result.include("category", category);
	}

	@Path("/admin/category/register")
	public void form() {
	}

	@NoInterceptMethod
	@Path("/categories")
	public void categories(){		
		this.result.include("categories", this.categoryDAO.list());
	}
	
	@Path("/admin/categories.json")
	public void categoriesJson(){	
		this.result.use(Results.json()).from(this.categoryDAO.list(), "categories").serialize();
	}
}
