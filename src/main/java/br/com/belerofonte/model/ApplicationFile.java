package br.com.belerofonte.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class ApplicationFile {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String nameOfFile;
	private String description;
	@NotNull
	private Long sizeOfFile;
	@NotNull
	@NotEmpty
	private String contentType;
	@NotNull
	@ManyToOne
	private Plataform plataform;
	@NotNull
	@ManyToOne
	private ApplicationCategory applicationCategory;
	@NotNull
	@ManyToOne
	private ApplicationType applicationType;
	@Temporal(TemporalType.DATE)
	private Calendar uploadDate;
	@NotNull
	private Long numberOfDownloads;

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

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
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

	/*@Override
	public String toString() {
		return "Id[" + this.id + "] " + "Name[" + this.name + "] "
				+ "NameOfFile[" + this.nameOfFile + "] " + "Description["
				+ this.description + "] " + "SizeOfFile[" + this.sizeOfFile
				+ "] " + "ContentType[" + this.contentType + "] "
				+ "Plataform[" + this.plataform.getId() + "]["
				+ this.plataform.getName() + "] " + "ApplicationType["
				+ this.applicationType.getId() + "]["
				+ this.applicationType.getName() + "] " + "UploadDate["
				+ this.uploadDate.getTime() + "]";
	}*/
}