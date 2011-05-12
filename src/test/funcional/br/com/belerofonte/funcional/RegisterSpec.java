package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class RegisterSpec {
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
	public void shouldRegisterNewUser(){
		cw.navigateToPage("user/register");
		
		cw.checkMessage("Registre-se");
		
		Long aleatorio = System.currentTimeMillis();
		cw.TypeAtField(aleatorio.toString(), "user.name");
		cw.TypeAtField(aleatorio.toString(), "user.username");
		cw.TypeAtField("jose@bele.com", "user.email");
		cw.TypeAtField("jose", "user.username");
		cw.TypeAtField("senhadoze", "user.password");
		cw.TypeAtField("senhadoze", "user.confirmPassword");		
		cw.submitForm("newUser");
		
		cw.checkMessage("Bem vindo, "+aleatorio.toString()+"!");
	}
}
