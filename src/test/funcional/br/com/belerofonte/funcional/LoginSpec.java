package br.com.belerofonte.funcional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.util.CommonWebSteps;

public class LoginSpec {
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
	public void shouldLoginInAppLikeAdmin() {
		cw.login("admin", "admin");

		cw.checkMessage("Bem vindo, Admin!");
	}

	@Test
	public void shouldLoginInAppLikeNormalUser() {
		cw.login("username", "password");

		cw.checkMessage("Bem vindo, Username!");
	}

	@Test
	public void shouldNotLoginInAppLikeInvalidUser() {
		cw.login("invalid", "invalid");

		cw.checkMessage("Login ou senha inv‡lido");
	}
	
	@Test 
	public void shouldLogoffApp(){
		cw.login("username", "password");

		cw.checkMessage("Bem vindo, Username!");
		
		cw.clickTheLink("Logoff");
		
	}
}