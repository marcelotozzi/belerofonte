package unit.br.com.belerofonte.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.components.FileSearcher;
import br.com.belerofonte.service.SearchService;

public class SearchServiceTest {
	
	private SearchService searchService;
	@Mock
	private FileSearcher fileSearcher;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.searchService = new SearchService(this.fileSearcher);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldSearch(){
		String text = "application";
		this.searchService.search(text);
		
		Mockito.verify(this.fileSearcher).fullText(text);
	}
}