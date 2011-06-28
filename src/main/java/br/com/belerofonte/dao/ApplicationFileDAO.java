package br.com.belerofonte.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
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

	@SuppressWarnings("unchecked")
	public List<ApplicationFile> topDownloads(int number) {
		return this.session.createCriteria(ApplicationFile.class).setMaxResults(number).addOrder(Order.desc("numberOfDownloads")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ApplicationFile> recentApplications(int number) {
		return this.session.createCriteria(ApplicationFile.class).setMaxResults(number).addOrder(Order.desc("uploadDate")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ApplicationFile> list() {
		return this.session.createCriteria(ApplicationFile.class).list();
	}
}