package funcional.br.com.belerofonte.funcional;

import funcional.br.com.belerofonte.util.CommonWebSteps;
import integration.br.com.belerofonte.dao.DaoTest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unit.br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.UserDAO;

public class AdminSpec {
	private CommonWebSteps cw;
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
	public void shouldEnterInTheAdminPage() {
		cw.givenImInTheAdminPage();

		cw.checkMessage("Administração");
		cw.checkMessage("Plataformas");
		cw.checkMessage("Categorias");
	}
}