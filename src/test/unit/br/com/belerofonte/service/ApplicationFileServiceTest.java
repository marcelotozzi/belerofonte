package br.com.belerofonte.service;

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

public class ApplicationFileServiceTest {
	
	private ApplicationFileService service;
	private UploadedFile uploadedFile;
	@Mock
	private ApplicationFileDAO applicationFileDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.service = new ApplicationFileService(this.applicationFileDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreate() {
		ApplicationFile appFile = Given.applicationFile();
		
		this.service.create(uploadedFile, appFile);
		
		Mockito.verify(this.applicationFileDAO).save(appFile);
	}
}