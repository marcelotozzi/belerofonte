package br.com.belerofonte.controller;

import java.io.File;
import java.util.Calendar;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.components.Account;
import br.com.belerofonte.components.PropertiesLoader;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.FileService;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.util.test.MockResult;

public class FileControllerIntegrationTest extends DaoTest {

	private FileController controller;
	private UploadedFile uploadFile;
	private FileService fileService;
	private ApplicationFileDAO fileDAO;
	private Session s;
	private Transaction tx;
	private PropertiesLoader propertyLoader;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.uploadFile = new UploadedFileTest();

		s = getSession();
		tx = s.beginTransaction();
		Given.setSession(s);
		this.fileDAO = new ApplicationFileDAO(this.s);
		this.propertyLoader = new PropertiesLoader();
		this.result = new MockResult();
		this.account = new Account();
		this.account.performLogin(Given.userPersisted(null, "Name", "username", "name@email.com", "password", "password"));
		this.fileService = new FileService(this.fileDAO, this.propertyLoader, this.account);
		this.controller = new FileController(this.fileDAO, this.fileService, this.result);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldCreateAndUploadFile() {
		ApplicationFile appFile = Given.file(null, "Name", "nameOfFile.file", "Description",
				"contentType", 1L, 1212L, Calendar.getInstance(),
				Given.categoryPersisted(null, "Category"),
				Given.plataformPersisted(null, "Plataform"),
				Given.userPersisted(null, "Name", "username", "email@email.com", "password", "password"));
		
		this.controller.create(this.uploadFile, appFile);

		File file = new File(this.propertyLoader.getValue("folderFiles")
				+this.account.getUser().getUsername()
				+this.propertyLoader.getValue("appFolder")+"/image.jpg");
		Download fileUploaded = new FileDownload(file, "image/jpeg");

		Assert.assertNotNull(fileUploaded);
		Assert.assertNotNull(file);
		Assert.assertEquals("image.jpg", file.getName());
	}
	
	@Test
	public void shouldDownloadFile(){
		Given.filePersisted(null, "Imagem", "image.jpg", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Plataform", "username");

		Download down = this.controller.downloadFile(this.fileDAO.findByName("Imagem").getId());
			
		Assert.assertNotNull(down);
	}
}