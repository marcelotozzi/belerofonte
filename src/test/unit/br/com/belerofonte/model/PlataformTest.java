package br.com.belerofonte.model;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlataformTest {

	private Plataform plataform;

	@Before
	public void setUp() throws Exception {
		this.plataform = new Plataform();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetFiles() {
		this.plataform.setFiles(Arrays.asList(new ApplicationFile()));

		Assert.assertEquals(1, this.plataform.getFiles().size());
	}
}
