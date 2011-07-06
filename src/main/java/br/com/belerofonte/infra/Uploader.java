package br.com.belerofonte.infra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class Uploader {
	private String dir;
	private PropertiesLoader loader;
	private User user;
	
	public Uploader(PropertiesLoader propertiesLoader) {
		this.loader = propertiesLoader;
	}

	public Uploader forUser(User user) {
		// -------- TODO Uploader refatorar geração de pastas -----------
		this.user = user;
		this.dir = this.loader.getValue("folderFiles") + this.user.getUsername();
		if (!new File(dir).exists()) {
			new File(dir).mkdir();
		}
			
		this.dir = this.loader.getValue("folderFiles") + this.user.getUsername() + this.loader.getValue("appFolder");
		if (!new File(dir).exists()) {
			new File(dir).mkdir();
		}
		// -------- TODO Uploader -----------
		return this;
	}

	public File copyFile(UploadedFile uploadedFile) throws FileNotFoundException, IOException {
		File filedestiny = new File(this.dir, uploadedFile.getFileName());
		
		IOUtils.copyLarge(uploadedFile.getFile(), new FileOutputStream(filedestiny));	
		
		return filedestiny;
	}
}