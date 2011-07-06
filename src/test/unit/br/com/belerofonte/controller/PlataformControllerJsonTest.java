package unit.br.com.belerofonte.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import unit.br.com.belerofonte.common.Given;
import br.com.belerofonte.controller.PlataformController;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class PlataformControllerJsonTest {
	
	@Mock
	private PlataformDAO plataformDAO;
	private PlataformController controller;
	private MockSerializationResult result;
	private Validator validator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockSerializationResult();
		this.validator = new MockValidator();
		this.controller = new PlataformController(this.plataformDAO, this.result, this.validator);
	}
	
	@Test
	public void shouldReturnJsonWithPlataforms() throws Exception {
		Plataform plataform = Given.plataform(1l, "Plataform");
		
		String expectedResult = "{\"plataforms\": [{\"id\": 1,\"name\": \"Plataform\"}]}";
		
		Mockito.when(this.plataformDAO.list()).thenReturn(fileListingWillContain(plataform));
		
		this.controller.plataformsJson();

		Assert.assertThat(this.result.serializedResult(), is(equalTo(expectedResult)));
	}
	
	private List<Plataform> fileListingWillContain(final Plataform plataform) {
		return Arrays.asList(plataform);
	}
}