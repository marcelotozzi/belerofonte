package br.com.belerofonte.funcional;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.util.CommonWebSteps;

public class FileSpec {
	private CommonWebSteps cw;
	private Session s;
	private Transaction tx;
	private UserDAO userDao;

	@Before
	public void setUp() throws Exception {
		cw = new CommonWebSteps();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		userDao = new UserDAO(s);
		userDao.save(Given.user(null, "Username", "username", "username@email.com", "password", "password"));
		tx.commit();
		s.close();
	}

	@After
	public void tearDown() throws Exception {
		cw.closeAndQuit();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		userDao = new UserDAO(s);
		userDao.remove(userDao.findByUsername("username"));
		tx.commit();
		s.close();
	}
	
	@Test
	public void shouldEnterInFileUploadPage() {
		cw.login("username", "password");
		cw.checkMessage("Bem vindo, Username!");
		cw.clickTheLink("Adicionar Aplicativo");
		cw.checkMessage("Registre uma aplicação");
	}
	
	@Test
	public void shouldUploadFile(){
		cw.login("username", "password");
		cw.checkMessage("Bem vindo, Username!");
		cw.clickTheLink("Adicionar Aplicativo");
		cw.checkMessage("Registre uma aplicação");
		cw.checkMessage("Informe os dados do arquivo");		
		cw.typeAtField("Belerofonte", "file.name");
		cw.typeAtField("Aplicação do sistema Belerofonte", "file.description");
		cw.select("selectPlataform","Android");
		
		//cw.clickTheButton("submitFile");
		Assert.fail("Não implementado");
	}
}