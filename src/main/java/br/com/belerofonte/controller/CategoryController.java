package br.com.belerofonte.controller;

import br.com.belerofonte.dao.CategoryDAO;
import br.com.belerofonte.model.Category;

public class CategoryController {

	private CategoryDAO categoryDAO;

	public CategoryController(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public void create(Category category) {
		this.categoryDAO.save(category);
	}

	public void update(Category category) {
		this.categoryDAO.update(category);
	}

	public void delete(Long id) {
		this.categoryDAO.remove(this.categoryDAO.load(id));
	}

}
