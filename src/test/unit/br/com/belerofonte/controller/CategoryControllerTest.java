package br.com.belerofonte.controller;


import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
	
	@Test
	public void shouldCallFormMethod(){
		this.controller.form();
	}
	
	@Test
	public void shouldCallCategoriesMethodAndReturnListInResult(){
		this.controller.categories();
		@SuppressWarnings("unchecked")
		List<ApplicationCategory> objects = (List<ApplicationCategory>) this.result.included().get("categories");
		
		Assert.assertNotNull(objects);
	}
	
	@Test
	public void shouldCallShowMethodAndReturnApplicationCategoryInResult(){
		Mockito.when(this.categoryDAO.load(1L)).thenReturn(new ApplicationCategory());

		this.controller.show(1L);
		
		ApplicationCategory category = (ApplicationCategory) this.result.included().get("category");
		Assert.assertNotNull(category);
	}
	
	@Test
	public void shouldCallEditMethodAndReturnApplicationCategoryInResult(){
		Mockito.when(this.categoryDAO.load(1L)).thenReturn(new ApplicationCategory());

		this.controller.edit(1L);
		
		ApplicationCategory categpry = (ApplicationCategory) this.result.included().get("category");
		Assert.assertNotNull(categpry);
	}
	
	@Test
	public void shouldReturnJsonWithCategories() {
		this.controller.categoriesJson();
		fail("Nao implementado");
	}
}