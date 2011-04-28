package br.com.belerofonte.dao;

import junit.framework.Assert;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.model.Category;

public class CategoryDAOTest extends DaoTest {

	private Session s;
	private Transaction tx;
	private CategoryDAO categoryDAO;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		this.categoryDAO =  new CategoryDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}
	
	@Test
	public void shouldFindByName() {
		this.categoryDAO.save(Given.category("Ação"));

		Category category = this.categoryDAO.findByName("Ação");

		Assert.assertEquals("Ação", category.getName());
	}

	@Test
	public void shouldNotFindByInvalidName() {
		Category category = this.categoryDAO.findByName("AçãoInvalido");

		Assert.assertNull(category);
	}

	@Test
	public void shouldLoadCategory() {
		this.categoryDAO.save(Given.category("Ação"));

		Category categoryByName = this.categoryDAO.findByName("Ação");
		Category categoryByLoad = this.categoryDAO.load(categoryByName.getId());

		Assert.assertEquals(categoryByName.getId(), categoryByLoad.getId());
		Assert.assertEquals(categoryByName.getName(), categoryByLoad.getName());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidCategory() {
		Category categoryByLoad = this.categoryDAO.load(10000L);

		Assert.assertNull(categoryByLoad);
	}

	@Test
	public void shouldSaveCategory() {
		this.categoryDAO.save(Given.category("Ação"));

		Category category = this.categoryDAO.findByName("Ação");

		Assert.assertEquals("Ação", category.getName());
	}

	@Test
	public void shouldUpdateCategory() {
		this.categoryDAO.save(Given.category("Ação"));

		Category p = this.categoryDAO.findByName("Ação");
		p.setName("Aventura");

		this.categoryDAO.update(p);

		Category category = this.categoryDAO.findByName("Aventura");

		Assert.assertEquals("Aventura", category.getName());
	}

	@Test
	public void shouldRemove() {
		this.categoryDAO.save(Given.category("Ação"));

		Category category = this.categoryDAO.findByName("Ação");

		this.categoryDAO.remove(category);

		Category cat = this.categoryDAO.findByName("Ação");

		Assert.assertNull(cat);
	}
}