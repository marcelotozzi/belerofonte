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
import br.com.belerofonte.controller.CategoryController;
import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class CategoryControllerJsonTest {
	private CategoryController controller;
	@Mock private ApplicationCategoryDAO categoryDAO;
	private MockSerializationResult result;
	private Validator validator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockSerializationResult();
		this.validator = new MockValidator();
		this.controller = new CategoryController(this.categoryDAO, this.result, this.validator);
	}
	
	@Test
	public void shouldReturnJsonWithCategories() throws Exception {
		ApplicationCategory category = Given.category(1l, "Category");
		
		String expectedResult = "{\"categories\": [{\"id\": 1,\"name\": \"Category\"}]}";
		
		Mockito.when(this.categoryDAO.list()).thenReturn(fileListingWillContain(category));
		
		this.controller.categoriesJson();

		Assert.assertThat(this.result.serializedResult(), is(equalTo(expectedResult)));
	}

	private List<ApplicationCategory> fileListingWillContain(ApplicationCategory category) {
			return Arrays.asList(category);
	}
}