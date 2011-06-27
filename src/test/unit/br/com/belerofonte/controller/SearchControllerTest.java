package br.com.belerofonte.controller;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.service.SearchService;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

public class SearchControllerTest {

	private SearchController controller;
	private Result result;
	@Mock
	private SearchService searchService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new SearchController(searchService, result);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void shouldSearch() {
		this.controller.textSearch("twitter");
		fail("Nao implementado");
	}
}