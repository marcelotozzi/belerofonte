package br.com.belerofonte.service;

import java.util.List;

import br.com.belerofonte.components.FileSearcher;
import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class SearchService {

	private FileSearcher fileSearcher;

	public SearchService(FileSearcher fileSearcher) {
		this.fileSearcher = fileSearcher;
	}

	public List<ApplicationFile> search(String text) {
		
		List<ApplicationFile> result = this.fileSearcher.fullText(text);
		
		return result;
	}
}