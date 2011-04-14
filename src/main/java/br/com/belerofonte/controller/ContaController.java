package br.com.belerofonte.controller;

import br.com.belerofonte.components.Conta;
import br.com.belerofonte.dao.UsuarioDao;
import br.com.belerofonte.model.Usuario;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ContaController {
	private final UsuarioDao usuarioDao;
	private Conta conta;
	private Result result;

	public ContaController(Conta conta, UsuarioDao usuarioDao, Result result) {
		this.conta = conta;
		this.usuarioDao = usuarioDao;
		this.result = result;
	}

	@Path("conta/login")
	public void form() {
	}

	@Post
	@Path("/conta/autenticar")
	public void autenticar(Usuario usuario) {
		Usuario autenticado = usuarioDao.buscaPorLoginESenha(usuario);

		if (autenticado != null) {
			this.conta.efetuaLogin(autenticado);
			this.result.redirectTo(ContaController.class).conta();
			return;
		}
		this.result.redirectTo(ContaController.class).form();
	}

	@Path("/conta/logoff")
	public void logoff() {
		this.conta.logoff();
		this.result.redirectTo(HomeController.class).home();
	}
	
	@Path("/conta")
	public void conta() {
	}
}