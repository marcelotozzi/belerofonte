package unit.br.com.belerofonte.controller;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import unit.br.com.belerofonte.common.Given;
import br.com.belerofonte.controller.PlataformController;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class PlataformControllerTest {

	@Mock
	private PlataformDAO plataformDAO;
	private PlataformController controller;
	private Result result;
	private Validator validator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.validator = new MockValidator();
		this.controller = new PlataformController(this.plataformDAO, this.result, this.validator);
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
	
	@Test
	public void shouldCallFormMethod(){
		this.controller.form();
	}
	
	@Test
	public void shouldCallPlataformsMethodAndReturnListInResult(){
		this.controller.plataforms();
		@SuppressWarnings("unchecked")
		List<Plataform> objects = (List<Plataform>) this.result.included().get("plataforms");
		
		Assert.assertNotNull(objects);
	}
	
	@Test
	public void shouldCallShowMethodAndReturnPlataformInResult(){
		Mockito.when(this.plataformDAO.load(1L)).thenReturn(new Plataform());

		this.controller.show(1L);
		
		Plataform plataform = (Plataform) this.result.included().get("plataform");
		Assert.assertNotNull(plataform);
	}
	
	@Test
	public void shouldCallEditMethodAndReturnPlataformInResult(){
		Mockito.when(this.plataformDAO.load(1L)).thenReturn(new Plataform());

		this.controller.edit(1L);
		
		Plataform plataform = (Plataform) this.result.included().get("plataform");
		Assert.assertNotNull(plataform);
	}
}