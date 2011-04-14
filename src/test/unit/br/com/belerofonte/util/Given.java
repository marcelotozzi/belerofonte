package br.com.belerofonte.util;

import br.com.belerofonte.model.Usuario;

public class Given {

	public static Usuario usuario(String nome, String login, String senha) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		return usuario;
	}
}
