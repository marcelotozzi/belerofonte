package funcional.br.com.belerofonte.funcional;

import funcional.br.com.belerofonte.util.CommonWebSteps;
import integration.br.com.belerofonte.dao.DaoTest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unit.br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.dao.UserDAO;

public class PlataformSpec {
	private CommonWebSteps cw;
	private Long aleatorio;
	private Session s;
	private Transaction tx;
	private PlataformDAO dao;
	private UserDAO userDao;

	@Before
	public void setUp() throws Exception {
		cw = new CommonWebSteps();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		userDao = new UserDAO(s);
		userDao.save(Given.user(null, "Admin", "admin", "admin@email.com", "admin", "admin"));
		tx.commit();
		s.close();
	}

	@After
	public void tearDown() throws Exception {
		cw.closeAndQuit();
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		userDao = new UserDAO(s);
		userDao.remove(userDao.findByUsername("admin"));
		tx.commit();
		s.close();
	}

	@Test
	public void shouldEnterInPlataformPage() {
		cw.givenImInThePlataformPage();
		cw.checkMessage("Plataformas");
	}
	
	@Test
	public void shouldRegisterPlataform(){
		cw.givenImInThePlataformPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre uma plataforma");
		aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "plataform.name");
		cw.submitForm("newPlataform");
		cw.checkMessage(aleatorio.toString());
		
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		dao = new PlataformDAO(s);
		dao.remove(dao.findByName(aleatorio.toString()));
		tx.commit();
		s.close();
	}
}
