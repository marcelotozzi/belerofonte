package br.com.belerofonte.dao;

import org.hibernate.Session;

import br.com.belerofonte.model.Category;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class CategoryDAO {

	private Session session;

	public void save(Category category) {
		this.session.save(category);
	}

	public void update(Category category) {
		this.session.update(category);
	}

	public Category load(long id) {
		return (Category) this.session.load(Category.class, id);
	}

	public void remove(Category category) {
		this.session.delete(category);
	}

	public Category findByName(String name) {
		return null;
	}
}