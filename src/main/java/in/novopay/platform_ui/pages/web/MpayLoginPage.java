package in.novopay.platform_ui.pages.web;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.Log;

public class MpayLoginPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(wdriver, 300);

	public MpayLoginPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(id = "j_username")
	WebElement username;

	@FindBy(id = "j_password")
	WebElement password;

	@FindBy(xpath = "//button")
	WebElement submit;

	@FindBy(xpath = "//img")
	WebElement logo;
	
	@FindBy(xpath = "//*[@id='userListContent']//h2")
	WebElement pageTitle;

	public void login(Map<String, String> dataMap) {
		try {
			wdriver.manage().window().maximize();
			Log.info("Mpay Portal Login page displayed");
			wait.until(ExpectedConditions.visibilityOf(username));
			username.click();
			username.clear();
			username.sendKeys(dataMap.get("USERNAME"));
			wait.until(ExpectedConditions.elementToBeClickable(password));
			password.click();
			password.clear();
			password.sendKeys(dataMap.get("PASSWORD"));
			Log.info("clicking on submit button");
			wait.until(ExpectedConditions.elementToBeClickable(submit));
			submit.click();
			wait.until(ExpectedConditions.visibilityOf(pageTitle));
			Log.info(pageTitle.getText() + " page displayed");
		} catch (Exception e) {
			Log.info("Login Failed");
		}
	}
}
