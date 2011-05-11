package br.com.belerofonte.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;

public class PreDados {
	public static void main(String[] args) {
		Session session = DaoTest.getSession();
		Transaction tx = session.beginTransaction();
		UserDAO userDAO  = new UserDAO(session);
		
		List<User> users = userDAO.list();
		for (User user : users) {
			userDAO.remove(user);
		}
		userDAO.save(Given.user(null, "Admin", "admin", "admin@gmail.com", "admin", "admin"));
		userDAO.save(Given.user(null, "Username", "username", "admin@gmail.com", "password", "password"));
		tx.commit();
		session.close();
	}
}