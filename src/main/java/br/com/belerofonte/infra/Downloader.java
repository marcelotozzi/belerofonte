package br.com.belerofonte.infra;

import java.io.File;

import br.com.belerofonte.model.ApplicationFile;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class Downloader {
	private PropertiesLoader loader;
	
	public Downloader(PropertiesLoader propertiesLoader) {
		this.loader = propertiesLoader;
	}
	
	public Download getFileTheUser(ApplicationFile appFile) {
		File file = new File(loader.getValue("folderFiles") 
				+ appFile.getUser().getUsername() 
				+ loader.getValue("appFolder") 
				+ appFile.getNameOfFile());
		
		return new FileDownload(file, appFile.getContentType(), appFile.getNameOfFile());
	}
}