package br.com.belerofonte.controller;

import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.model.Plataform;
import br.com.caelum.vraptor.Resource;

@Resource
public class PlataformController {

	private PlataformDAO plataformDAO;

	public PlataformController(PlataformDAO plataformDAO) {
		this.plataformDAO = plataformDAO;
	}

	public void create(Plataform plataform) {
		this.plataformDAO.save(plataform);	
	}

	public void update(Plataform plataform) {
		this.plataformDAO.update(plataform);
	}

	public void delete(long id) {
		this.plataformDAO.remove(this.plataformDAO.load(id));
	}
}