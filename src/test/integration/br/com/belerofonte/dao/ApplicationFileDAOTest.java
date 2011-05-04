package br.com.belerofonte.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
import br.com.belerofonte.model.ApplicationType;
import br.com.belerofonte.model.Plataform;

public class ApplicationFileDAOTest extends DaoTest {

	private ApplicationFileDAO applicationFileDAO;
	private Session s;
	private Transaction tx;
	private PlataformDAO plataformDAO;
	private ApplicationTypeDAO applicationTypeDAO;

	@Before
	public void setUp() throws Exception {
		s = getSession();
		tx = s.beginTransaction();
		this.applicationFileDAO = new ApplicationFileDAO(s);
		this.plataformDAO = new PlataformDAO(s);
		this.applicationTypeDAO = new ApplicationTypeDAO(s);
	}

	@After
	public void tearDown() throws Exception {
		tx.rollback();
	}

	@Test
	public void shouldFindByName() {
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));

		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Angry Birds");

		Assert.assertEquals("Angry Birds", applicationFile.getName());
	}
	
	@Test
	public void shouldNotFindByInvalidName() {
		ApplicationFile applicationFile = this.applicationFileDAO.findByName("AçãoInvalido");

		Assert.assertNull(applicationFile);
	}
	
	@Test
	public void shouldSaveApplicationFile() {
		
		ApplicationFile appFile = givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android");
		
		this.applicationFileDAO.save(appFile);
		
		ApplicationFile angry = this.applicationFileDAO.findByName("Angry Birds");
		
		Assert.assertNotNull(angry);
	}

	@Test
	public void shouldLoadApplicationFile() {
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));

		ApplicationFile applicationFileByName = this.applicationFileDAO.findByName("Angry Birds");
		ApplicationFile applicationFileByLoad = this.applicationFileDAO.load(applicationFileByName.getId());
		Assert.assertEquals(applicationFileByName.getId(), applicationFileByLoad.getId());
		Assert.assertEquals(applicationFileByName.getName(), applicationFileByLoad.getName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void shouldNotLoadInvalidApplicationFile() {
		ApplicationFile applicationFileByLoad = this.applicationFileDAO.load(10000L);

		Assert.assertNull(applicationFileByLoad);
	}

	@Test
	public void shouldRemoveApplicationFile() {
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));

		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Angry Birds");

		this.applicationFileDAO.remove(applicationFile);

		ApplicationFile app = this.applicationFileDAO.findByName("Angry Birds");

		Assert.assertNull(app);
	}
	
	@Test(expected=PropertyValueException.class)
	public void shouldNotRegisterWithNameNullApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile(null, "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithNameEmptyApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile("", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}
	
	@Test(expected=PropertyValueException.class)
	public void shouldNotRegisterWithNameOfFileNullApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", null, "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithNameOfFileEmptyApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}
	
	@Test(expected=PropertyValueException.class)
	public void shouldNotRegisterWithContentTypeNullApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", null, 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}
	
	@Test(expected=InvalidStateException.class)
	public void shouldNotRegisterWithContentTypeEmptyApplicationFile(){
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));
	}

	@Test
	public void shouldUpdateApplicationFile() {
		this.applicationFileDAO.save(givenApplicationFile("Angry Birds", "angry_birds.file", "Description", "contentType", 
				1L, 133545L, GregorianCalendar.getInstance(), "Whatever", "Android"));

		ApplicationFile p = this.applicationFileDAO.findByName("Angry Birds");
		p.setName("Angry Birds Rio");

		this.applicationFileDAO.update(p);

		ApplicationFile applicationFile = this.applicationFileDAO.findByName("Angry Birds Rio");

		Assert.assertEquals("Angry Birds Rio", applicationFile.getName());
	}
	
	private ApplicationFile givenApplicationFile(String name, String nameOfFile, String description, String contentType, 
			Long numberOfDownloads, Long sizeOfFile, Calendar uploadDate, String applicationType, String plataform) {
		ApplicationFile appFile = new ApplicationFile();
		appFile.setName(name);
		appFile.setNameOfFile(nameOfFile);
		appFile.setDescription(description);
		appFile.setContentType(contentType);
		appFile.setNumberOfDownloads(numberOfDownloads);
		appFile.setSizeOfFile(sizeOfFile);
		appFile.setUploadDate(uploadDate);
		
		ApplicationType appType = Given.applicationType(null, applicationType);
		this.applicationTypeDAO.save(appType);
		appFile.setApplicationType(appType);
		
		Plataform plat = Given.plataform(null, plataform);
		this.plataformDAO.save(plat);
		appFile.setPlataform(plat);
		return appFile;
	}
}