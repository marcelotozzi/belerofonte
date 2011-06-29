package br.com.belerofonte.model;


import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationFileTest {

	private ApplicationFile file;
	@Before
	public void setUp() throws Exception {
		this.file = new ApplicationFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetId() {
		this.file.setId(1L);
		Assert.assertEquals(1L, this.file.getId().longValue());
	}
	
	@Test
	public void testGetDescription(){
		this.file.setDescription("description");
		Assert.assertEquals("description", this.file.getDescription());	
	}
	
	@Test
	public void testGetCategory(){
		ApplicationCategory category = new ApplicationCategory();
		category.setName("Category");
		this.file.setApplicationCategory(category);
		Assert.assertEquals("Category", this.file.getApplicationCategory().getName());	
	}
	
	@Test
	public void testGetPlataform(){
		Plataform plataform = new Plataform();
		plataform.setName("Plataform");
		this.file.setPlataform(plataform);
		Assert.assertEquals("Plataform", this.file.getPlataform().getName());	
	}
	
	@Test
	public void testGetSizeOfFile(){
		this.file.setSizeOfFile(1L);
		Assert.assertEquals(new Long(1L), this.file.getSizeOfFile());	
	}
	
	@Test
	public void testGetUploadDate(){
		Calendar uploadDate = Calendar.getInstance();
		this.file.setUploadDate(uploadDate );
		Assert.assertEquals(uploadDate, this.file.getUploadDate());	
	}
}