package br.com.belerofonte.dao;

import junit.framework.Assert;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.InvalidStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.model.Plataform;

public class PlataformDAOTest extends DaoTest {

	private PlataformDAO plataformDAO;
	private Session s;
	private Transaction tx;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		this.plataformDAO =  new PlataformDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldFindByName() {
		this.plataformDAO.save(Given.plataform(1L,"Plataform"));

		Plataform plataform = this.plataformDAO.findByName("Plataform");

		Assert.assertEquals("Plataform", plataform.getName());
	}

	@Test
	public void shouldNotFindByInvalidName() {
		Plataform plataform = this.plataformDAO.findByName("InvalidPlataform");

		Assert.assertNull(plataform);
	}

	@Test
	public void shouldLoadPlatafom() {
		this.plataformDAO.save(Given.plataform(1L,"Plataform"));

		Plataform plataformByName = this.plataformDAO.findByName("Plataform");
		Plataform plataformByLoad = this.plataformDAO.load(plataformByName.getId());

		Assert.assertEquals(plataformByName.getId(), plataformByLoad.getId());
		Assert.assertEquals(plataformByName.getName(), plataformByLoad.getName());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidPlataform() {
		Plataform plataformByLoad = this.plataformDAO.load(10000L);

		Assert.assertNull(plataformByLoad);
	}

	@Test
	public void shouldSavePlataform() {
		this.plataformDAO.save(Given.plataform(1L,"Plataform"));

		Plataform plataform = this.plataformDAO.findByName("Plataform");

		Assert.assertEquals("Plataform", plataform.getName());
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotSaveWithNameEmptyPlataform(){
		this.plataformDAO.save(Given.plataform(null, ""));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotSaveWithNameNullPlataform(){
		this.plataformDAO.save(Given.plataform(null, null));
	}

	@Test
	public void shouldUpdatePlataform() {
		this.plataformDAO.save(Given.plataform(1L,"Plataform"));

		Plataform p = this.plataformDAO.findByName("Plataform");
		p.setName("Plataform2");

		this.plataformDAO.update(p);

		Plataform plataform = this.plataformDAO.findByName("Plataform2");

		Assert.assertEquals("Plataform2", plataform.getName());
	}

	@Test
	public void shouldRemove() {
		this.plataformDAO.save(Given.plataform(1L,"Plataform"));

		Plataform plataform = this.plataformDAO.findByName("Plataform");

		this.plataformDAO.remove(plataform);

		Plataform u = this.plataformDAO.findByName("Plataform");

		Assert.assertNull(u);
	}
}