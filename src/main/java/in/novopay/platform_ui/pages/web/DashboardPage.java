package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.Log;

public class DashboardPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(wdriver, 300);

	@FindBy(xpath = ".//span[text()='Dashboard']/..")
	WebElement dashboard;

	@FindBy(xpath = "//*[@id='retailer-search_value']")
	WebElement search;

	@FindBy(xpath = "//*[@id='retailer-search_dropdown']")
	WebElement retailerSearch;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;

	// Load all objects in HomePage
	public DashboardPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void dashboard(Map<String, String> usrData) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(dashboard));
		Log.info("clicking on Dashboard option");
		clickInvisibleElement(dashboard);
		waitForSpinner();
		pageDown();
		wait.until(ExpectedConditions.elementToBeClickable(search));
		Log.info("entering mobile number");
		search.sendKeys(getStoredMobileFromIni());
		waitForSpinner();
		wait.until(ExpectedConditions.visibilityOf(retailerSearch));
		Log.info("searching for retailer");
		String shop = getShopNameFromIni("getShopName");
		String owner = getOwnerNameFromIni("getOwnerName");
		String retailer = ".//strong[contains(text(),'" + shop + ", " + owner
				+ "')]/../span[contains(text(),'ONBOARD') or contains(text(),'APPROVED')]/../..";
		Thread.sleep(1000);
		WebElement retailerDropDown = wdriver.findElement(By.xpath(retailer));
		wait.until(ExpectedConditions.elementToBeClickable(retailerDropDown));
		Log.info("selecting Retailer with Shop name '" + shop + "' and Owner name '" + owner + "'");
		Thread.sleep(1000);
		retailerDropDown.click();
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.click();
		pressEnter();
		waitForSpinner();
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		Log.info(pageTitle.getText() + " page displayed");
	}

	public void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) wdriver;
		jse.executeScript("scroll(0, 250);");
	}

	public void waitForSpinner() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-container']")));
		Log.info("spinner load complete");
	}
}
