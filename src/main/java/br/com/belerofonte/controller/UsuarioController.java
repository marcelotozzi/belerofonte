package br.com.belerofonte.controller;

import br.com.belerofonte.dao.UsuarioDao;
import br.com.belerofonte.model.Usuario;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

public class UsuarioController {

	private final UsuarioDao usuarioDao;

	public UsuarioController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Post
	@Path("/usuario")
	public void criar(Usuario usuario) {
		this.usuarioDao.cria(usuario);
	}

	@Put
	@Path("/usuario")
	public void atualizar(Usuario usuario) {
		this.usuarioDao.atualiza(usuario);
	}

	@Delete
	@Path("/usuario")
	public void deletar(Usuario usuario) {
		this.usuarioDao.deleta(usuario);
	}

	@Path("/usuario/registrar")
	public void form() {
	}

	@Get
	@Path("/usuario/{id}/{login}")
	public void show(long id) {
	}
	
	@Path("/usuario/editar/{id}")
	public Usuario editar(long id) {
		return usuarioDao.buscaPorId(id);
	}
}