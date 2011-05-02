package br.com.belerofonte.dao;

import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ApplicationFileDAO {

	public void save(ApplicationFile appFile) {
		// TODO Auto-generated method stub
	}

	public ApplicationFile load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ApplicationFile applicationFile) {
		// TODO Auto-generated method stub
	}
}