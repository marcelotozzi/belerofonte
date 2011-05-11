package br.com.belerofonte.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class CategoryControllerTest {

	private CategoryController controller;
	@Mock private ApplicationCategoryDAO categoryDAO;
	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new CategoryController(this.categoryDAO, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterCategory() {
		ApplicationCategory cat = Given.category(1L,"ANDROID");
		
		this.controller.create(cat);
		
		Mockito.verify(this.categoryDAO).save(cat);
	}
	
	@Test
	public void shouldUpdateCategory() {
		ApplicationCategory cat = Given.category(1L,"ANDROID");
		
		this.controller.update(cat);
		
		Mockito.verify(this.categoryDAO).update(cat);
	}

	@Test
	public void shouldDeleteCategory() {
		ApplicationCategory cat = this.categoryDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.categoryDAO).remove(cat);
	}
}