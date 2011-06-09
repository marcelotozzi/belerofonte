package br.com.belerofonte.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	@OneToMany(fetch=FetchType.EAGER) 
	@JoinColumn(name="user_id") 
	private List<ApplicationFile> files;
	
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
	
	public List<ApplicationFile> getFiles() {
		return files;
	}

	public void setFiles(List<ApplicationFile> files) {
		this.files = files;
	}
}