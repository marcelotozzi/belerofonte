package br.com.belerofonte.controller;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.belerofonte.common.Given;
import br.com.belerofonte.dao.DaoTest;
import br.com.belerofonte.dao.UserDAO;

public class LoginFuncionalTest {
	private WebDriver webdriver;
	private Session session;
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		this.session = DaoTest.getSession();
		this.userDAO = new UserDAO(this.session);
		userDAO.save(Given.user(null, "Admin", "admin", "admin@gmail.com", "admin", "admin"));
		userDAO.save(Given.user(null, "Username", "username", "admin@gmail.com", "password", "password"));
		this.session.close();
		this.webdriver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		this.webdriver.close();
		this.webdriver.quit();
	}

	@Test
	public void shouldLoginInAppLikeAdmin() {
		this.webdriver.get("http://localhost:8081/belerofonte");

		WebElement loginlink = this.webdriver.findElement(By.name("loginlink"));
		loginlink.click();
		
		String username = "admin";
		String password = "admin";
		WebElement inputUsername = this.webdriver.findElement(By.name("user.username"));
		inputUsername.sendKeys(username);

		WebElement inputPassword = this.webdriver.findElement(By.name("user.password"));
		inputPassword.sendKeys(password);

		WebElement formLogin = this.webdriver.findElement(By.id("formLogin"));
		formLogin.submit();

		WebElement welcome = this.webdriver.findElement(By.id("welcome"));

		WebElement adminMenu = this.webdriver.findElement(By.name("adminMenu"));

		Assert.assertEquals("Bem vindo, Admin!", welcome.getText());
		Assert.assertNotNull(adminMenu);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void shouldLoginInAppLikeNormalUser() {
		this.webdriver.get("http://localhost:8081/belerofonte");

		WebElement loginlink = this.webdriver.findElement(By.name("loginlink"));
		loginlink.click();
		
		String username = "username";
		String password = "password";
		WebElement inputUsername = this.webdriver.findElement(By.name("user.username"));
		inputUsername.sendKeys(username);

		WebElement inputPassword = this.webdriver.findElement(By.name("user.password"));
		inputPassword.sendKeys(password);

		WebElement formLogin = this.webdriver.findElement(By.id("formLogin"));
		formLogin.submit();

		WebElement welcome = this.webdriver.findElement(By.id("welcome"));
		
		Assert.assertEquals("Bem vindo, Username!", welcome.getText());
		
		this.webdriver.findElement(By.name("adminMenu"));
	}
	
	@Test
	public void shouldNotLoginInAppWithInvalidUser() {
		this.webdriver.get("http://localhost:8081/belerofonte");

		WebElement loginlink = this.webdriver.findElement(By.name("loginlink"));
		loginlink.click();
		
		String username = "invalid";
		String password = "invalid";
		WebElement inputUsername = this.webdriver.findElement(By.name("user.username"));
		inputUsername.sendKeys(username);

		WebElement inputPassword = this.webdriver.findElement(By.name("user.password"));
		inputPassword.sendKeys(password);

		WebElement formLogin = this.webdriver.findElement(By.id("formLogin"));
		formLogin.submit();

		Assert.assertEquals("http://localhost:8081/belerofonte/account/login", this.webdriver.getCurrentUrl());
	}
}