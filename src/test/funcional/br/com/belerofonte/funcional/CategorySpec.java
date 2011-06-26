package br.com.belerofonte.funcional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.util.CommonWebSteps;

public class CategorySpec {
	private CommonWebSteps cw;
	private Long aleatorio;
	private Session s;
	private Transaction tx;
	private ApplicationCategoryDAO dao;
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
	public void shouldEnterInCategoriesPage() {
		cw.givenImInTheCategoriesPage();
		cw.checkMessage("Categorias");
	}
	
	@Test
	public void shouldRegisterCategory(){
		cw.givenImInTheCategoriesPage();
		cw.clickTheLink("Adicionar");
		cw.checkMessage("Registre uma categoria");
		aleatorio = System.currentTimeMillis();
		cw.typeAtField(aleatorio.toString(), "category.name");
		cw.submitForm("newCategory");
		cw.checkMessage(aleatorio.toString());
		
		s = DaoTest.getSession();
		tx = s.beginTransaction();
		dao = new ApplicationCategoryDAO(s);
		dao.remove(dao.findByName(aleatorio.toString()));
		tx.commit();
		s.close();
	}
}
