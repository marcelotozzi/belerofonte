package br.com.belerofonte.controller;

import java.io.File;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.components.PropertiesLoader;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.FileService;
import br.com.belerofonte.util.BeforeDataIntegration;
import br.com.belerofonte.util.UploadedFileTest;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class FileControllerUploadTest extends DaoTest {

	private FileController controller;
	private UploadedFile uploadFile;
	private FileService fileService;
	private ApplicationFileDAO fileDAO;
	private Session s;
	private Transaction tx;
	private PlataformDAO plataformDAO;
	private ApplicationTypeDAO typeDAO;
	private PropertiesLoader propertyLoader;

	@Before
	public void setUp() throws Exception {
		this.uploadFile = new UploadedFileTest();

		s = getSession();
		tx = s.beginTransaction();
		BeforeDataIntegration.main(s);
		this.fileDAO = new ApplicationFileDAO(this.s);
		this.plataformDAO = new PlataformDAO(this.s);
		this.typeDAO = new ApplicationTypeDAO(this.s);
		this.propertyLoader  = new PropertiesLoader();
		this.fileService = new FileService(this.fileDAO, this.propertyLoader);
		this.controller = new FileController(this.fileDAO, this.fileService);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldCreateAndUploadFile() {
		ApplicationFile appFile = Given.applicationFile(null, "image",
				null, "description", 0L, null,
				this.plataformDAO.findByName("iOS"),
				this.typeDAO.findByName("Imagem"), null, 0L);

		this.controller.create(this.uploadFile, appFile);
		
		File file = new File("files/image.jpg");
		Download fileUploaded = new FileDownload(file, "image/jpeg");
		
		Assert.assertNotNull(fileUploaded);
		Assert.assertNotNull(file);
		Assert.assertEquals("image.jpg", file.getName());
	}
}