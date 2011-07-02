package br.com.belerofonte.infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class PropertiesLoader {
	private Properties props;
	private String nameOfFileProperties = "/files.properties";
	private static final Logger log = Logger.getLogger(PropertiesLoader.class);

	public PropertiesLoader() {
		props = new Properties();
		InputStream in = this.getClass().getResourceAsStream(nameOfFileProperties);
		try {
			props.load(in);
			in.close();
			log.debug("Carregado arquivo " + this.nameOfFileProperties);
		} catch (IOException e) {
			log.error("Arquivo n‹o encontrado" + this.nameOfFileProperties, e);
		}
	}

	public String getValue(String key) {
		return (String) props.getProperty(key);
	}
}
