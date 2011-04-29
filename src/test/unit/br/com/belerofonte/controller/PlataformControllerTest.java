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
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class PlataformControllerTest {

	@Mock
	private PlataformDAO plataformDAO;
	private PlataformController controller;
	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new PlataformController(this.plataformDAO, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterPlataform() {
		Plataform plataform = Given.plataform(1L,"ANDROID");
		
		this.controller.create(plataform);
		
		Mockito.verify(this.plataformDAO).save(plataform);
	}
	
	@Test
	public void shouldUpdatePlataform() {
		Plataform plataform = Given.plataform(1L,"ANDROID");
		
		this.controller.update(plataform);
		
		Mockito.verify(this.plataformDAO).update(plataform);
	}

	@Test
	public void shouldDeletePlataform() {
		Plataform u = this.plataformDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.plataformDAO).remove(u);
	}
}