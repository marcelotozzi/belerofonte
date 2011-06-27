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
	@Path("/search.json")
	public void textSearch(String text) {
		List<ApplicationFile> list = this.searchService.search(text);
		this.result.use(Results.json()).from(list, "list").serialize();
	}
}
