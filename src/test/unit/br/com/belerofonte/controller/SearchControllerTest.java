package unit.br.com.belerofonte.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.controller.SearchController;
import br.com.belerofonte.service.SearchService;
import br.com.caelum.vraptor.Result;

public class SearchControllerTest {
	private SearchController controller;
	@Mock
	private SearchService searchService;
	@Mock
	private Result result;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.controller = new SearchController(this.searchService, this.result);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldSearch() {
		String seek = "Name";
			
		this.controller.searchResult(seek);
		
		Mockito.verify(this.result).include("word",seek);
		Mockito.verify(this.result).include("files", this.searchService.search(seek));
	}
}