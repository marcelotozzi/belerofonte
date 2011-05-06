package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.PlataformController;
import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class PlataformControllerInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldInterceptCreateMethodPLataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("create",
								Plataform.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("update",
								Plataform.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("delete",
								Long.class))));
	}

	@Test
	public void shouldInterceptShowMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("show",
								Long.class))));
	}
	
	@Test
	public void shouldInterceptEditMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("edit",
								Long.class))));
	}
	
	@Test
	public void shouldInterceptFormMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("form"))));
	}
	
	@Test
	public void shouldInterceptPlataformsMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("plataforms"))));
	}
}