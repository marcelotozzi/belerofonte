package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.components.Conta;
import br.com.belerofonte.model.Usuario;
import br.com.belerofonte.util.Given;

public class ContaTest {
	private Conta conta;

	@Before
	public void setUp() throws Exception {
		this.conta = new Conta();
	}

	@Test
	public void deveriaEfetuarLogin() {
		this.conta.efetuaLogin(new Usuario());

		Assert.assertTrue(this.conta.isLogado());
	}

	@Test
	public void deveriaEfetuarLogoff() {
		this.conta.efetuaLogin(new Usuario());

		this.conta.logoff();

		Assert.assertFalse(this.conta.isLogado());
	}

	@Test
	public void deveriaEstarLogadoComoAdmin() {
		this.conta.efetuaLogin(Given.usuario("Nome","admin","senha"));

		Assert.assertTrue(this.conta.isAdmin());
	}

	@Test
	public void naoDeveriaEstarLogadoComoAdmin() {
		this.conta.efetuaLogin(Given.usuario("Nome","login","senha"));

		Assert.assertFalse(this.conta.isAdmin());
	}
}