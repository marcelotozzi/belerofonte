package br.com.belerofonte.common;

import java.util.Calendar;

import org.hibernate.Session;

import br.com.belerofonte.dao.ApplicationCategoryDAO;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.dao.ApplicationTypeDAO;
import br.com.belerofonte.dao.PlataformDAO;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.model.ApplicationType;
import br.com.belerofonte.model.ApplicationCategory;
import br.com.belerofonte.model.Plataform;
import br.com.belerofonte.model.User;

public class Given {
	
	private static ApplicationCategoryDAO categoryDAO;
	private static PlataformDAO plataformDAO;
	private static ApplicationTypeDAO typeDAO;
	private static ApplicationFileDAO fileDAO;
	private static UserDAO userDAO;

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
	
	public static User userPersisted(Long id, String name, String username, String email, String password, String confirmPassword){
		User user = userDAO.findByUsername(username);
		if (user != null) {
			return user;
		}else{
			user = new User();
			user.setName(name);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setConfirmPassword(confirmPassword);
			userDAO.save(user);
			return user;
		}
	}

	public static User invalidUser(Long id, String name, String username, String email,
			String password, String confirmPassword) {
		User user = user(id, name, username, email, password, confirmPassword);
		
		return user;
	}

	public static Plataform plataform(Long id, String name) {
		Plataform p = new Plataform();
		if(id!=null){
			p.setId(id);
		}
		p.setName(name);
		return p;
	}

	public static Plataform plataformPersisted(Long id, String name){
		Plataform plataform = plataformDAO.findByName(name);
		if (plataform != null) {
			return plataform;
		}else{
			plataform = new Plataform();
			plataform.setName(name);
			plataformDAO.save(plataform);
			return plataform;
		}
	}
	
	public static ApplicationType type(Long id, String name) {
		ApplicationType applicationType = new ApplicationType();
		if(id != null){
			applicationType.setId(id);
		}
		applicationType.setName(name);
		return applicationType;
	}
	
	public static ApplicationType typePersisted(Long id, String name) {
		ApplicationType applicationType = typeDAO.findByName(name);
		if(applicationType != null){
			return applicationType;
		}else{
			applicationType = new ApplicationType();
			applicationType.setName(name);
			typeDAO.save(applicationType);
			return applicationType;
		}
	}

	public static ApplicationFile file(Long id, String name, String nameOfFile, String description,
			String contentType, Long numberOfDownloads, Long sizeOfFile, Calendar uploadDate,
			ApplicationCategory category, ApplicationType type, Plataform plataform,
			User user){
		ApplicationFile file = new ApplicationFile();
		if (id != null) {
			file.setId(id);
		}
		file.setName(name);
		file.setNameOfFile(nameOfFile);
		file.setDescription(description);
		file.setContentType(contentType);
		file.setNumberOfDownloads(numberOfDownloads);
		file.setSizeOfFile(sizeOfFile);
		file.setUploadDate(uploadDate);
		file.setApplicationCategory(category);
		file.setApplicationType(type);
		file.setPlataform(plataform);
		file.setUser(user);
		return file;
	}
	
	public static ApplicationFile filePersisted(Long id, String name, String nameOfFile, String description,
			String contentType, Long numberOfDownloads, Long sizeOfFile, Calendar uploadDate,
			String nameCategory, String nameType, String namePlataform, String user){
		ApplicationFile file = fileDAO.findByName(name);
		if(file != null){
			return file;
		}else{
			file = new ApplicationFile();
			file.setName(name);
			file.setNameOfFile(nameOfFile);
			file.setDescription(description);
			file.setContentType(contentType);
			file.setNumberOfDownloads(numberOfDownloads);
			file.setSizeOfFile(sizeOfFile);
			file.setUploadDate(uploadDate);
			file.setApplicationCategory(categoryPersisted(null, nameCategory));
			file.setApplicationType(typePersisted(null, nameType));
			file.setPlataform(plataformPersisted(null, namePlataform));
			file.setUser(userPersisted(null, user, user, user+"@email.com", user, user));
			fileDAO.save(file);
			return file;
		}
	}

	public static ApplicationCategory category(Long id,String name) {
		ApplicationCategory appCategory = new ApplicationCategory();
		if (id != null) {
			appCategory.setId(id);
		}
		appCategory.setName(name);
		return appCategory;
	}
	
	public static ApplicationCategory categoryPersisted(Long id,String name) {
		ApplicationCategory appCategory = categoryDAO.findByName(name);
		if (appCategory != null) {
			return appCategory;
		}else{
			appCategory = new ApplicationCategory();
			appCategory.setName(name);
			categoryDAO.save(appCategory);
			return appCategory;
		}
	}

	public static void setSession(Session s) {
		categoryDAO = new ApplicationCategoryDAO(s);
		plataformDAO = new PlataformDAO(s);
		fileDAO = new ApplicationFileDAO(s);
		typeDAO = new ApplicationTypeDAO(s);
		userDAO = new UserDAO(s);
	}
}