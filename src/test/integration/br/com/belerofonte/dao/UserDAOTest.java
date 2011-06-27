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
import br.com.belerofonte.model.User;

public class UserDAOTest extends DaoTest {

	private UserDAO userDAO;
	private Session s;
	private Transaction tx;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.getTransaction();
		tx.begin();
	
		this.userDAO = new UserDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldFindByUsername() {
		this.userDAO.save(Given.user(null, "Name", "shouldFind", "shouldFind@gmail.com", "senha", "senha"));

		User user = this.userDAO.findByUsername("shouldFind");

		Assert.assertEquals("shouldFind", user.getUsername());
	}

	@Test
	public void shouldNotFindByInvalidUsername() {
		User user = this.userDAO.findByUsername("InvalidUsername");

		Assert.assertNull(user);
	}

	@Test
	public void shouldLoadUser() {
		this.userDAO.save(Given.user(null, "LoadUser", "LoadUser", "LoadUser@gmail.com", "senha", "senha"));

		User userByUsername = this.userDAO.findByUsername("LoadUser");

		User userByLoad = this.userDAO.load(userByUsername.getId());

		Assert.assertEquals(userByUsername.getId(), userByLoad.getId());
		Assert.assertEquals(userByUsername.getUsername(), userByLoad.getUsername());
		Assert.assertEquals(userByUsername.getEmail(), userByLoad.getEmail());
		Assert.assertEquals(userByUsername.getPassword(), userByLoad.getPassword());
		Assert.assertEquals(userByUsername.getConfirmPassword(), userByLoad.getConfirmPassword());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidUser() {
		User userByLoad = this.userDAO.load(10000L);

		Assert.assertNull(userByLoad);
	}

	@Test
	public void shouldSaveUser() {
		this.userDAO.save(Given.user(null, "SaveUser", "SaveUser", "SaveUser@gmail.com", "senha", "senha"));

		User user = this.userDAO.findByUsername("SaveUser");

		Assert.assertEquals("SaveUser", user.getUsername());
		Assert.assertEquals("SaveUser@gmail.com", user.getEmail());
		Assert.assertEquals("senha", user.getPassword());
		Assert.assertEquals("senha", user.getConfirmPassword());
	}
	
	@Test(expected = InvalidStateException.class)
	public void shouldNotSaveUserWithNameNull() {
		this.userDAO.save(Given.user(null, null, "SaveUser", "SaveUser@gmail.com", "senha", "senha"));
	}
	
	@Test(expected = InvalidStateException.class)
	public void shouldNotSaveUserWithNameEmpty() {
		this.userDAO.save(Given.user(null, "", "SaveUser", "SaveUser@gmail.com", "senha", "senha"));
	}
	
	@Test(expected = InvalidStateException.class)
	public void shouldNotSaveUserWithUsernameNull() {
		this.userDAO.save(Given.user(null, "SaveUser", null, "SaveUser@gmail.com", "senha", "senha"));
	}
	
	@Test(expected = InvalidStateException.class)
	public void shouldNotSaveUserWithUsernameEmpty() {
		this.userDAO.save(Given.user(null, "SaveUser", "", "SaveUser@gmail.com", "senha", "senha"));
	}

	@Test(expected = InvalidStateException.class)
	public void shouldNotSaveUserWithAInvalidEmail() {
		this.userDAO.save(Given.user(null, "InvalidEmail", "InvalidEmail", "tozzigmailcom", "senha", "senha"));

		User user = this.userDAO.findByUsername("InvalidEmail");

		Assert.assertEquals("InvalidEmail", user.getUsername());
	}

	@Test
	public void shouldFindByUsernameAndPassword() {
		this.userDAO.save(Given.user(null, "UsernameAndPass", "UsernameAndPass", "tozzi@gmail.com", "senha", "senha"));

		User user = this.userDAO.findByUsernameAndPassword(this.userDAO.findByUsername("UsernameAndPass"));

		Assert.assertEquals("UsernameAndPass", user.getUsername());
		Assert.assertEquals("senha", user.getPassword());
	}

	@Test
	public void shouldNotFindByInvalidUsernameAndPassword() {
		User user = this.userDAO.findByUsernameAndPassword(Given.user(null, "invalid", "invalid", "email@d.com", "senha", "senha"));

		Assert.assertNull(user);
	}

	@Test
	public void shouldUpdateUser() {
		this.userDAO.save(Given.user(null, "UpdateUser", "UpdateUser", "UpdateUser@gmail.com", "senha", "senha"));
		
		User u = this.userDAO.findByUsername("UpdateUser");
		u.setEmail("200@gmail.com");
		u.setName("Verdadeiro Nome");
		
		this.userDAO.update(u);
		
		User user = this.userDAO.findByUsername("UpdateUser");

		Assert.assertEquals("200@gmail.com", user.getEmail());
		Assert.assertEquals("Verdadeiro Nome", user.getName());
		Assert.assertEquals("UpdateUser", user.getUsername());
		Assert.assertEquals("senha", user.getPassword());
		Assert.assertEquals("senha", user.getConfirmPassword());
	}

	@Test
	public void shouldRemove() {
		this.userDAO.save(Given.user(null, "Remove", "Remove", "Remove@gmail.com", "senha", "senha"));

		User user = this.userDAO.findByUsername("Remove");

		this.userDAO.remove(user);
		
		User u = this.userDAO.findByUsername("Remove");

		Assert.assertNull(u);
	}
}