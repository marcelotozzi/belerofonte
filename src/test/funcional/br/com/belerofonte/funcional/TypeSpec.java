package br.com.belerofonte.funcional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.util.CommonWebSteps;

public class TypeSpec {
	private CommonWebSteps cw;
	private Long aleatorio;
	private Session s;
	private Transaction tx;
	private UserDAO dao;

	@Before
	public void setUp() throws Exception {
		cw = new CommonWebSteps();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		dao = new UserDAO(s);
		dao.save(Given.user(null, "Admin", "admin", "admin@email.com", "admin", "admin"));
		tx.commit();
		s.close();
	}

	@After
	public void tearDown() throws Exception {
		cw.closeAndQuit();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		dao = new UserDAO(s);
		dao.remove(dao.findByUsername("admin"));
		tx.commit();
		s.close();
	}

	@Test
	public void shouldEnterInTypesPage() {
		cw.givenImInTheTypesPage();
		cw.checkMessage("Tipos");
	}
	
	@Test
	public void shouldRegisterType(){
		cw.givenImInTheTypesPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre um tipo");
		aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "type.name");
		cw.submitForm("newType");
		cw.checkMessage(aleatorio.toString());
		Session s = DaoTest.getSession();
		Transaction tx = s.beginTransaction();
		ApplicationTypeDAO dao = new ApplicationTypeDAO(s);
		dao.remove(dao.findByName(aleatorio.toString()));
		tx.commit();
		s.close();
	}
}