package br.com.belerofonte.components;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.AccountController;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class AccountControllerInterceptorTest {

	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldNotInterceptTheFormMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptTheAuthenticatesMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class, AccountController.class
						.getDeclaredMethod("authenticates", User.class))));
	}

	@Test
	public void shouldInterceptTheLogoffMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("logoff"))));
	}

	@Test
	public void shouldInterceptTheAccountMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("account"))));
	}
}