package unit.br.com.belerofonte.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import unit.br.com.belerofonte.common.Given;
import br.com.belerofonte.controller.SearchController;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.SearchService;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class SearchControllerJsonTest {
	private SearchController controller;
	private MockSerializationResult result;
	@Mock
	private SearchService searchService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.result = new MockSerializationResult();
		this.controller = new SearchController(searchService, result);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void shouldSearchJson() throws Exception {
		ApplicationFile file = Given.file(1L, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, null, Given.category(1l, "Category"), Given.plataform(1l, "Plataform"), 
				Given.user(1l, "Name", "username", "email@email.com", "password", "password"));
		
		String seek = "Name";
		
		String expectedResult = "{\"files\": [{\"id\": 1,\"name\": \"Name\",\"nameOfFile\": " +
		"\"nameOfFile.file\",\"description\": \"Description\",\"sizeOfFile\": 13134,\"contentType\": " +
		"\"contentType\",\"plataform\": {\"id\": 1,\"name\": \"Plataform\"},\"applicationCategory\": " +
		"{\"id\": 1,\"name\": \"Category\"},\"numberOfDownloads\": 0,\"user\": {\"id\": 1,\"name\": " +
		"\"Name\",\"username\": \"username\",\"email\": \"email@email.com\",\"password\": " +
		"\"password\",\"confirmPassword\": \"password\"}}]}";
		
		Mockito.when(this.searchService.search(seek)).thenReturn(this.fileListingWillContain(file));
		
		this.controller.textSearchJson(seek);
		
		Assert.assertThat(this.result.serializedResult(), is(equalTo(expectedResult)));
	}
	
	private List<ApplicationFile> fileListingWillContain(final ApplicationFile file) {
		return Arrays.asList(file);
	}
}