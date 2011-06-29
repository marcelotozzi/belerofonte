package br.com.belerofonte.model;


import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationCategoryTest {

	private ApplicationCategory category;

	@Before
	public void setUp() throws Exception {
		this.category = new ApplicationCategory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetFiles() {
		this.category.setFiles(Arrays.asList(new ApplicationFile()));

		Assert.assertEquals(1, this.category.getFiles().size());
	}
}
