package br.com.belerofonte.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.SubCategoryDAO;
import br.com.belerofonte.model.SubCategory;

public class SubCatergoryControllerTest {

	private SubCatergoryController controller;
	@Mock private SubCategoryDAO subCategoryDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new SubCatergoryController(this.subCategoryDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldRegisterSubCategory() {
		SubCategory subCategory = Given.subCategory("Games", "Ação");
		
		this.controller.create(subCategory);
		
		Mockito.verify(this.subCategoryDAO).save(subCategory);
	}
	
	@Test
	public void shouldUpdateSubCategory() {
		SubCategory subCategory = Given.subCategory("Games", "Ação");
		
		this.controller.update(subCategory);
		
		Mockito.verify(this.subCategoryDAO).update(subCategory);
	}

	@Test
	public void shouldDeleteSubCategory() {
		SubCategory subCategory = this.subCategoryDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.subCategoryDAO).remove(subCategory);
	}
}