package br.com.belerofonte.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.components.Conta;
import br.com.belerofonte.dao.UsuarioDao;
import br.com.belerofonte.model.Usuario;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class ContaControllerTest {

	private ContaController controller;
	private Result result;
	private Conta conta;
	@Mock
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.conta = new Conta();
		this.result = new MockResult();
		this.controller = new ContaController(this.conta, this.usuarioDao, this.result);
	}

	@Test
	public void deveriaAutenticarUsuario() {
		Usuario usuario = new Usuario();
		this.controller.autenticar(usuario);

		Mockito.verify(this.usuarioDao).buscaPorLoginESenha(usuario);
	}

	@Test
	public void deveriaDeslogar() {
		this.controller.autenticar(new Usuario());

		this.controller.logoff();

		Assert.assertFalse(this.conta.isLogado());
	}

}
