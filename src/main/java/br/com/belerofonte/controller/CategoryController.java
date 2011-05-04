package br.com.belerofonte.controller;

import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.model.ApplicationCategory;

public class CategoryController {

	private ApplicationCategoryDAO categoryDAO;

	public CategoryController(ApplicationCategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public void create(ApplicationCategory category) {
		this.categoryDAO.save(category);
	}

	public void update(ApplicationCategory category) {
		this.categoryDAO.update(category);
	}

	public void delete(Long id) {
		this.categoryDAO.remove(this.categoryDAO.load(id));
	}

}
