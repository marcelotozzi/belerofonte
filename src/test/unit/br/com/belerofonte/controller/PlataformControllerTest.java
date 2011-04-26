package br.com.belerofonte.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;

public class PlataformControllerTest {

	@Mock
	private PlataformDAO plataformDAO;
	private PlataformController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new PlataformController(this.plataformDAO);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterPlataform() {
		Plataform plataform = Given.plataform("ANDROID");
		
		this.controller.create(plataform);
		
		Mockito.verify(this.plataformDAO).save(plataform);
	}
	
	@Test
	public void shouldUpdatePlataform() {
		Plataform plataform = Given.plataform("ANDROID");
		
		this.controller.update(plataform);
		
		Mockito.verify(this.plataformDAO).update(plataform);
	}

	@Test
	public void shouldDeletePlataform() {
		givenThereIsAPlataformRegistered(1L, "ANDROID");

		Plataform u = this.plataformDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.plataformDAO).remove(u);
	}
	
	private void givenThereIsAPlataformRegistered(Long id, String name) {
		Plataform plataform = Given.plataform(name);

		Mockito.when(this.plataformDAO.load(id)).thenReturn(plataform);
	}
}