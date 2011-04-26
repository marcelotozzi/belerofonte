package br.com.belerofonte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

@Entity
public class User {
	@Id @GeneratedValue private Long id;
	@NotEmpty @Length(min = 3) private String name;
	@NotEmpty @Length(min = 3, max = 18) @Index(name="user_usname", columnNames="username") private String username;
	@NotEmpty @Length(min = 10) @Email private String email;
	@NotEmpty @Length(min = 4) private String password;
	@NotEmpty @Length(min = 4) private String confirmPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ID:["+ this.id +"] Name:["+ this.name +"] Username:["+ this.username +"] Email:["+ this.email+"]";
	}
}