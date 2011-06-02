package br.com.belerofonte.dao;

import java.util.Calendar;
import java.util.List;

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
import br.com.belerofonte.model.ApplicationFile;

public class ApplicationFileDAOTest extends DaoTest {

	private ApplicationFileDAO applicationFileDAO;
	private Session s;
	private Transaction tx;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		Given.setSession(s);
		this.applicationFileDAO = new ApplicationFileDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldFindByName() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
		
		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Name");

		Assert.assertEquals("Name", applicationFile.getName());
	}

	@Test
	public void shouldNotFindByInvalidName() {
		ApplicationFile applicationFile = this.applicationFileDAO.findByName("InvalidName");

		Assert.assertNull(applicationFile);
	}

	@Test
	public void shouldSaveApplicationFile() {
		ApplicationFile appFile = Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");

		this.applicationFileDAO.save(appFile);

		ApplicationFile angry = this.applicationFileDAO.findByName("Name");

		Assert.assertNotNull(angry);
	}

	@Test
	public void shouldLoadApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");

		ApplicationFile applicationFileByName = this.applicationFileDAO.findByName("Name");
		ApplicationFile applicationFileByLoad = this.applicationFileDAO.load(applicationFileByName.getId());
		Assert.assertEquals(applicationFileByName.getId(),applicationFileByLoad.getId());
		Assert.assertEquals(applicationFileByName.getName(),applicationFileByLoad.getName());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidApplicationFile() {
		ApplicationFile applicationFileByLoad = this.applicationFileDAO.load(10000L);

		Assert.assertNull(applicationFileByLoad);
	}

	@Test
	public void shouldRemoveApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");

		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Name");

		this.applicationFileDAO.remove(applicationFile);

		ApplicationFile app = this.applicationFileDAO.findByName("Angry Birds");

		Assert.assertNull(app);
	}

	@Test(expected = PropertyValueException.class)
	public void shouldNotRegisterWithNameNullApplicationFile() {
		Given.filePersisted(null, null, "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = InvalidStateException.class)
	public void shouldNotRegisterWithNameEmptyApplicationFile() {
		Given.filePersisted(null, "", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = PropertyValueException.class)
	public void shouldNotRegisterWithNameOfFileNullApplicationFile() {
		Given.filePersisted(null, "Name", null, "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = InvalidStateException.class)
	public void shouldNotRegisterWithNameOfFileEmptyApplicationFile() {
		Given.filePersisted(null, "Name", "", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = PropertyValueException.class)
	public void shouldNotRegisterWithContentTypeNullApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", null, 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = InvalidStateException.class)
	public void shouldNotRegisterWithContentTypeEmptyApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = PropertyValueException.class)
	public void shouldNotRegisterWithNumberOfDownloadsNullApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				null, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test(expected = PropertyValueException.class)
	public void shouldNotRegisterWithSizeOfFileNullApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, null, Calendar.getInstance(), "Category", "Type", "Plataform");
	}

	@Test
	public void shouldUpdateApplicationFile() {
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 
				0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");

		ApplicationFile p = this.applicationFileDAO.findByName("Name");
		p.setName("Name Rio");

		this.applicationFileDAO.update(p);

		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Name Rio");

		Assert.assertEquals("Name Rio", applicationFile.getName());
	}
	
	@Test
	public void shouldReturnRecentApplications(){
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 0L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
		Given.filePersisted(null, "Name1", "nameOfFile1.file", "Description1", "contentType", 0L, 13134L, Calendar.getInstance(), "Category1", "Type1", "Plataform1");
		
		List<ApplicationFile> recents = this.applicationFileDAO.recentApplications(2);
	
		Assert.assertEquals(2, recents.size());
	}
	
	@Test
	public void shouldReturnTopDownloads(){
		Given.filePersisted(null, "Name", "nameOfFile.file", "Description", "contentType", 10L, 13134L, Calendar.getInstance(), "Category", "Type", "Plataform");
		Given.filePersisted(null, "Name1", "nameOfFile1.file", "Description1", "contentType", 20L, 13134L, Calendar.getInstance(), "Category1", "Type1", "Plataform1");
		
		List<ApplicationFile> top = this.applicationFileDAO.topDownloads(2);
		
		Assert.assertEquals(20, top.get(0).getNumberOfDownloads().longValue());
		Assert.assertEquals(10, top.get(1).getNumberOfDownloads().longValue());
	}
}