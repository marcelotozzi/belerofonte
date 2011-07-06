package integration.br.com.belerofonte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class UploadedFileTest implements UploadedFile {

	public String getContentType() {
		return "application/octet-stream";
	}

	public InputStream getFile() {
		try {
			return new FileInputStream(new File(
					"/Users/marcelotozzi/Desktop/Twitter.apk"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return "Twitter.apk";
	}
}