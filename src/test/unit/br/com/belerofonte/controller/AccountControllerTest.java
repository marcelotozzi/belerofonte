package br.com.belerofonte.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class AccountControllerTest {

	private AccountController controller;
	private Account account;
	private Result result;
	@Mock
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.account = new Account();
		this.controller = new AccountController(userDAO, result, account);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldAuthenticateTheUser() {
		User user = Given.user(1, "username", "email", "pass", "pass");
		Mockito.when(this.userDAO.findByLoginAndPassword(user)).thenReturn(user);
		
		this.controller.authenticates(user);
		
		Assert.assertTrue(this.account.isLogged());
		Mockito.verify(this.userDAO).findByLoginAndPassword(user);
	}
	
	@Test
	public void shouldNotAuthenticateInvalidUser(){
		User user = Given.invalidUser(1, "usernameinvalid", "invalid@bele.com", "pass", "pass");
		Mockito.when(this.userDAO.findByLoginAndPassword(user)).thenReturn(null);
		
		this.controller.authenticates(user);
		
		Mockito.verify(this.userDAO).findByLoginAndPassword(user);
		Assert.assertFalse(this.account.isLogged());
	}

	@Test
	public void shouldLogoffTheUser() {
		this.controller.logoff();
		
		Assert.assertFalse(this.account.isLogged());
	}
}