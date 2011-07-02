package br.com.belerofonte.controller;

import java.util.List;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.SearchService;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
@InterceptResource
public class SearchController {

	private SearchService searchService;
	private Result result;

	public SearchController(SearchService searchService, Result result) {
		this.searchService = searchService;
		this.result = result;
	}

	@NoInterceptMethod
	@Path("/search")
	public void textSearch(String seek) {
		//TODO corrigir esse redirect para exibir nome na pesquisa
		this.result.redirectTo(this).searchResult(seek);
	}
	
	@NoInterceptMethod
	@Path("/search/{seek}")
	public void searchResult(String seek) {
		this.result.include("word",seek);
		this.result.include("files", this.searchService.search(seek));
	}

	@NoInterceptMethod
	@Path("/search.json")
	public void textSearchJson(String seek) {
		List<ApplicationFile> list = this.searchService.search(seek);
		this.result.use(Results.json()).from(list, "files")
		.include("applicationCategory")
		.include("plataform")
		.include("user")
		.serialize();
	}
}