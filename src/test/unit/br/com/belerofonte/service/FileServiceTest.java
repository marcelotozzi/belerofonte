package br.com.belerofonte.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.components.PropertiesLoader;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class FileServiceTest {
	
	private FileService service;
	private UploadedFile uploadedFile;
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	@Mock 
	private PropertiesLoader proprertiesLoader;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.uploadedFile = new UploadedFileTest();
		this.service = new FileService(this.applicationFileDAO, this.proprertiesLoader);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreate() {
		ApplicationFile appFile = Given.file(null, null, null, null, null, null, null, null, null, null);
		
		Mockito.when(proprertiesLoader.getValue("folderFiles")).thenReturn("/Users/marcelotozzi/Desktop/uploaded/");
		
		this.service.create(uploadedFile, appFile);
		
		Mockito.verify(this.applicationFileDAO).save(appFile);
	}
}