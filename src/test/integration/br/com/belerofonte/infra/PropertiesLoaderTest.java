package integration.br.com.belerofonte.infra;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.infra.PropertiesLoader;

public class PropertiesLoaderTest {

	private PropertiesLoader propertiesLoader;

	@Before
	public void setUp() throws Exception {
		this.propertiesLoader = new PropertiesLoader();
	}
	
	@Test
	public void shouldLoadProperties(){
		String loaded = this.propertiesLoader.getValue("folderFiles");
		Assert.assertEquals("/Users/marcelotozzi/Dev/Java/workspaces/workspace_belerofonte/belerofonte/WebContent/files/", loaded);
	}
}