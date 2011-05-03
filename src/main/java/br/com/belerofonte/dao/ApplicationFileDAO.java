package br.com.belerofonte.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ApplicationFileDAO {

	private Session session;

	public ApplicationFileDAO(Session session) {
		this.session = session;
	}
	
	public void save(ApplicationFile applicationFile) {
		this.session.save(applicationFile);
	}

	public ApplicationFile load(long id) {
		return (ApplicationFile) this.session.load(ApplicationFile.class, id);
	}

	public void delete(ApplicationFile applicationFile) {
		this.session.delete(applicationFile);
	}

	public void update(ApplicationFile applicationFile) {
		this.session.update(applicationFile);
	}

	public ApplicationFile findByName(String name) {
		return (ApplicationFile) this.session
		.createCriteria(ApplicationFile.class)
		.add(Restrictions.eq("name", name))
		.uniqueResult();
	}

	public void remove(ApplicationFile applicationFile) {
		this.session.delete(applicationFile);
	}
}