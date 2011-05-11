package br.com.belerofonte.util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonWebSteps {
	String serverUrl = "http://localhost:8081/belerofonte";
	WebDriver driver = new FirefoxDriver();

	public void navigateToPage(String page) {
		driver.get(serverUrl + page);
	}

	public void imNotLoggedOn() {
		navigateToPage("/logout");
	}

	public void imAtHome() {
		navigateToPage("/home");
	}

	public void login(String username, String password) {
		// imNotLoggedOn();
		navigateToPage("/account/login");
		TypeAtField(username, "user.username");
		TypeAtField(password, "user.password");
		submitForm("formLogin");
	}

	public void submitForm(String form) {
		this.driver.findElement(By.id(form)).submit();
	}

	public void TypeAtField(String chars, String inputTextName) {
		this.driver.findElement(By.name(inputTextName)).sendKeys(chars);
	}

	public void clickTheButton(String buttonId) {
		this.driver.findElement(By.id(buttonId)).click();
	}

	public void clickTheButtonByName(String buttonName) {
		this.driver.findElement(By.id(buttonName)).click();
	}

	public void checkMessage(String message) {
		Assert.assertTrue(this.driver.getPageSource().contains(message));
	}

	public void closeAndQuit() {
		this.driver.close();
		this.driver.quit();
	}
}