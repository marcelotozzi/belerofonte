package integration.br.com.belerofonte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class UploadedPhotoTest implements UploadedFile {

	@Override
	public String getContentType() {
		return "image/jpeg";
	}

	@Override
	public InputStream getFile() {
		try {
			return new FileInputStream(new File(
					"/Users/marcelotozzi/Desktop/image.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getFileName() {
		return "image.jpg";
	}

}
