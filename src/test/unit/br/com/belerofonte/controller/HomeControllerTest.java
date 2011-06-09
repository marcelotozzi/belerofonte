package br.com.belerofonte.controller;

import org.junit.Before;
import org.junit.Test;

public class HomeControllerTest {

	private HomeController controller;

	@Before
	public void setUp() throws Exception {
		this.controller = new HomeController();
	}

	@Test
	public void testHome() {
		this.controller.home();
	}
}