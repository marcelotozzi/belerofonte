package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@InterceptResource
public class AdminController {
	@Path("/admin")
	public void admin(){
		
	}
}