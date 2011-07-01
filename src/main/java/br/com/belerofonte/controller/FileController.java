package br.com.belerofonte.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
@InterceptResource
public class FileController {

	private final ApplicationFileDAO applicationFileDAO;
	private final FileService applicationFileService;
	private final Result result;
	private final Validator validator;

	public FileController(ApplicationFileDAO applicationFileDAO, FileService applicationFileService, 
			Result result, Validator validator) {
		this.applicationFileDAO = applicationFileDAO;
		this.applicationFileService  = applicationFileService;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/admin/file/create")
	public void create(final UploadedFile uploadedFile, final ApplicationFile file) {
		this.validator.checking(new Validations(){{
			that(!file.getName().isEmpty(), "name", "name_not_reported");			   
		    that(!file.getDescription().isEmpty(), "description", "description_not_reported");	
		    that(file.getApplicationCategory(), is(notNullValue()), "category", "category_not_reported");
		    that(file.getPlataform(), is(notNullValue()), "plataform", "plataform_not_reported");
		}});
		this.validator.onErrorRedirectTo(this).form();
		
		this.applicationFileService.create(uploadedFile,file);
		this.result.redirectTo(FileController.class).show(file.getId());
	}

	public void delete(final Long id) {
		this.applicationFileDAO.remove(this.applicationFileDAO.load(id));
	}

	public void update(final ApplicationFile applicationFile) {
		this.validator.checking(new Validations(){{
			that(!applicationFile.getName().isEmpty(), "name", "name_not_reported");			   
		    that(!applicationFile.getDescription().isEmpty(), "description", "description_not_reported");	
		    that(applicationFile.getApplicationCategory(), is(notNullValue()), "category", "category_not_reported");
		    that(applicationFile.getPlataform(), is(notNullValue()), "plataform", "plataform_not_reported");
		}});
		this.validator.onErrorRedirectTo(this).form();
		
		this.applicationFileDAO.update(applicationFile);
		this.result.redirectTo(AccountController.class).account();
	}
	
	@Path("file/register")
	public void form(){
	}
	
	@NoInterceptMethod
	@Path("file/show/{id}")
	public void show(final Long id){
		ApplicationFile applicationFile = this.applicationFileDAO.load(id);
		this.result.include("file", applicationFile);
	}
	
	@NoInterceptMethod
	@Path("/files/topDownloads.json")
	public void topDownloadsJson(){
		this.result.use(Results.json()).from(this.applicationFileDAO.topDownloads(10), "topDownloads")
		.include("applicationCategory")
		.include("plataform")
		.include("user")
		.serialize();
	}
	
	@NoInterceptMethod
	@Path("/files/recentApplications.json")
	public void recentApplicationsJson(){
		this.result.use(Results.json()).from(this.applicationFileDAO.recentApplications(10), "recentApps")
		.include("applicationCategory")
		.include("plataform")
		.include("user")
		.serialize();
	}

	@NoInterceptMethod
	@Path("/file/download/{id}")
	public Download downloadFile(final Long id) {
		return this.applicationFileService.searchAndDownloadFile(id);
	}
}