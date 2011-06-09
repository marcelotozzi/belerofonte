package br.com.belerofonte.components;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class PropertiesLoaderTest {

	private PropertiesLoader propertiesLoader;

	@Before
	public void setUp() throws Exception {
		this.propertiesLoader = new PropertiesLoader();
	}
	
	@Test
	public void shouldLoadProperties(){
		String loaded = this.propertiesLoader.getValue("folderFiles");
		Assert.assertEquals("/Users/marcelotozzi/Desktop/files/", loaded);
	}
}