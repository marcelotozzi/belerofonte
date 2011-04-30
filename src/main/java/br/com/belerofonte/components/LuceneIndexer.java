package br.com.belerofonte.components;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class LuceneIndexer{
	
	public LuceneIndexer() {
		System.out.println("-------------CONSTRUTOR-------------");
		
	}
	
	@PostConstruct
	public void init() {
		System.out.println("-------------Startou-------------");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("-------------Destroy-------------");
	}
}