package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.HomeController;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class HomeControllerInterceptorTest {

	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}
	
	@Test
	public void shouldNotInterceptTheHomeMethodHomeController() throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(HomeController.class, HomeController.class.getDeclaredMethod("home"))));
	}
}