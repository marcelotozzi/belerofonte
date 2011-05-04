package br.com.belerofonte.controller;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginFuncionalTest {
	private WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
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
		
		WebElement adminMenu = this.webdriver.findElement(By.name("adminMenu"));
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
