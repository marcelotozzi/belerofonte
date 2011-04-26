package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;

public class AccountTest {

	private Account account;

	@Before
	public void setUp() {
		this.account = new Account();
	}

	@Test
	public void thereShouldBeLogged() {
		Assert.assertNull(this.account.getUser());
	}

	@Test
	public void shouldLogin() {
		this.account.performLogin(Given.user(1L, "Name", "username", "email@belerofonte.com", "password", "password"));

		Assert.assertNotNull(this.account.getUser());
	}

	@Test
	public void shouldBeLogged() {
		this.account.performLogin(Given.user(1L, "Name", "username", "email@belerofonte.com", "password", "password"));

		Assert.assertTrue(this.account.isLogged());
	}

	@Test
	public void shouldLogOut() {
		this.account.performLogin(Given.user(1L, "Name", "username","email@belerofonte.com", "password", "password"));
	
		this.account.logoff();
		
		Assert.assertFalse(this.account.isLogged());
	}

	@Test
	public void shouldBeLoggedInAsAdmin() {
		this.account.performLogin(Given.user(1L, "Name", "admin","admin@belerofonte.com", "adminpass", "adminpass"));
		
		Assert.assertTrue(this.account.isAdmin());
	}

	@Test
	public void thereShouldBeLoggedInAsAdmin() {
		this.account.performLogin(Given.user(1L, "Name", "username","email@belerofonte.com", "password", "password"));
		
		Assert.assertFalse(this.account.isAdmin());
	}
}