package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class CategorySpec {
	private CommonWebSteps cw;

	@Before
	public void setUp() throws Exception {
		cw = new CommonWebSteps();
	}

	@After
	public void tearDown() throws Exception {
		cw.closeAndQuit();
	}

	@Test
	public void shouldEnterInCategoriesPage() {
		cw.givenImInTheCategoriesPage();
		cw.checkMessage("Categorias");
	}
	
	@Test
	public void shouldRegisterCategory(){
		cw.givenImInTheCategoriesPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre uma categoria");
		Long aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "category.name");
		cw.submitForm("newCategory");
		cw.checkMessage("- "+aleatorio.toString()+" -");
	}
}
