package br.com.belerofonte.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
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
		this.session.delete(user);
	}

	public User load(long id) {
		return (User) this.session.load(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		return this.session.createCriteria(User.class).addOrder(Order.asc("username")).list();
	}

	public boolean containsUserWithUsername(String username) {
		return !this.session.createCriteria(User.class).add(Restrictions.eq("username", username)).list().isEmpty();
	}
}