package br.com.belerofonte.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class ApplicationCategory {

	@Id @GeneratedValue private Long id;
	@NotNull @NotEmpty private String name;
	@NotNull @OneToMany private List<ApplicationType> applicationTypes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ApplicationType> getApplicationTypes() {
		return applicationTypes;
	}

	public void setApplicationTypes(List<ApplicationType> applicationTypes) {
		this.applicationTypes = applicationTypes;
	}
}