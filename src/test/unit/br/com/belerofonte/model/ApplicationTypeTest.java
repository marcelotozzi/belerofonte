package br.com.belerofonte.model;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTypeTest {

	private ApplicationType appType;

	@Before
	public void setUp() throws Exception {
		this.appType = new ApplicationType();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetId() {
		this.appType.setId(1L);
		Assert.assertEquals(1L, this.appType.getId().longValue());
	}

	@Test
	public void testSetFiles() {
		this.appType.setFiles(Arrays.asList(new ApplicationFile()));

		Assert.assertEquals(1, this.appType.getFiles().size());
	}
}