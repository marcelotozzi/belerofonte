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
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class ApplicationFileControllerTest {

	private ApplicationFileController controller;
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	@Mock
	private UploadedFile uploadFile; 

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new ApplicationFileController(this.applicationFileDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldUploadFile() {
		ApplicationFile appFile = Given.applicationFile();
		this.controller.upload(this.uploadFile, appFile);

		Mockito.verify(this.applicationFileDAO).save(appFile);
	}

	@Test
	public void shouldDeleteFile() {
		ApplicationFile appFile = this.applicationFileDAO.load(1L);

		this.controller.deleteFile(1L);

		Mockito.verify(this.applicationFileDAO).delete(appFile);

	}
}
