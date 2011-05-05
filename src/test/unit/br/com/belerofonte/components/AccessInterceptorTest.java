package br.com.belerofonte.components;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.AdminController;
import br.com.belerofonte.controller.HomeController;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class AccessInterceptorTest {

	private AccessInterceptor interceptor;
	private ResourceMethod method;
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
	public void shouldInterceptMethodAdminInAdminController() throws SecurityException, NoSuchMethodException {
		this.method = DefaultResourceMethod.instanceFor(AdminController.class,AdminController.class.getMethod("admin"));

		Assert.assertTrue(this.interceptor.accepts(this.method));
	}
	
	@Test
	public void shouldInterceptMethodHomeInHomeController() throws SecurityException, NoSuchMethodException {
		this.method = DefaultResourceMethod.instanceFor(HomeController.class, HomeController.class.getMethod("home"));

		Assert.assertFalse(this.interceptor.accepts(this.method));
	}
}