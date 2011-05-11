package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
@InterceptResource
public class AccountController {
	private final UserDAO userDAO;
	private Account account;
	private final Result result;
	private Validator validator;

	public AccountController(UserDAO dao, Result result, Account account,Validator validator) {
		this.userDAO = dao;
		this.result = result;
		this.account = account;
		this.validator = validator;
	}

	@NoInterceptMethod
	@Path("/account/login")
	public void form() {
	}

	@NoInterceptMethod
	@Post
	@Path("/account/authenticates")	
	public void authenticates(User user) {
		User authenticated = this.userDAO.findByUsernameAndPassword(user);
		    
		if (authenticated != null) {
			this.account.performLogin(authenticated);
			result.redirectTo(AccountController.class).account();
		}else{
			this.validator.add(new ValidationMessage("erro","Login ou senha inv‡lido"));
			validator.onErrorUsePageOf(AccountController.class).form();
		}
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