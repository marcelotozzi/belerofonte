package br.com.belerofonte.components;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.belerofonte.controller.AccountController;
import br.com.belerofonte.controller.AdminController;
import br.com.belerofonte.controller.CategoryController;
import br.com.belerofonte.controller.FileController;
import br.com.belerofonte.controller.HomeController;
import br.com.belerofonte.controller.PlataformController;
import br.com.belerofonte.controller.UserController;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.model.Plataform;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.validator.ValidationMessage;

public class AccessInterceptorTest {
	private AccessInterceptor interceptor;
	private Result result;
	@Mock
	private Account account;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.interceptor = new AccessInterceptor(this.account, this.result);
	}

	@Test
	public void shouldNotInterceptTheFormMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptTheAuthenticatesMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class, AccountController.class
						.getDeclaredMethod("authenticates", User.class))));
	}

	@Test
	public void shouldInterceptTheLogoffMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("logoff"))));
	}

	@Test
	public void shouldInterceptTheAccountMethodAccountController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AccountController.class,
						AccountController.class.getDeclaredMethod("account"))));
	}

	@Test
	public void shouldInterceptTheAdminMethodAdminController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(AdminController.class,
						AdminController.class.getDeclaredMethod("admin"))));
	}

	@Test
	public void shouldInterceptCreateMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod.instanceFor(
				CategoryController.class, CategoryController.class
						.getDeclaredMethod("create", ApplicationCategory.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod.instanceFor(
				CategoryController.class, CategoryController.class
						.getDeclaredMethod("update", ApplicationCategory.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class, CategoryController.class
						.getDeclaredMethod("delete", Long.class))));
	}

	@Test
	public void shouldNotInterceptShowMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class, CategoryController.class
						.getDeclaredMethod("show", Long.class))));
	}

	@Test
	public void shouldInterceptEditMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class, CategoryController.class
						.getDeclaredMethod("edit", Long.class))));
	}

	@Test
	public void shouldInterceptFormMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class,
						CategoryController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptCategoriesMethodCategoryController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(CategoryController.class, CategoryController.class
						.getDeclaredMethod("categories"))));
	}

	@Test
	public void shouldInterceptTheCreateMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("create", UploadedFile.class,
								ApplicationFile.class))));
	}

	@Test
	public void shouldInterceptTheDeleteMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("delete", Long.class))));
	}

	@Test
	public void shouldInterceptTheUpdateMethodFileController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(FileController.class, FileController.class
						.getDeclaredMethod("update", ApplicationFile.class))));
	}

	@Test
	public void shouldNotInterceptTheHomeMethodHomeController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(HomeController.class,
						HomeController.class.getDeclaredMethod("home"))));
	}

	@Test
	public void shouldInterceptCreateMethodPLataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("create",
								Plataform.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("update",
								Plataform.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("delete",
								Long.class))));
	}

	@Test
	public void shouldNotInterceptShowMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("show",
								Long.class))));
	}

	@Test
	public void shouldInterceptEditMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("edit",
								Long.class))));
	}

	@Test
	public void shouldInterceptFormMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptPlataformsMethodPlataformController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(PlataformController.class,
						PlataformController.class
								.getDeclaredMethod("plataforms"))));
	}

	

	@Test
	public void shouldNotInterceptFormMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class,
						UserController.class.getDeclaredMethod("form"))));
	}

	@Test
	public void shouldNotInterceptCreateMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("create", User.class))));
	}

	@Test
	public void shouldInterceptUpdateMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("update", User.class))));
	}

	@Test
	public void shouldInterceptDeleteMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("delete", Long.class))));
	}

	@Test
	public void shouldNotInterceptShowMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertFalse(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("show", Long.class))));
	}

	@Test
	public void shouldInterceptEditMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("edit", Long.class))));
	}

	@Test
	public void shouldInterceptVerifyUsernameMethodUserController()
			throws SecurityException, NoSuchMethodException {
		Assert.assertTrue(this.interceptor.accepts(DefaultResourceMethod
				.instanceFor(UserController.class, UserController.class
						.getDeclaredMethod("verifyUsername", String.class))));
	}
	
	@Test
	public void shouldRedirectToLogin(){
		Mockito.when(this.account.isLogged()).thenReturn(false);
		
		this.interceptor.intercept(null, null, null);
		
		@SuppressWarnings("unchecked")
		List<ValidationMessage> errors = (List<ValidationMessage>) this.result.included().get("errors");
		for (ValidationMessage erro : errors) {
			Assert.assertEquals("Faça o Login para acessar sua conta.", erro.getMessage());
		}
	}
}