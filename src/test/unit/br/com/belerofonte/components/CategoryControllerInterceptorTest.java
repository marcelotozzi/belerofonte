package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.CategoryController;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class CategoryControllerInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldInterceptCreateMethodCategoryController() throws SecurityException, NoSuchMethodException {
		
		System.out.println();
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class,
						CategoryController.class.getDeclaredMethod("create", ApplicationCategory.class))));
	}
	
	@Test
	public void shouldInterceptUpdateMethodCategoryController() throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class,
						CategoryController.class.getDeclaredMethod("update",ApplicationCategory.class))));
	}
	
	@Test
	public void shouldInterceptDeleteMethodCategoryController() throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class,
						CategoryController.class.getDeclaredMethod("delete", Long.class))));
	}
}
