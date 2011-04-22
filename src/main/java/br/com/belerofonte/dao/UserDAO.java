package br.com.belerofonte.dao;

import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class UserDAO {

	public User findByLoginAndPassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}