package br.com.belerofonte.components;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;

import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class FileSearcher {
	private FullTextSession fullTextSession;
	private SearchFactory searchFactory;
	private static final Logger log = Logger.getLogger(FileSearcher.class);
	
	public FileSearcher(Session session) {
		this.fullTextSession = Search.getFullTextSession(session);
		this.searchFactory = fullTextSession.getSearchFactory();
	}

	@SuppressWarnings("unchecked")
	public List<ApplicationFile> fullText(String text) {
		QueryParser parser = new QueryParser(Version.LUCENE_32, "name",searchFactory.getAnalyzer(ApplicationFile.class));
		//TODO refatorar pesquisa para pegar todos os tipos de classes // conforme a classe inserida busca pelo campo indexado
		Query luceneQuery = null;
		try {
			luceneQuery = parser.parse("name.orderBy_name:" + text);
		} catch (ParseException e) {
			// TODO inserir log // lançar log para controller
			log.error("Erro no parse de name.orderBy_name:", e);
		}

		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
	
		return fullTextQuery.list();
	}
}
