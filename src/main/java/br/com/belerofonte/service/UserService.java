package br.com.belerofonte.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import br.com.belerofonte.components.Account;
import br.com.belerofonte.dao.UserDAO;
import br.com.belerofonte.infra.PropertiesLoader;
import br.com.belerofonte.model.User;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class UserService {
	private UserDAO userDAO;
	private String diretorio;
	private String diretorioThumb;
	private PropertiesLoader propertiesLoader;
	private Account account;
	private User user;
	private int width = 100;
	private int height = 100;

	public UserService(UserDAO userDAO, PropertiesLoader propertiesLoader,
			Account account) {
		this.userDAO = userDAO;
		this.propertiesLoader = propertiesLoader;
		this.account = account;
		this.user = this.account.getUser();
	}

	public void update(User user, UploadedFile photo) {
		this.user = this.account.getUser();
		
		// -------- TODO UserService refatorar gera‹o de pastas -----------
		this.diretorio = this.propertiesLoader.getValue("folderFiles") + this.user.getUsername();
		
		if (!new File(diretorio).exists()) {
			new File(diretorio).mkdir();
		}
		
		this.diretorio = this.propertiesLoader.getValue("folderFiles")
				+ this.user.getUsername()
				+ this.propertiesLoader.getValue("photoFolder");
		
		if (!new File(this.diretorio).exists()) {
			new File(this.diretorio).mkdir();
		}

		this.diretorioThumb = this.propertiesLoader.getValue("folderFiles")
				+ this.user.getUsername()
				+ this.propertiesLoader.getValue("thumbFolder");
		
		if (!new File(diretorioThumb).exists()) {
			new File(diretorioThumb).mkdir();
		}

		try {
			InputStream stream = photo.getFile();

			String tmpName = user.getId().toString();
			
			File arquivo = new File(diretorio + tmpName + ".jpg");
			arquivo.createNewFile();

			IOUtils.copy(stream, new FileOutputStream(arquivo));

			thumb(tmpName, arquivo);

			this.userDAO.update(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -------- TODO UserService -----------
	}

	private void thumb(String tmpName, File arquivo) throws IOException,
			FileNotFoundException {
		File thumbnail = new File(diretorioThumb + "thumb_" + tmpName + ".jpg");
		thumbnail.createNewFile();

		if (arquivo != null && thumbnail != null) {
			FileInputStream input = new FileInputStream(arquivo);
			try {
				BufferedImage bufImage = ImageIO.read(input);
				if (bufImage != null) {
					double scale = calcScale(bufImage, width, height);
					BufferedImage imageScaled = scale(bufImage, scale);
					FileOutputStream fos = new FileOutputStream(thumbnail);
					ImageIO.write(imageScaled, "JPG", fos);
					fos.close();
					input.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// FileInputStream fileInputStream = new FileInputStream(arquivo);
		// FileOutputStream fileOutputStream = new FileOutputStream(thumbnail);
		// IOUtils.copy(fileInputStream, fileOutputStream);
	}

	private static double calcScale(BufferedImage img, int width, int height) {
		double scale1 = (double) width / (double) img.getWidth();
		double scale2 = (double) height / (double) img.getHeight();
		double scale = scale1 > scale2 ? scale2 : scale1;
		return scale;
	}

	private static BufferedImage scale(BufferedImage img, double scale) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage scaled = new BufferedImage((int) (w * scale),
				(int) (h * scale), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = scaled.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(img.getScaledInstance((int) (w * scale),
				(int) (h * scale), Image.SCALE_SMOOTH), 0, 0,
				(int) (w * scale), (int) (h * scale), null);
		g2.dispose();
		return scaled;
	}
}