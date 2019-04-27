package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;

public class ProfilePage extends BasePage {

	RetailerDocumentsPage rdp = new RetailerDocumentsPage(wdriver);
	ReadDataFromExcel readData = new ReadDataFromExcel();
	DBUtils dbUtils = new DBUtils();

	WebDriverWait wait = new WebDriverWait(wdriver, 300);

	@FindBy(xpath = "//div/ul/li[3]/a")
	WebElement documentsTab;

	@FindBy(xpath = "//*[@id='approval-form']/div[2]/div[1]/div/div/div/a[1]")
	WebElement uploadDocumentsButton;

	@FindBy(xpath = "//*[@id='retailerDOB']")
	WebElement dob;

	@FindBy(xpath = "//div[2]/div/div[2]/input")
	WebElement remarks;

	@FindBy(xpath = "//div[2]/div/div[3]/button")
	WebElement submit;

	@FindBy(xpath = "//tr[1]/td[2]")
	WebElement datePicker;

	@FindBy(xpath = "//*[@id='email']")
	WebElement email;

	@FindBy(xpath = "//div[2]/div/a")
	WebElement activate;

	@FindBy(xpath = "//*[@id='refNo']")
	WebElement refNo;

	@FindBy(xpath = "//*[@id='amount']")
	WebElement amount;

	@FindBy(xpath = "//*[@id='walletManager_chosen']/a")
	WebElement walletManagedyBank;

	@FindBy(xpath = "//*[@id='axisBcAgentId']")
	WebElement axisBcAgentId;

	@FindBy(xpath = "//*[@id='axisBcTerminalId']")
	WebElement axisBcTerminalId;

	@FindBy(xpath = "//*[@id='rblBcAgentId']")
	WebElement rblBcAgentId;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitActivation;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;

	@FindBy(xpath = "//div[contains(@class,'noty_type_success')]")
	WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'noty_type_error')]")
	WebElement errorMessage;

	public ProfilePage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void profile(Map<String, String> usrData)
			throws InterruptedException, AWTException, ClassNotFoundException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(documentsTab));
		documentsTab.click();

		if (usrData.get("CLICKUPLOADBUTTON").equalsIgnoreCase("YES")) {
			pageScrollDown();
			wait.until(ExpectedConditions.visibilityOf(uploadDocumentsButton));
			Log.info("clicking on Upload Documents button");
			uploadDocumentsButton.click();
			waitForSpinner();
			wait.until(ExpectedConditions.visibilityOf(pageTitle));
			Log.info(pageTitle.getText() + " page displayed");
		} else if (usrData.get("CLICKUPLOADBUTTON").equalsIgnoreCase("NO")) {
			pageScrollDown();
			if (usrData.get("VERIFYPANCARD").equalsIgnoreCase("Valid")) {
				verifyRadioButton(1, 1);
			} else if (usrData.get("VERIFYPANCARD").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(1, 2);
			}
			Thread.sleep(500);
			if (usrData.get("VERIFYAADHAARCARD").equalsIgnoreCase("Valid")) {
				verifyRadioButton(2, 1);
			} else if (usrData.get("VERIFYAADHAARCARD").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(2, 2);
			}
			Thread.sleep(500);
			if (usrData.get("VERIFYSHOPESTACTDOC").equalsIgnoreCase("Valid")) {
				verifyRadioButton(3, 1);
			} else if (usrData.get("VERIFYSHOPESTACTDOC").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(3, 2);
			}
			Thread.sleep(500);
			if (usrData.get("VERIFYSHOPESTACTDOC").equalsIgnoreCase("Valid")) {
				verifyRadioButton(4, 1);
			} else if (usrData.get("VERIFYSHOPESTACTDOC").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(4, 2);
			}
			Thread.sleep(500);
			if (usrData.get("VERIFYRETONBOARDINGFORM").equalsIgnoreCase("Valid")) {
				verifyRadioButton(5, 1);
			} else if (usrData.get("VERIFYRETONBOARDINGFORM").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(5, 2);
			}
			if (usrData.get("VERIFYAFFIDAVITFORM").equalsIgnoreCase("Valid")) {
				verifyRadioButton(6, 1);
			} else if (usrData.get("VERIFYAFFIDAVITFORM").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(6, 2);
			}
			if (usrData.get("VERIFYPOA").equalsIgnoreCase("Valid")) {
				verifyRadioButton(7, 1);
			} else if (usrData.get("VERIFYPOA").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(7, 2);
			}
			if (usrData.get("VERIFYLOCALINTFORM").equalsIgnoreCase("Valid")) {
				verifyRadioButton(8, 1);
			} else if (usrData.get("VERIFYLOCALINTFORM").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(8, 2);
			}
			if (usrData.get("VERIFYIDPROOFPAGES").equalsIgnoreCase("Valid")) {
				verifyRadioButton(9, 1);
			} else if (usrData.get("VERIFYIDPROOFPAGES").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(9, 2);
			}
			if (usrData.get("VERIFYSHOPESTLICENSE").equalsIgnoreCase("Valid")) {
				verifyRadioButton(10, 1);
			} else if (usrData.get("VERIFYSHOPESTLICENSE").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(10, 2);
			}
			if (usrData.get("VERIFYM/STURNOVERSURROGATEDOC").equalsIgnoreCase("Valid")) {
				verifyRadioButton(11, 1);
			} else if (usrData.get("VERIFYM/STURNOVERSURROGATEDOC").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(11, 2);
			}
			if (usrData.get("VERIFYOWNERPHOTO").equalsIgnoreCase("Valid")) {
				verifyRadioButton(12, 1);
			} else if (usrData.get("VERIFYOWNERPHOTO").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(12, 2);
			}
			if (usrData.get("VERIFYCANCELLEDCHEQUE").equalsIgnoreCase("Valid")) {
				verifyRadioButton(13, 1);
			} else if (usrData.get("VERIFYCANCELLEDCHEQUE").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(13, 2);
			}
			if (usrData.get("VERIFYSHOPPHOTO").equalsIgnoreCase("Valid")) {
				verifyRadioButton(14, 1);
			} else if (usrData.get("VERIFYSHOPPHOTO").equalsIgnoreCase("Invalid")) {
				verifyRadioButton(14, 2);
			}

			Thread.sleep(500);
			rdp.pageDown();
			Thread.sleep(500);

			wait.until(ExpectedConditions.visibilityOf(dob));
			Log.info("clicking Date of Birth");
			dob.click();
			Thread.sleep(500);
			dob.click();
			dob.clear();

			email.click();

			dob.click();
			Thread.sleep(500);
			dob.click();
			wait.until(ExpectedConditions.visibilityOf(datePicker));
			datePicker.click();

			wait.until(ExpectedConditions.visibilityOf(remarks));
			Log.info("entering Remarks");
			remarks.sendKeys("Approved");
			wait.until(ExpectedConditions.visibilityOf(submit));
			Log.info("clicking on Submit button");
			submit.click();
			waitForSpinner();

			String mob = getStoredMobileFromIni();
			try {
				wait.until(ExpectedConditions.visibilityOf(successMessage));
				Log.info("Retailer with mobile number " + mob + " is onboarded");
				waitForSpinner();
				wait.until(ExpectedConditions.visibilityOf(pageTitle));
				Log.info(pageTitle.getText() + " page displayed");
			} catch (Exception e) {
				Log.info("Retailer with mobile number " + mob + " couldn't be onboarded due to below error:");
				Log.info(errorMessage.getText());
			}
		} else {
			wait.until(ExpectedConditions.visibilityOf(activate));
			Log.info("clicking activation icon");
			activate.click();
			wait.until(ExpectedConditions.visibilityOf(refNo));
			Log.info("entering reference number");
			refNo.sendKeys(generateRandomNumber(9999, 1000));
			Log.info("entering amount");
			amount.sendKeys(generateRandomNumber(99, 10));
			Log.info("clicking on Wallet Managed By Bank drop down");
			String walletManagedBy = getPartnerFromIni(usrData.get("WALLETMANAGEDBYBANK"));
			walletManagedyBank.click();
			int i = 0;
			if (walletManagedBy.equalsIgnoreCase("RBL")) {
				i = 1;
			} else if (walletManagedBy.equalsIgnoreCase("AXIS")) {
				i = 2;
			} else if (walletManagedBy.equalsIgnoreCase("IDFC")) {
				i = 3;
			} else if (walletManagedBy.equalsIgnoreCase("YBL")) {
				i = 4;
			}
			WebElement walletManagedByXpath = wdriver
					.findElement(By.xpath("//*[@id='walletManager_chosen']/div/ul/li[" + i + "]"));
			wait.until(ExpectedConditions.visibilityOf(walletManagedByXpath));
			Log.info("selecting Wallet Managed By Bank");
			walletManagedByXpath.click();

			String partner = walletManagedBy.toLowerCase();
			if (walletManagedBy.equalsIgnoreCase("RBL") || walletManagedBy.equalsIgnoreCase("AXIS")) {
				String partnerSpecificInfo = "//*[@id='a-" + partner + "-specific']/legend";
				WebElement partnerSpecificInfoElement = wdriver.findElement(By.xpath(partnerSpecificInfo));
				if (partnerSpecificInfoElement.getText().equals("AXIS Specific Info")) {
					Log.info("entering Axis BC Agent ID");
					axisBcAgentId.sendKeys(generateRandomNumber(999999, 100000));
					Log.info("entering Axis BC Terminal ID");
					axisBcTerminalId.sendKeys(generateRandomNumber(999999, 100000));
				} else {
					Log.info("entering RBL BC Agent ID");
					rblBcAgentId.sendKeys("NOV1000" + generateRandomNumber(99, 10));
				}
			}
			submitActivation.click();

			String mob = getStoredMobileFromIni();
			dbUtils.updateMPIN(mob);
			Log.info("MPIN changed to 1111");
			dbUtils.updateWalletBalance(mob);
			Log.info("Walet balance updated to 10,00,000.00");
			try {
				wait.until(ExpectedConditions.visibilityOf(successMessage));
				dbUtils.changeBCAgentId(mob, partner);
				Log.info("Congratulations! Retailer with mobile number " + mob + " is activated");
				waitForSpinner();
				wait.until(ExpectedConditions.visibilityOf(pageTitle));
				Log.info(pageTitle.getText() + " page displayed");
			} catch (Exception e) {
				Log.info("Retailer with mobile number " + mob + " couldn't be activated due to below error:");
				Log.info(errorMessage.getText());
			}
		}
	}

	public void verifyRadioButton(int i, int j) throws IOException {
		String docId = readData.readExcel("./test-data", "UITestData.xlsx", "ProfilePage", i + 1, 25);
		String action = "";
		if (j == 1) {
			action = "VERIFIED";
		} else if (j == 2) {
			action = "INVALID";
		}
		String verifyPageXpath = "//h4[contains(text(),'" + docId + "')]/parent::div//label/input[contains(@value,'"
				+ action + "')]";
		WebElement verifyPageRadioButton = wdriver.findElement(By.xpath(verifyPageXpath));
		Log.info("selecting radio button");
		verifyPageRadioButton.click();
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
