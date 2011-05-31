package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class PlataformSpec {
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
	public void shouldEnterInPlataformPage() {
		cw.givenImInThePlataformPage();
		cw.checkMessage("Plataformas");
	}
	
	@Test
	public void shouldRegisterPlataform(){
		cw.givenImInThePlataformPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre uma plataforma");
		Long aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "plataform.name");
		cw.submitForm("newPlataform");
		cw.checkMessage(aleatorio.toString());
	}
}
