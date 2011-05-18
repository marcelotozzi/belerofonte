package br.com.belerofonte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class UploadedFileTest implements UploadedFile {

	public String getContentType() {
		return "image/jpeg";
	}

	public InputStream getFile() {
		try {
			return new FileInputStream(new File(
					"/Users/marcelotozzi/Desktop/image.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return "image.jpg";
	}
}