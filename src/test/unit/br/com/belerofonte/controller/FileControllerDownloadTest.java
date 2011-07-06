package br.com.belerofonte.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import junit.framework.Assert;

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
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class FileControllerDownloadTest {

	@Mock
	private ApplicationFileDAO applicationFileDAO;
	private FileController controller;
	private Result result;
	private FileService applicationFileService;
	private Account account;
	private Validator validator;
	private Uploader uploader;
	private Downloader downloader;
	private PropertiesLoader proprertiesLoader;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.validator = new MockValidator();
		this.account = new Account();
		this.account.performLogin(Given.user(null, "Name", "username",
				"name@email.com", "password", "password"));
		this.proprertiesLoader = new PropertiesLoader();
		this.downloader = new Downloader(this.proprertiesLoader);
		this.uploader = new Uploader(this.proprertiesLoader);
		this.applicationFileService = new FileService(this.applicationFileDAO,
				this.account, this.uploader, this.downloader);
		this.controller = new FileController(this.applicationFileDAO,
				this.applicationFileService, this.result, this.validator);
	}

	@After
	public void tearDown() throws Exception {
		StringBuilder dir = new StringBuilder(
				this.proprertiesLoader.getValue("folderFiles"));
		dir.append(this.account.getUser().getUsername());
		File path = new File(dir.toString());
		Given.deleteDir(path);
	}

	@Test
	public void shouldDownloadFile() throws FileNotFoundException, IOException {
		ApplicationFile appFile = Given.file(20L, "Name", "Twitter.apk",
				"description", "application/octet-stream", 0L, 1343L, Calendar
						.getInstance(), Given.category(null, "Category"), Given
						.plataform(null, "Plataform"), Given.user(null, "Name",
						"username", "email@email.com", "password", "password"));

		givenFileUploaded(appFile);

		Mockito.when(this.applicationFileDAO.load(20)).thenReturn(appFile);

		Download down = this.controller.downloadFile(20L);

		Assert.assertNotNull(down);
		Mockito.verify(this.applicationFileDAO).load(20L);
	}

	private Download givenFileUploaded(ApplicationFile appFile)
			throws IOException {
		// TODO refatorar isso, seu porco
		String diretorio = proprertiesLoader.getValue("folderFiles")
				+ appFile.getUser().getUsername();
		if (!new File(diretorio).exists()) {
			new File(diretorio).mkdir();
		}

		diretorio = proprertiesLoader.getValue("folderFiles")
				+ appFile.getUser().getUsername()
				+ proprertiesLoader.getValue("appFolder");
		if (!new File(diretorio).exists()) {
			new File(diretorio).mkdir();
		}

		diretorio = proprertiesLoader.getValue("folderFiles")
				+ appFile.getUser().getUsername()
				+ proprertiesLoader.getValue("appFolder")
				+ appFile.getNameOfFile();
		
		File file = new File(diretorio);
		file.createNewFile();
		
		file.createNewFile();
		return new FileDownload(file, appFile.getContentType());
		// TODO refatorar isso, seu porco
	}
}