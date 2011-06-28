package br.com.belerofonte.controller;


import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.model.ApplicationType;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class TypeControllerTest {

	private TypeController controller;
	@Mock private ApplicationTypeDAO applicationTypeDAO;
	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new TypeController(this.applicationTypeDAO, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterApplicationType() {
		ApplicationType applicationType = Given.type(null, "Ação");

		this.controller.create(applicationType);

		Mockito.verify(this.applicationTypeDAO).save(applicationType);
	}
	
	@Test
	public void shouldUpdateApplicationType() {
		ApplicationType applicationType = Given.type(null, "Ação");
		
		this.controller.update(applicationType);

		Mockito.verify(this.applicationTypeDAO).update(applicationType);
	}

	@Test
	public void shouldDeleteApplicationType() {
		ApplicationType applicationType = this.applicationTypeDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.applicationTypeDAO).remove(applicationType);
	}
	
	@Test
	public void shouldCallFormMethod(){
		this.controller.form();
	}
	
	@Test
	public void shouldCallTypesMethodAndReturnListInResult(){
		this.controller.types();
		@SuppressWarnings("unchecked")
		List<ApplicationType> objects = (List<ApplicationType>) this.result.included().get("types");
		
		Assert.assertNotNull(objects);
	}
	
	@Test
	public void shouldCallShowMethodAndReturnApplicationTypeInResult(){
		Mockito.when(this.applicationTypeDAO.load(1L)).thenReturn(new ApplicationType());

		this.controller.show(1L);
		
		ApplicationType type = (ApplicationType) this.result.included().get("type");
		Assert.assertNotNull(type);
	}
	
	@Test
	public void shouldCallEditMethodAndReturnApplicationTypeInResult(){
		Mockito.when(this.applicationTypeDAO.load(1L)).thenReturn(new ApplicationType());

		this.controller.edit(1L);
		
		ApplicationType type = (ApplicationType) this.result.included().get("type");
		Assert.assertNotNull(type);
	}
	
	@Test
	public void shouldReturnJsonWithTypes() {
		this.controller.applicationTypesJson();
		fail("Nao implementado");
	}
}