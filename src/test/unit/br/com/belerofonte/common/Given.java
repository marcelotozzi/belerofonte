package br.com.belerofonte.common;

import br.com.belerofonte.model.Category;
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

	public static Plataform plataform(String name) {
		Plataform p = new Plataform();
		p.setName(name);
		return p;
	}

	public static Category category(String name) {
		Category category = new Category();
		category.setName(name);
		return category;
	}
}