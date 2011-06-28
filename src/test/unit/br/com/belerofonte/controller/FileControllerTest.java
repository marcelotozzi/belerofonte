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

import br.com.belerofonte.components.Account;
import br.com.belerofonte.components.PropertiesLoader;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.model.User;
import br.com.belerofonte.service.FileService;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.util.test.MockResult;

public class FileControllerTest {

	private FileController controller;
	private FileService applicationFileService;
	private UploadedFile uploadFile;
	private PropertiesLoader proprertiesLoader;
	private Result result;
	private Account account;
	@Mock
	private ApplicationFileDAO applicationFileDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.uploadFile = new UploadedFileTest();
		this.result = new MockResult();
		this.account = new Account();
		this.account.performLogin(new User());
		this.proprertiesLoader = new PropertiesLoader();
		this.applicationFileService = new FileService(this.applicationFileDAO, this.proprertiesLoader, this.account);
		this.controller = new FileController(this.applicationFileDAO, this.applicationFileService, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateApplicationFile() throws FileNotFoundException, IOException {
		ApplicationFile appFile = new ApplicationFile();
			
		this.controller.create(this.uploadFile, appFile);

		Mockito.verify(this.applicationFileDAO).save(appFile);
	}

	@Test
	public void shouldUpdateApplicationFile() {
		ApplicationFile appFile = new ApplicationFile();

		this.controller.update(appFile);

		Mockito.verify(this.applicationFileDAO).update(appFile);
	}

	@Test
	public void shouldDeleteApplicationFile() {
		ApplicationFile appFile = this.applicationFileDAO.load(1L);

		this.controller.delete(1L);

		Mockito.verify(this.applicationFileDAO).remove(appFile);
	}
	
	@Test
	public void shouldCallFormMethod(){
		this.controller.form();
	}
	
	@Test
	public void shouldCallShowMethodAndReturnApplicationFileInResult(){
		Mockito.when(this.applicationFileDAO.load(1L)).thenReturn(new ApplicationFile());

		this.controller.show(1L);
		
		ApplicationFile file = (ApplicationFile) this.result.included().get("file");
		Assert.assertNotNull(file);
	}
}