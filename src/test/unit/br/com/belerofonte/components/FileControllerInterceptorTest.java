package br.com.belerofonte.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.FileController;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;

public class FileControllerInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	private Account account;

	@Before
	public void setUp() throws Exception {
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldInterceptTheCreateMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("create", UploadedFile.class,
								ApplicationFile.class))));
	}

	@Test
	public void shouldInterceptTheDeleteMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("delete", Long.class))));
	}

	@Test
	public void shouldInterceptTheUpdateMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("update", ApplicationFile.class))));
	}
}