package br.com.belerofonte.model;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;

	@Before
	public void setUp() throws Exception {
		this.user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetFiles() {
		this.user.setFiles(Arrays.asList(new ApplicationFile()));

		Assert.assertEquals(1, this.user.getFiles().size());
	}
}
