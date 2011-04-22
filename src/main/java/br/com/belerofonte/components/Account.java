package br.com.belerofonte.components;

import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Account {
	private User user;

	public User getUser() {
		return this.user;
	}

	public void performLogin(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return (this.user != null) ? true : false;
	}

	public void logoff() {
		this.user = null;
	}

	public boolean isAdmin() {
		return (this.user.getUsername().equals("admin")) ? true : false;
	}
}