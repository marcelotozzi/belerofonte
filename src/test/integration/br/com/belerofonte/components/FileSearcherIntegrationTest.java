package br.com.belerofonte.components;


import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.dao.DaoTest;

public class FileSearcherIntegrationTest {
	private FileSearcher fileSearcher;
	
	@Before
	public void setUp() throws Exception {
		this.fileSearcher = new FileSearcher(DaoTest.getSession());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldSearch(){
		String text = "file";
		this.fileSearcher.fullText(text);
		fail("Nao implementado");
	}
}