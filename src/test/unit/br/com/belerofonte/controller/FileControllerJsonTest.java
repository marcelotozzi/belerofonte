package unit.br.com.belerofonte.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
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
import br.com.belerofonte.controller.FileController;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class FileControllerJsonTest {
	private FileController controller;
	private MockSerializationResult result;
	@Mock
	private ApplicationFileDAO applicationFileDAO;
	@Mock
	private FileService service;
	private Validator validator;

	@Before
	public void setup() throws IOException {
		this.result = new MockSerializationResult();
		this.validator = new MockValidator();
		MockitoAnnotations.initMocks(this);
		this.controller = new FileController(this.applicationFileDAO,this.service, this.result, this.validator);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void shouldReturnJsonWithTopDownloads() throws Exception {
		ApplicationFile file = Given.file(1L, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, null, Given.category(1l, "Category"), Given.plataform(1l, "Plataform"), 
				Given.user(1l, "Name", "username", "email@email.com", "password", "password"));
		
		String expectedResult = "{\"topDownloads\": [{\"id\": 1,\"name\": \"Name\",\"nameOfFile\": " +
				"\"nameOfFile.file\",\"description\": \"Description\",\"sizeOfFile\": 13134,\"contentType\": " +
				"\"contentType\",\"plataform\": {\"id\": 1,\"name\": \"Plataform\"},\"applicationCategory\": " +
				"{\"id\": 1,\"name\": \"Category\"},\"numberOfDownloads\": 0,\"user\": {\"id\": 1,\"name\": " +
				"\"Name\",\"username\": \"username\",\"email\": \"email@email.com\",\"password\": " +
				"\"password\",\"confirmPassword\": \"password\"}}]}";
		
		Mockito.when(this.applicationFileDAO.topDownloads(10)).thenReturn(fileListingWillContain(file));
		
		this.controller.topDownloadsJson();	
			
		Assert.assertThat(this.result.serializedResult(), is(equalTo(expectedResult)));
	}

	private List<ApplicationFile> fileListingWillContain(final ApplicationFile file) {
		return Arrays.asList(file);
	}

	@Test
	public void shouldReturnJsonWithRecentApplications() throws Exception {
		ApplicationFile file = Given.file(1L, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, null, Given.category(1l, "Category"), Given.plataform(1l, "Plataform"), 
				Given.user(1l, "Name", "username", "email@email.com", "password", "password"));
		
		String expectedResult = "{\"recentApps\": [{\"id\": 1,\"name\": \"Name\",\"nameOfFile\": " +
				"\"nameOfFile.file\",\"description\": \"Description\",\"sizeOfFile\": 13134,\"contentType\": " +
				"\"contentType\",\"plataform\": {\"id\": 1,\"name\": \"Plataform\"},\"applicationCategory\": " +
				"{\"id\": 1,\"name\": \"Category\"},\"numberOfDownloads\": 0,\"user\": {\"id\": 1,\"name\": " +
				"\"Name\",\"username\": \"username\",\"email\": \"email@email.com\",\"password\": " +
				"\"password\",\"confirmPassword\": \"password\"}}]}";
		
		Mockito.when(this.applicationFileDAO.recentApplications(10)).thenReturn(fileListingWillContain(file));
		
		this.controller.recentApplicationsJson();
		
		Assert.assertThat(this.result.serializedResult(), is(equalTo(expectedResult)));
	}
}