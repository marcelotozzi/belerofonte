package br.com.belerofonte.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
@Indexed
public class ApplicationFile {
	@Id @GeneratedValue @DocumentId private Long id;
	@NotNull @NotEmpty @Field(index = Index.TOKENIZED, store = Store.NO) private String name;
	@NotNull @NotEmpty private String nameOfFile;
	@Field(index = Index.TOKENIZED, store = Store.NO) private String description;
	@NotNull private Long sizeOfFile;
	@NotNull @NotEmpty private String contentType;
	@NotNull @ManyToOne private Plataform plataform;
	@NotNull @ManyToOne private ApplicationCategory applicationCategory;
	@Temporal(TemporalType.TIMESTAMP) private Calendar uploadDate;
	@NotNull private Long numberOfDownloads;
	@NotNull @ManyToOne private User user;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameOfFile() {
		return nameOfFile;
	}

	public void setNameOfFile(String nameOfFile) {
		this.nameOfFile = nameOfFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSizeOfFile() {
		return sizeOfFile;
	}

	public void setSizeOfFile(Long sizeOfFile) {
		this.sizeOfFile = sizeOfFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Plataform getPlataform() {
		return plataform;
	}

	public void setPlataform(Plataform plataform) {
		this.plataform = plataform;
	}

	public Calendar getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Calendar uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Long getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(Long numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	public ApplicationCategory getApplicationCategory() {
		return applicationCategory;
	}

	public void setApplicationCategory(ApplicationCategory applicationCategory) {
		this.applicationCategory = applicationCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}