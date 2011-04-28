package br.com.belerofonte.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.CategoryDAO;
import br.com.belerofonte.model.Category;

public class CategoryControllerTest {

	private CategoryController controller;
	@Mock private CategoryDAO categoryDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new CategoryController(this.categoryDAO);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterCategory() {
		Category category = Given.category("Games");

		this.controller.create(category);

		Mockito.verify(this.categoryDAO).save(category);
	}
	
	@Test
	public void shouldUpdateCategory() {
		Category category = Given.category("Games");
		
		this.controller.update(category);

		Mockito.verify(this.categoryDAO).update(category);
	}

	@Test
	public void shouldDeleteCategory() {
		Category category = this.categoryDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.categoryDAO).remove(category);
	}
}