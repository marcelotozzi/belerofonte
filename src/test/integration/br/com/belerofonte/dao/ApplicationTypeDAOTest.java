package br.com.belerofonte.dao;

import junit.framework.Assert;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.InvalidStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.model.ApplicationType;

public class ApplicationTypeDAOTest extends DaoTest {

	private Session s;
	private Transaction tx;
	private ApplicationTypeDAO applicationTypeDAO;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		this.applicationTypeDAO =  new ApplicationTypeDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}
	
	@Test
	public void shouldFindByName() {
		this.applicationTypeDAO.save(Given.type(null, "Type"));

		ApplicationType applicationType = this.applicationTypeDAO.findByName("Type");

		Assert.assertEquals("Type", applicationType.getName());
	}

	@Test
	public void shouldNotFindByInvalidName() {
		ApplicationType applicationType = this.applicationTypeDAO.findByName("AçãoInvalido");

		Assert.assertNull(applicationType);
	}

	@Test
	public void shouldLoadApplicationType() {
		this.applicationTypeDAO.save(Given.type(null, "Ação"));

		ApplicationType applicationTypeByName = this.applicationTypeDAO.findByName("Ação");
		ApplicationType applicationTypeByLoad = this.applicationTypeDAO.load(applicationTypeByName.getId());

		Assert.assertEquals(applicationTypeByName.getId(), applicationTypeByLoad.getId());
		Assert.assertEquals(applicationTypeByName.getName(), applicationTypeByLoad.getName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidApplicationType() {
		ApplicationType applicationTypeByLoad = this.applicationTypeDAO.load(10000L);

		Assert.assertNull(applicationTypeByLoad);
	}

	@Test
	public void shouldSaveApplicationType() {
		this.applicationTypeDAO.save(Given.type(null, "Ação"));

		ApplicationType applicationType = this.applicationTypeDAO.findByName("Ação");

		Assert.assertEquals("Ação", applicationType.getName());
	}
	
	@Test(expected=PropertyValueException.class)
	public void shouldNotRegisterWithNameNullApplicationType(){
		this.applicationTypeDAO.save(Given.type(null, null));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithNameEmptyApplicationType(){
		this.applicationTypeDAO.save(Given.type(null, ""));
	}
	
	@Test
	public void shouldUpdateApplicationType() {
		this.applicationTypeDAO.save(Given.type(null, "Ação"));

		ApplicationType p = this.applicationTypeDAO.findByName("Ação");
		p.setName("Aventura");

		this.applicationTypeDAO.update(p);

		ApplicationType applicationType = this.applicationTypeDAO.findByName("Aventura");

		Assert.assertEquals("Aventura", applicationType.getName());
	}

	@Test
	public void shouldRemove() {
		this.applicationTypeDAO.save(Given.type(null, "Ação"));

		ApplicationType applicationType = this.applicationTypeDAO.findByName("Ação");

		this.applicationTypeDAO.remove(applicationType);

		ApplicationType app = this.applicationTypeDAO.findByName("Ação");

		Assert.assertNull(app);
	}
}