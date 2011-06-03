package br.com.belerofonte.controller;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.dao.ApplicationFileDAO;
import br.com.belerofonte.model.ApplicationFile;
import br.com.belerofonte.service.FileService;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;

@Resource
@InterceptResource
public class FileController {

	private final ApplicationFileDAO applicationFileDAO;
	private final FileService applicationFileService;
	private final Result result;

	public FileController(ApplicationFileDAO applicationFileDAO, FileService applicationFileService, Result result) {
		this.applicationFileDAO = applicationFileDAO;
		this.applicationFileService  = applicationFileService;
		this.result = result;
	}

	@Post
	@Path("/admin/file/create")
	public void create(final UploadedFile uploadedFile, final ApplicationFile file) {
		this.applicationFileService.create(uploadedFile,file);
		this.result.redirectTo(FileController.class).show(file.getId());
	}

	public void delete(final Long id) {
		this.applicationFileDAO.delete(this.applicationFileDAO.load(id));
	}

	public void update(final ApplicationFile applicationFile) {
		this.applicationFileDAO.update(applicationFile);
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
		this.result.use(Results.json()).from(this.applicationFileDAO.topDownloads(10), "topDownloads").serialize();
	}
	
	@NoInterceptMethod
	@Path("/files/recentApplications.json")
	public void recentApplicationsJson(){
		this.result.use(Results.json()).from(this.applicationFileDAO.recentApplications(10), "recentApps").serialize();
	}

	@NoInterceptMethod
	@Path("/file/download/{id}")
	public Download downloadFile(final Long id) {
		return this.applicationFileService.searchAndDownloadFile(id);
	}
}