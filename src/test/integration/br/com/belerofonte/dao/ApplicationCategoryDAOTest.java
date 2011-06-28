package br.com.belerofonte.dao;


import java.util.List;

import junit.framework.Assert;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.InvalidStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.model.ApplicationCategory;

public class ApplicationCategoryDAOTest extends DaoTest{

	private Transaction tx;
	private ApplicationCategoryDAO applicationCategoryDAO;
	private Session s;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		Given.setSession(s);
		this.applicationCategoryDAO =  new ApplicationCategoryDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldFindByName() {
		this.applicationCategoryDAO.save(Given.category(null, "Category"));

		ApplicationCategory applicationCategory = this.applicationCategoryDAO.findByName("Category");

		Assert.assertEquals("Category", applicationCategory.getName());
	}

	@Test
	public void shouldNotFindByInvalidName() {
		ApplicationCategory applicationCategory = this.applicationCategoryDAO.findByName("CategoriaInvalido");

		Assert.assertNull(applicationCategory);
	}

	@Test
	public void shouldLoadApplicationCategory() {
		this.applicationCategoryDAO.save(Given.category(null, "Category"));

		ApplicationCategory applicationCategoryByName = this.applicationCategoryDAO.findByName("Category");
		ApplicationCategory applicationCategoryByLoad = this.applicationCategoryDAO.load(applicationCategoryByName.getId());

		Assert.assertEquals(applicationCategoryByName.getId(), applicationCategoryByLoad.getId());
		Assert.assertEquals(applicationCategoryByName.getName(), applicationCategoryByLoad.getName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidApplicationTCategory() {
		ApplicationCategory applicationCategoryByLoad = this.applicationCategoryDAO.load(10000L);

		Assert.assertNull(applicationCategoryByLoad);
	}

	@Test
	public void shouldSaveApplicationCategory() {
		this.applicationCategoryDAO.save(Given.category(null, "Category"));

		ApplicationCategory applicationCategory = this.applicationCategoryDAO.findByName("Category");

		Assert.assertEquals("Category", applicationCategory.getName());
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithNameNullApplicationCategory(){
		this.applicationCategoryDAO.save(Given.category(null, null));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithNameEmptyApplicationCategory(){
		this.applicationCategoryDAO.save(Given.category(null, ""));
	}
	
	@Test
	public void shouldUpdateApplicationCategory() {
		this.applicationCategoryDAO.save(Given.category(null, "Category2"));

		ApplicationCategory p = this.applicationCategoryDAO.findByName("Category2");
		p.setName("Category");

		this.applicationCategoryDAO.update(p);

		ApplicationCategory applicationCategory = this.applicationCategoryDAO.findByName("Category");

		Assert.assertEquals("Category", applicationCategory.getName());
	}

	@Test
	public void shouldRemove() {
		this.applicationCategoryDAO.save(Given.category(null, "Category"));

		ApplicationCategory applicationCategory = this.applicationCategoryDAO.findByName("Category");

		this.applicationCategoryDAO.remove(applicationCategory);

		ApplicationCategory app = this.applicationCategoryDAO.findByName("Category");

		Assert.assertNull(app);
	}
	
	@Test
	public void shouldList(){
		this.applicationCategoryDAO.save(Given.category(null, "Category1"));
		this.applicationCategoryDAO.save(Given.category(null, "Category2"));
		this.applicationCategoryDAO.save(Given.category(null, "Category3"));

		List<ApplicationCategory> categories = this.applicationCategoryDAO.list();
		
		Assert.assertEquals(3, categories.size());
	}
}
