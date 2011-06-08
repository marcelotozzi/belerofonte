package br.com.belerofonte.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class ApplicationCategory {
	@Id @GeneratedValue private Long id;
	@NotNull @NotEmpty private String name;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="applicationCategory", targetEntity=ApplicationFile.class) 
	@JoinColumn(name="applicationCategory_id") 
	private List<ApplicationFile> files;
	
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

	public List<ApplicationFile> getFiles() {
		return files;
	}

	public void setFiles(List<ApplicationFile> files) {
		this.files = files;
	}
}