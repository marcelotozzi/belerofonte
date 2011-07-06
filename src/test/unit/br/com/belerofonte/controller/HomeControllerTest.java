package unit.br.com.belerofonte.controller;

import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.controller.HomeController;

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