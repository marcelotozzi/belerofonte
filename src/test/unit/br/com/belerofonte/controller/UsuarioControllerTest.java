package br.com.belerofonte.controller;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.dao.UsuarioDao;
import br.com.belerofonte.model.Usuario;

public class UsuarioControllerTest {

	private UsuarioController controller;
	@Mock
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new UsuarioController(this.usuarioDao);
	}
	
	@Test
	public void deveriaCadastrarUsuario(){
		Usuario usuario = new Usuario();
		
		this.controller.criar(usuario);
		
		Mockito.verify(this.usuarioDao).cria(usuario);
	}
	
	@Test
	public void deveriaAtualizarUsuario(){
		Usuario usuario = new Usuario();;

		this.controller.atualizar(usuario);
		
		Mockito.verify(this.usuarioDao).atualiza(usuario);
	}
	
	@Test
	public void deveriaRemoverUsuario(){
		Usuario usuario = new Usuario();;

		this.controller.deletar(usuario);
		
		Mockito.verify(this.usuarioDao).deleta(usuario);
	}
}
