package br.com.belerofonte.util;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import br.com.belerofonte.components.PropertiesLoader;

public class CommonWebSteps {
	String serverUrl;
	WebDriver driver = new HtmlUnitDriver();
	private PropertiesLoader loader;
	
	public CommonWebSteps() {
		loader = new PropertiesLoader();
		serverUrl = loader.getValue("homeUrlTest");
	}

	public void navigateToPage(String page) {
		driver.get(serverUrl + page);
	}

	public void imNotLoggedOn() {
		navigateToPage("account/logout");
	}

	public void imAtHome() {
		navigateToPage(serverUrl);
	}

	public void login(String username, String password) {
		// imNotLoggedOn();
		navigateToPage("account/login");
		typeAtField(username, "user.username");
		typeAtField(password, "user.password");
		submitForm("formLogin");
	}

	public void submitForm(String form) {
		this.driver.findElement(By.id(form)).submit();
	}

	public void typeAtField(String chars, String inputTextName) {
		this.driver.findElement(By.name(inputTextName)).sendKeys(chars);
	}

	public void clickTheButton(String buttonId) {
		this.driver.findElement(By.id(buttonId)).click();
	}

	public void checkMessage(String message) {
		Assert.assertTrue(this.driver.getPageSource().contains(message));
	}

	public void closeAndQuit() {
		this.driver.close();
		this.driver.quit();
	}

	public void clickTheLink(String linkText) {
		this.driver.findElement(By.linkText(linkText)).click();
	}

	public void givenImInTheAdminPage() {
		login("admin", "admin");
		checkMessage("Bem vindo, Admin!");
		navigateToPage("admin");
	}

	public void givenImInThePlataformPage() {
		this.givenImInTheAdminPage();
		navigateToPage("plataforms");
	}

	public void givenImInTheCategoriesPage() {
		givenImInTheAdminPage();
		navigateToPage("categories");
	}

	public void select(String id, String name) {
		// WebElement select = driver.findElement(By.xpath("//select[@id='"+id+"']"));
		WebElement select = driver.findElement(By.id(id));
		List<WebElement> allOptions = select.findElements(By.tagName("option"));
		for (WebElement option : allOptions) {
			if(option.getText().equals(name)){
				option.setSelected();
			}
		}
	}
}