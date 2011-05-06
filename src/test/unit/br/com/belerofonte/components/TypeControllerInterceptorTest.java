package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.TypeController;
import br.com.belerofonte.model.ApplicationType;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class TypeControllerInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldInterceptCreateMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("create",
								ApplicationType.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("update",
								ApplicationType.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodDeleteTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("delete",
								Long.class))));
	}

	@Test
	public void shouldInterceptShowMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("show",
								Long.class))));
	}
	
	@Test
	public void shouldInterceptEditMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("edit",
								Long.class))));
	}
	
	@Test
	public void shouldInterceptFormMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("form"))));
	}
	
	@Test
	public void shouldInterceptApplicationTypesMethodTypeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(TypeController.class,
						TypeController.class.getDeclaredMethod("applicationTypes"))));
	}
}