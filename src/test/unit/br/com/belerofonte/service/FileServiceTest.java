package br.com.belerofonte.service;

import java.io.File;

import org.junit.After;
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
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class FileServiceTest {
	
	private FileService service;
	private UploadedFile uploadedFile;
	private PropertiesLoader proprertiesLoader;
	private Account account;
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	private Uploader uploader;
	@Mock
	private Downloader downloader;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.uploadedFile = new UploadedFileTest();
		this.proprertiesLoader = new PropertiesLoader();
		this.account = new Account();
		this.uploader = new Uploader(this.proprertiesLoader);
		this.account.performLogin(Given.user(null, "Usernam", "username", "email@email.com", "password", "password"));
		this.service =  new FileService(this.applicationFileDAO, this.account, this.uploader, this.downloader);
	}

	@After
	public void tearDown() throws Exception {
		StringBuilder dir  = new StringBuilder(this.proprertiesLoader.getValue("folderFiles"));
		dir.append(this.account.getUser().getUsername());
		File path = new File(dir.toString());
		Given.deleteDir(path);  
	}

	@Test
	public void shouldCreate() {
		ApplicationFile appFile = new ApplicationFile();
		
		this.service.create(uploadedFile, appFile);
		
		Mockito.verify(this.applicationFileDAO).save(appFile);
	}
}