package br.com.belerofonte.controller;


import org.junit.After;
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

public class ApplicationTypeControllerTest {

	private ApplicationTypeController controller;
	@Mock private ApplicationTypeDAO applicationTypeDAO;
	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new ApplicationTypeController(this.applicationTypeDAO, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldRegisterApplicationType() {
		ApplicationType applicationType = Given.applicationType(null, "Ação");

		this.controller.create(applicationType);

		Mockito.verify(this.applicationTypeDAO).save(applicationType);
	}
	
	@Test
	public void shouldUpdateApplicationType() {
		ApplicationType applicationType = Given.applicationType(null, "Ação");
		
		this.controller.update(applicationType);

		Mockito.verify(this.applicationTypeDAO).update(applicationType);
	}

	@Test
	public void shouldDeleteApplicationType() {
		ApplicationType applicationType = this.applicationTypeDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.applicationTypeDAO).remove(applicationType);
	}
}