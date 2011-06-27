package br.com.belerofonte.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class PropertiesLoader {
	private Properties props;
	private String nameOfFileProperties = "/files.properties";

	public PropertiesLoader() {
		props = new Properties();
		InputStream in = this.getClass().getResourceAsStream(nameOfFileProperties);
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		return (String) props.getProperty(key);
	}
}
