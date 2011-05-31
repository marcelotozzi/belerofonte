package br.com.belerofonte.util;

import java.util.List;

import org.hibernate.Session;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.belerofonte.model.ApplicationType;
import br.com.belerofonte.model.Plataform;

public class BeforeDataIntegration {
	public static void main(Session session) {
		PlataformDAO plataformDAO = new PlataformDAO(session);
		ApplicationTypeDAO typeDAO = new ApplicationTypeDAO(session);
		ApplicationCategoryDAO categoryDAO = new ApplicationCategoryDAO(session);

		List<Plataform> plats = plataformDAO.list();
		for (Plataform plataform : plats) {
			plataformDAO.remove(plataform);
		}
		
		plataformDAO.save(Given.plataform(null, "iOS"));
		
		List<ApplicationType> types = typeDAO.list();
		for (ApplicationType applicationType : types) {
			typeDAO.remove(applicationType);
		}
		
		typeDAO.save(Given.type(null, "Imagem"));
		
		List<ApplicationCategory> cats = categoryDAO.list();
		for (ApplicationCategory applicationCategory : cats) {
			categoryDAO.remove(applicationCategory);
		}
		
		categoryDAO.save(Given.category(null, "Edição"));
	}
}