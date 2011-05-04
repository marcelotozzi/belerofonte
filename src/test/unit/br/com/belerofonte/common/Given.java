package br.com.belerofonte.common;

import java.util.Calendar;

import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.model.ApplicationType;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.belerofonte.model.Plataform;
import br.com.belerofonte.model.User;

public class Given {
	
	public static User user(Long id,String name, String username, String email, String password, String confirmPassword) {
		User user = new User();
		if(id != null){
			user.setId(id);
		}
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		return user;
	}

	public static User invalidUser(Long id, String name, String username, String email,
			String password, String confirmPassword) {
		User user = user(id, name, username, email, password, confirmPassword);
		
		return user;
	}

	public static Plataform plataform(Long id, String name) {
		Plataform p = new Plataform();
		
		p.setId(id);
		p.setName(name);
		return p;
	}

	public static ApplicationType applicationType(Long id, String name) {
		ApplicationType applicationType = new ApplicationType();
		applicationType.setId(id);
		applicationType.setName(name);
		return applicationType;
	}

	public static ApplicationFile applicationFile(Long id, String name, String nameOfFile, String description, 
			Long sizeOfFile, String contentType, Plataform plataform, ApplicationType applicationType, Calendar uploadDate) {
		ApplicationFile appFile = new ApplicationFile();
		if (id != null) {
			appFile.setId(id);
		}
		appFile.setName(name);
		appFile.setNameOfFile(nameOfFile);
		appFile.setDescription(description);
		appFile.setSizeOfFile(sizeOfFile);
		appFile.setContentType(contentType);
		appFile.setPlataform(plataform);
		appFile.setApplicationType(applicationType);
		appFile.setUploadDate(uploadDate);
		return appFile;
	}

	public static ApplicationCategory category(long id, String name) {
		ApplicationCategory cat = new ApplicationCategory();
		cat.setId(id);
		cat.setName(name);
		return cat;
	}
}