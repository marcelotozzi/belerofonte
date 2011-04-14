package br.com.belerofonte.components;

import br.com.belerofonte.model.Usuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Conta {
	private Usuario usuario;

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void efetuaLogin(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return (this.usuario != null) ? true : false;
	}

	public void logoff() {
		this.usuario = null;
	}

	public boolean isAdmin() {
		return (this.usuario.getLogin().equals("admin")) ? true : false;
	}
}