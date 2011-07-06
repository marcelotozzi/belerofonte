package br.com.belerofonte.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;
import br.com.belerofonte.service.UserService;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;

@Resource
@InterceptResource
public class UserController {

	private Account account;
	private UserDAO userDAO;
	private Result result;
	private Validator validator;
	private UserService userService;

	public UserController(UserDAO userDAO, Account account, Result result, Validator validator, UserService userService) {
		this.account = account;
		this.userDAO = userDAO;
		this.result = result;
		this.validator = validator;
		this.userService = userService;
	}

	@NoInterceptMethod
	@Path("user/register")
	public void form() {
	}

	@NoInterceptMethod
	@Post
	@Path("user")
	public void create(final User user) {
		validator.checking(new Validations() {{
		    boolean usernameDoesNotExist = !userDAO.containsUserWithUsername(user.getUsername());
		    that(usernameDoesNotExist, "username", "username_already_exists");
		    
		    that(!user.getName().isEmpty(), "Name", "name_not_reported");			   
		    that(user.getUsername().matches("[a-z0-9_]+"), "username", "invalid_username");
		    that(!user.getEmail().isEmpty(), "email", "email_not_reported");
		    that(!user.getPassword().isEmpty(), "password", "password_not_reported");
		    that(!user.getConfirmPassword().isEmpty(), "confirm_password", "password_not_reported");
		    that(user.getPassword().equals(user.getConfirmPassword()), "equals_password", "password_not_equals");
		}});
		validator.onErrorRedirectTo(this).form();
		
		this.userDAO.save(user);
		this.account.performLogin(this.userDAO.findByUsername(user.getUsername()));
		this.result.redirectTo(AccountController.class).account();
	}

	@Put
	@Path("user")
	public void update(final User user, final UploadedFile photo) {
		validator.checking(new Validations() {{
			that(photo, is(notNullValue()), "photo", "photo_not_reported");
		    that(!user.getName().isEmpty(), "Name", "name_not_reported");			   
		    that(user.getUsername().matches("[a-z0-9_]+"), "username", "invalid_username");
		    that(!user.getEmail().isEmpty(), "email", "email_not_reported");
		    that(!user.getPassword().isEmpty(), "password", "password_not_reported");
		    that(!user.getConfirmPassword().isEmpty(), "confirm_password", "password_not_reported");
		    that(user.getPassword().equals(user.getConfirmPassword()), "equals_password", "password_not_equals");
		}});
		validator.onErrorRedirectTo(this).edit(user.getId());
		
		this.userService.update(user, photo);
		
		this.result.redirectTo(UserController.class).show(this.account.getUser().getId());
	}

	@Delete
	@Path("/user/{id}")
	public void delete(Long id) {
		this.userDAO.remove(this.userDAO.load(id));
		this.result.redirectTo(UserController.class).show(this.account.getUser().getId());
	}

	@NoInterceptMethod
	@Get
	@Path("/user/{id}/{login}")
	public void show(Long id) {
		User person = this.userDAO.load(id);
		this.result.include("person", person);
	}

	@Path("/user/edit/{id}")
	public void edit(Long id) {
		 User user = this.userDAO.load(id);
		 result.include("user", user);
	}

	@Path("/user/verify/username")
	public boolean verifyUsername(String username) {
		return (this.userDAO.findByUsername(username) != null) ? true : false;
	}
}
