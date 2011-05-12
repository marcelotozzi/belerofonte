package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class AdminSpec {
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
	public void shouldEnterInTheAdminPage() {
		GivenImInTheAdminPage();

		cw.checkMessage("Administração");
		cw.checkMessage("Plataformas");
		cw.checkMessage("Categorias");
	}

	@Test
	public void shouldEnterInPlataformsPage() {
		GivenImInTheAdminPage();
		
		cw.clickTheLink("Plataformas");
		
		cw.checkMessage("Plataformas");
	}
	
	@Test
	public void shouldEnterInCategoriesPage() {
		GivenImInTheAdminPage();
		
		cw.clickTheLink("Categorias");
		
		cw.checkMessage("Categorias");
	}

	private void GivenImInTheAdminPage() {
		cw.login("admin", "admin");
		cw.checkMessage("Bem vindo, Admin!");
		cw.navigateToPage("admin");
	}
}