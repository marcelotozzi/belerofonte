package br.com.belerofonte.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

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
import br.com.belerofonte.service.FileService;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.util.test.MockResult;

public class FileControllerTest {

	private FileController controller;

	@Mock
	private FileService applicationFileService;

	@Mock
	private ApplicationFileDAO applicationFileDAO;

	private UploadedFile uploadFile;
	@Mock
	private PropertiesLoader proprertiesLoader;

	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.uploadFile = new UploadedFileTest();
		this.result = new MockResult();
		this.applicationFileService = new FileService(this.applicationFileDAO, this.proprertiesLoader);
		this.controller = new FileController(this.applicationFileDAO, this.applicationFileService, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateApplicationFile() throws FileNotFoundException, IOException {
		ApplicationFile appFile = Given.file(null, null, null, null, null, null, null, null, null, null, null);
		
		Mockito.when(proprertiesLoader.getValue("folderFiles")).thenReturn("/Users/marcelotozzi/Desktop/uploaded/");
		
		this.controller.create(this.uploadFile, appFile);

		Mockito.verify(this.applicationFileDAO).save(appFile);
	}

	@Test
	public void shouldUpdateApplicationFile() {
		ApplicationFile appFile = Given.file(null, null, null, null, null, null, null, null, null, null, null);

		this.controller.update(appFile);

		Mockito.verify(this.applicationFileDAO).update(appFile);
	}

	@Test
	public void shouldDeleteApplicationFile() {
		ApplicationFile appFile = this.applicationFileDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.applicationFileDAO).delete(appFile);
	}
	
	@Test
	public void shouldReturnRecentApplicationsJson(){
		//this.controller.recentApplicationsJson();
		Assert.fail("N�o implementado");
	}
	
	@Test
	public void shouldReturnTopDownloadsJson(){
		//this.controller.topDownloadsJson();
		Assert.fail("N�o implementado");
	}
}