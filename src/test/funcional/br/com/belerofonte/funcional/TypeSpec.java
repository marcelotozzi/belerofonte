package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class TypeSpec {
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
	public void shouldEnterInTypesPage() {
		cw.givenImInTheTypesPage();
		cw.checkMessage("Tipos");
	}
	
	@Test
	public void shouldRegisterType(){
		cw.givenImInTheTypesPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre um tipo");
		Long aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "type.name");
		cw.submitForm("newType");
		cw.checkMessage(aleatorio.toString());
	}
}