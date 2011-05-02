package br.com.belerofonte.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.belerofonte.model.ApplicationType;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ApplicationTypeDAO {

	private Session session;
	
	public ApplicationTypeDAO(Session session) {
		this.session = session;
	}

	public void save(ApplicationType applicationType) {
		this.session.save(applicationType);
	}

	public void update(ApplicationType applicationType) {
		this.session.update(applicationType);
	}

	public ApplicationType load(long id) {
		return (ApplicationType) this.session.load(ApplicationType.class, id);
	}

	public void remove(ApplicationType applicationType) {
		this.session.delete(applicationType);
	}

	public ApplicationType findByName(String name) {
		return (ApplicationType) this.session
		.createCriteria(ApplicationType.class)
		.add(Restrictions.eq("name", name))
		.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ApplicationType> list() {
		return this.session.createCriteria(ApplicationType.class).list();
	}
}