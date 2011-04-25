package br.com.belerofonte.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class UserDAO {
	private Session session;

	public UserDAO(Session session) {
		this.session = session;
	}

	public User findByUsernameAndPassword(User user) {
		return (User) this.session.createCriteria(User.class)
				.add(Restrictions.eq("username", user.getUsername()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();

	}

	public void save(User user) {
		this.session.save(user);
	}

	public User findByUsername(String username) {
		return (User) this.session.createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
	}

	public void update(User user) {
		this.session.update(user);
	}

	public void remove(User user) {
		// TODO Auto-generated method stub
	}

	public User load(long id) {
		return (User) this.session.load(User.class, id);
	}
}