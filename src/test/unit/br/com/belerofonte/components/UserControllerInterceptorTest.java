package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.UserController;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class UserControllerInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldNotInterceptFormMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class,
						UserController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptCreateMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("create", User.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("update", User.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("delete", Long.class))));
	}

	@Test
	public void shouldInterceptShowMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("show", Long.class))));
	}

	@Test
	public void shouldInterceptEditMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("edit", Long.class))));
	}

	@Test
	public void shouldInterceptVerifyUsernameMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("verifyUsername", String.class))));
	}
}