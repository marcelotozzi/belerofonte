package br.com.belerofonte.common;

import br.com.belerofonte.model.User;

public class Given {

	public static User user(long id, String username, String email,
			String password, String confirmPassword) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		return user;
	}

	public static User invalidUser(long id, String username, String email,
			String password, String confirmPassword) {
		User user = user(id, username, email, password, confirmPassword);
		
		return user;
	}

}
