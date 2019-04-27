package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class OpsLogoutPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(wdriver, 15);

	public OpsLogoutPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = ".//a[@class='dropdown-toggle username']")
	WebElement navbar;

	@FindBy(xpath = "//*[@id='help']")
	WebElement logout;

	@FindBy(xpath = "//button")
	WebElement signIn;

	@FindBy(xpath = "//img")
	WebElement logo;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;
	
	@FindBy(id = "j_username")
	WebElement username;

	public void logout(Map<String, String> dataMap) throws AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(navbar));
		Log.info("clicking username dropdown");
		navbar.click();
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		Log.info("logging out");
		logout.click();
		wait.until(ExpectedConditions.visibilityOf(signIn));
		Log.info("Logged out successfully");
		signIn.click();
		wait.until(ExpectedConditions.visibilityOf(username));
		Log.info("Ops Portal Login page displayed");
	}

	public void waitForSpinner() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-container']")));
		Log.info("spinner load complete");
	}

	public void tab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
}
