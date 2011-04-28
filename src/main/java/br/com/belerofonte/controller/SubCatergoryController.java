package br.com.belerofonte.controller;

import br.com.belerofonte.dao.SubCategoryDAO;
import br.com.belerofonte.model.SubCategory;
import br.com.caelum.vraptor.Resource;

@Resource
public class SubCatergoryController {

	private SubCategoryDAO subCategoryDAO;

	public SubCatergoryController(SubCategoryDAO subCategoryDAO) {
		this.subCategoryDAO = subCategoryDAO;
	}

	public void create(SubCategory subCategory) {
		this.subCategoryDAO.save(subCategory);
	}

	public void update(SubCategory subCategory) {
		this.subCategoryDAO.update(subCategory);
	}

	public void delete(long id) {
		this.subCategoryDAO.remove(this.subCategoryDAO.load(id));
	}
}