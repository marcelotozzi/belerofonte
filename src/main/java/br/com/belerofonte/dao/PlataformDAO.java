package br.com.belerofonte.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class PlataformDAO {

	private Session session;

	public PlataformDAO(Session session) {
		this.session = session;
	}

	public void save(Plataform plataform) {
		this.session.save(plataform);
	}

	public void update(Plataform plataform) {
		this.session.update(plataform);
	}

	public Plataform load(long id) {
		return (Plataform) this.session.load(Plataform.class, id);
	}

	public void remove(Plataform plataform) {
		this.session.delete(plataform);
	}

	public Plataform findByName(String name) {
		return (Plataform) this.session
			.createCriteria(Plataform.class)
			.add(Restrictions.eq("name", name))
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Plataform> list() {
		return this.session.createCriteria(Plataform.class).addOrder(Order.asc("name")).list();
	}

	public boolean containsPlataformWithName(String name) {
		return !this.session.createCriteria(Plataform.class).add(Restrictions.eq("name", name)).list().isEmpty();
	}
}