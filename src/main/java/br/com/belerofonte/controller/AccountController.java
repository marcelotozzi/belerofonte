package br.com.belerofonte.controller;

import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class AccountController {
	private final UserDAO userDAO;
	private Account account;
	private final Result result;

	public AccountController(UserDAO dao, Result result, Account account) {
		this.userDAO = dao;
		this.result = result;
		this.account = account;
	}

	@Path("/account/login")
	public void form() {
	}

	@Post
	@Path("/account/authenticates")	
	public void authenticates(User user) {
		User authenticated = this.userDAO.findByUsernameAndPassword(user);

		if (authenticated != null) {
			this.account.performLogin(authenticated);
			result.redirectTo(AccountController.class).account();
			return;
		}
		result.redirectTo(AccountController.class).form();
	}

	@Path("/account/logoff")
	public void logoff() {
		this.account.logoff();
		this.result.redirectTo(HomeController.class).home();
	}

	@Path("/account")
	public void account() {
	}
}