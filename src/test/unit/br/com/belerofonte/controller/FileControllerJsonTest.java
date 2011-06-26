package br.com.belerofonte.controller;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class FileControllerJsonTest {
	private FileController controller;
	private Result result;
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	@Mock
	private FileService service;

	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new FileController(this.applicationFileDAO,this.service, this.result);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void shouldReturnJsonWithTopDownloads() {
		this.controller.topDownloadsJson();
		fail("Nao implementado");
	}

	@Test
	public void shouldReturnJsonWithRecentApplications() {
		fail("Nao implementado");
	}
}