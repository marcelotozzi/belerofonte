package br.com.belerofonte.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
import br.com.caelum.vraptor.validator.Validations;

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
	public void authenticates(final User user) {
		this.validator.checking(new Validations(){{
			that(!user.getUsername().isEmpty(),"username","username_not_reported");
			that(!user.getPassword().isEmpty(), "password", "password_not_reported");
		}});
		this.validator.onErrorRedirectTo(this).form();
		
		final User authenticated = this.userDAO.findByUsernameAndPassword(user);

		this.validator.checking(new Validations(){{
			that(authenticated, is(notNullValue()), "login", "invalid_login_or_password");
		}});
		this.validator.onErrorRedirectTo(this).form();
		
		this.account.performLogin(authenticated);
		
		this.result.redirectTo(AccountController.class).account();
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