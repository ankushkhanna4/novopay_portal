package in.novopay.platform_ui.pages.web;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;

public class DeactivateRetailerPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	DBUtils dbUtils = new DBUtils();
	
	@FindBy(xpath = ".//span[text()='Deactivate Retailer']/..")
	WebElement deactivateRetailer;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;

	@FindBy(xpath = "//*[@id='msisdn']")
	WebElement mobileNo;

	@FindBy(xpath = "//div[2]/button[2]")
	WebElement search;

	@FindBy(xpath = "//*[@data-target='#deactivateRetailer']")
	WebElement deactivateButton;
	
	@FindBy(xpath = "//div[3]/button[2]")
	WebElement deactivateConfirm;

	public DeactivateRetailerPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	public void deactivateRetailer(Map<String, String> usrData) {
		wait.until(ExpectedConditions.elementToBeClickable(deactivateRetailer));
		Log.info("clicking on Deactivate Retailer option");
		clickInvisibleElement(deactivateRetailer);
		waitForSpinner();
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		Log.info(pageTitle.getText() + " page displayed");
		wait.until(ExpectedConditions.elementToBeClickable(mobileNo));
		Log.info("entering mobile number");
		mobileNo.sendKeys(getMobileToDeactivateFromIni(usrData.get("MOBILENUMBER")));
		Log.info("searching for Retailer");
		search.click();
		waitForSpinner();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(deactivateButton));
			Log.info("deactivating the Retailer");
			deactivateButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(deactivateConfirm
					));
			Log.info("confirming Deactivation");
			deactivateConfirm.click();
			waitForSpinner();
			Log.info("Retailer with mobile "+getMobileToDeactivateFromIni(usrData.get("MOBILENUMBER"))+" is deactivated");
			dbUtils.deleteFromOrgDevices(getMobileToDeactivateFromIni(usrData.get("MOBILENUMBER")));
		} catch (Exception e) {
			Log.info("Retailer with mobile "+getMobileToDeactivateFromIni(usrData.get("MOBILENUMBER"))+" doesn't exist");
		}
		
	}

	public void waitForSpinner() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-container']")));
		Log.info("spinner load complete");
	}
}
