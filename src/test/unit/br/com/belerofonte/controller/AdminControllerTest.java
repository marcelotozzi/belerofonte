package br.com.belerofonte.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminControllerTest {
	private AdminController controller;

	@Before
	public void setUp() throws Exception {
		this.controller = new AdminController();
	}

	@Test
	public void testAdmin() {
		this.controller.admin();
		Assert.assertNotNull(this.controller);
	}
}
