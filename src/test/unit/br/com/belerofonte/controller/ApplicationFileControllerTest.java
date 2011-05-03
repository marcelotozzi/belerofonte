package br.com.belerofonte.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.model.ApplicationType;
import br.com.belerofonte.service.ApplicationFileService;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class ApplicationFileControllerTest {

	private ApplicationFileController controller;
	private ApplicationFileService applicationFileService;

	@Mock
	private ApplicationFileDAO applicationFileDAO;
	@Mock
	private UploadedFile uploadFile;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.applicationFileService = new ApplicationFileService(this.applicationFileDAO);
		this.controller = new ApplicationFileController(this.applicationFileDAO, this.applicationFileService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateApplicationFile() {
		ApplicationFile appFile = Given.applicationFile();
		
		this.controller.create(this.uploadFile, appFile);

		Mockito.verify(this.applicationFileDAO).save(appFile);
	}
	
	@Test
	public void shouldUpdateApplicationFile() {
		ApplicationFile appFile = Given.applicationFile();
		
		this.controller.update(appFile);

		Mockito.verify(this.applicationFileDAO).update(appFile);
	}
	
	@Test
	public void shouldDeleteApplicationFile() {
		ApplicationFile appFile = this.applicationFileDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.applicationFileDAO).delete(appFile);
	}
}