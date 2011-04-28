package br.com.belerofonte.dao;

import org.hibernate.Session;

import br.com.belerofonte.model.SubCategory;

public class SubCategoryDAO {

	private Session session;

	public void save(SubCategory subCategory) {
		this.session.save(subCategory);
	}

	public void update(SubCategory subCategory) {
		this.session.update(subCategory);
	}

	public SubCategory load(long id) {
		return (SubCategory) this.session.load(SubCategory.class, id);
	}

	public void remove(SubCategory subCategory) {
		this.session.delete(subCategory);
	}
}