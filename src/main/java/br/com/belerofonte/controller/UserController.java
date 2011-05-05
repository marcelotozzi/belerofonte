package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@InterceptResource
public class UserController {

	private Account account;
	private UserDAO userDAO;
	private Result result;

	public UserController(UserDAO userDAO, Account account, Result result) {
		this.account = account;
		this.userDAO = userDAO;
		this.result = result;
	}

	@NoInterceptMethod
	@Path("user/register")
	public void form() {
	}

	@NoInterceptMethod
	@Post
	@Path("user")
	public void create(User user) {
		this.userDAO.save(user);
		this.account.performLogin(this.userDAO.findByUsername(user.getUsername()));
		this.result.redirectTo(AccountController.class).account();
	}

	@Put
	@Path("user")
	public void update(User user) {
		this.userDAO.update(user);
		this.result.redirectTo(UserController.class).show(this.account.getUser().getId());
	}

	@Delete
	@Path("/user/{id}")
	public void delete(long id) {
		this.userDAO.remove(this.userDAO.load(id));
		this.result.redirectTo(UserController.class).show(this.account.getUser().getId());
	}

	@Get
	@Path("/user/{id}/{login}")
	public void show(long id) {
		User person = this.userDAO.load(id);
		this.result.include("person", person);
	}

	@Path("/user/edit/{id}")
	public User edit(long id) {
		return this.userDAO.load(id);
	}

	@Path("/user/verify/username")
	public boolean verifyUsername(String username) {
		return (this.userDAO.findByUsername(username) != null) ? true : false;
	}
}
