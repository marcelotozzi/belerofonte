package br.com.belerofonte.controller;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.infra.Downloader;
import br.com.belerofonte.infra.PropertiesLoader;
import br.com.belerofonte.infra.Uploader;
import br.com.belerofonte.model.User;
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class FileControllerDownloadTest {
 
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	private FileController controller;
	private Result result;
	private FileService applicationFileService;
	private PropertiesLoader proprertiesLoader;
	private Account account;
	private Validator validator;
	@Mock
	private Uploader uploader;
	@Mock
	private Downloader downloader;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.validator = new MockValidator();
		this.account = new Account();
		this.account.performLogin(new User());
		this.proprertiesLoader = new PropertiesLoader();
		this.applicationFileService = new FileService(this.applicationFileDAO, this.proprertiesLoader, 
				this.account, this.uploader, this.downloader);
		this.controller = new FileController(this.applicationFileDAO, this.applicationFileService, this.result, this.validator);
	}

	@Test
	public void shouldDownloadFile(){		
		Mockito.when(this.applicationFileDAO.load(20))
		.thenReturn(Given.file(null, "Name", "image.jpg", "description", "image/jpg", 0L, 1343L, 
				Calendar.getInstance(), 
				Given.category(null, "Category"), 
				Given.plataform(null, "Plataform"),
				Given.user(null, "Name", "username", "email@email.com", "password", "password")));
		
		Download down = this.controller.downloadFile(20L);
		
		Assert.assertNotNull(down);
		Mockito.verify(this.applicationFileDAO).load(20L);
	}
}
