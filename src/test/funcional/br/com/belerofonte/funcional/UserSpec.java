package br.com.belerofonte.funcional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.util.CommonWebSteps;

public class UserSpec {
	private CommonWebSteps cw;
	private static Long aleatorio;

	@Before
	public void setUp() throws Exception {
		cw = new CommonWebSteps();
	}

	@After
	public void tearDown() throws Exception {
		cw.closeAndQuit();
		Session s = DaoTest.getSession();
		Transaction tx = s.beginTransaction();
		UserDAO dao = new UserDAO(s);
		dao.remove(dao.findByUsername(aleatorio.toString()));
		tx.commit();
		s.close();
	}

	@Test
	public void shouldRegisterNewUser() {
		cw.navigateToPage("user/register");

		cw.checkMessage("Registre-se");

		aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "user.name");
		cw.typeAtField(aleatorio.toString(), "user.username");
		cw.typeAtField("jose@bele.com", "user.email");
		cw.typeAtField("senhadoze", "user.password");
		cw.typeAtField("senhadoze", "user.confirmPassword");
		cw.submitForm("newUser");

		cw.checkMessage("Bem vindo, " + aleatorio.toString() + "!");
	}
}