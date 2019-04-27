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
import in.novopay.platform_ui.utils.JavaUtils;
import in.novopay.platform_ui.utils.Log;

public class AddRetailerPage extends BasePage {
	WriteDataToExcel wdte = new WriteDataToExcel();

	WebDriverWait wait = new WebDriverWait(wdriver, 300);

	@FindBy(xpath = ".//span[text()='Add Retailer']/..")
	WebElement addRetailer;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;

	@FindBy(id = "name")
	WebElement nameOfShop;

	@FindBy(id = "ownerName")
	WebElement nameOfOwner;

	@FindBy(id = "msisdn")
	WebElement mobileNo;

	@FindBy(xpath = "//*[@id='partner_chosen']/a")
	WebElement partner;

	@FindBy(id = "dateOfMeeting")
	WebElement dateOfMeeting;

	@FindBy(id = "panNumber")
	WebElement panNumber;

	@FindBy(id = "addressLine1")
	WebElement address;

	@FindBy(id = "pincode")
	WebElement pincode;

	@FindBy(id = "dob")
	WebElement dateOfBirth;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "panNo")
	WebElement panNo;

	@FindBy(id = "aadhaarNo")
	WebElement aadhaarNo;

	@FindBy(id = "idProof")
	WebElement idProof;

	@FindBy(id = "settlementAcNo")
	WebElement settlementAcNo;

	@FindBy(id = "settlementAcHolderName")
	WebElement settlementAcHolderName;

	@FindBy(id = "settlementIFSC")
	WebElement settlementIFSC;

	@FindBy(xpath = "//button[2]")
	WebElement createRetailer;

	@FindBy(xpath = "//tr[1]/td[2]")
	WebElement datePicker;

	@FindBy(xpath = "//div[contains(@class,'noty_type_success')]")
	WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'noty_type_error')]")
	WebElement errorMessage;

	// Load all objects in HomePage
	public AddRetailerPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void addRetailer(Map<String, String> usrData) throws InterruptedException, AWTException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(addRetailer));
		Log.info("clicking on Add Retailer option");
		clickInvisibleElement(addRetailer);
		waitForSpinner();
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		Log.info(pageTitle.getText() + " page displayed");
		addRetailerpageDropDowns(usrData, "onboardAs", "ONBOARDAS");

		Log.info("entering Name of Shop");
		nameOfShop.sendKeys(getShopNameFromIni(usrData.get("SHOPNAME")));

		Log.info("entering Owner Name");
		nameOfOwner.sendKeys(getOwnerNameFromIni(usrData.get("OWNERNAME")));
		
		Log.info("entering Mobile Number");
		mobileNo.sendKeys(getMobileFromIni(usrData.get("MOBILENUMBER")));

		// String mobileNum = "";
		// if (usrData.get("MOBILENUMBER").equalsIgnoreCase("DYNAMIC")) {
		// mobileNum = generateRandomMobileNumber();
		// mobileNo.sendKeys(mobileNum);
		// } else {
		// mobileNum = usrData.get("MOBILENUMBER");
		// mobileNo.sendKeys(usrData.get("MOBILENUMBER"));
		// }
		// wdte.writeToParticularCell("./test-data", "UITestData.xlsx", "DashboardPage",
		// 2, 6, mobileNum);
		// wdte.writeToParticularCell("./test-data", "UITestData.xlsx", "ProfilePage",
		// 4, 14, mobileNum);

		Log.info("clicking on Partner drop down");
		String partnerName = getPartnerFromIni(usrData.get("PARTNER"));
		// wdte.writeToParticularCell("./test-data", "UITestData.xlsx", "ProfilePage",
		// 4, 12, partnerName);
		partner.click();
		int i = 0;
		if (partnerName.equalsIgnoreCase("RBL")) {
			i = 1;
		} else if (partnerName.equalsIgnoreCase("NOVOPAY_SP")) {
			i = 2;
		} else if (partnerName.equalsIgnoreCase("AXIS")) {
			i = 3;
		} else if (partnerName.equalsIgnoreCase("IDFC")){
			i = 4;
		} else if (partnerName.equalsIgnoreCase("YBL")) {
			i = 5;
		}
		WebElement partnerXpath = wdriver.findElement(By.xpath("//*[@id='partner_chosen']/div/ul/li[" + i + "]"));
		wait.until(ExpectedConditions.visibilityOf(partnerXpath));
		Log.info("selecting Partner");
		partnerXpath.click();
		Log.info("entering Date of Meeting");
		dateOfMeeting.click();
		dateOfMeeting.click();
		dateOfMeeting.sendKeys(usrData.get("DOM"));
		pressEnter();
		panNumber.sendKeys(usrData.get("PANNUMBER"));
		addRetailerpageDropDowns(usrData, "businessCategory", "BUSINESSCATEGORY");
		pressTab();
		addRetailerpageDropDowns(usrData, "finCat", "YEARLYTURNOVER");
		address.sendKeys(usrData.get("ADDRESS"));
		addRetailerpageDropDowns(usrData, "state", "STATE");
		waitForSpinner();
		addRetailerpageDropDowns(usrData, "district", "DISTRICT");
		waitForSpinner();
		addRetailerpageDropDowns(usrData, "subDistrict", "SUBDISTRICT");
		waitForSpinner();
		addRetailerpageDropDowns(usrData, "vtc", "VTC");
		wait.until(ExpectedConditions.visibilityOf(pincode));
		Log.info("entering pincode");
		pincode.sendKeys(usrData.get("PINCODE"));
		pressTab();
		addRetailerpageDropDowns(usrData, "region", "REGION");
		waitForSpinner();
		addRetailerpageDropDowns(usrData, "area", "AREA");
		waitForSpinner();
		addRetailerpageDropDowns(usrData, "territory", "TERRITORY");
		Log.info("entering Date ofBirth");
		dateOfBirth.click();
		wait.until(ExpectedConditions.visibilityOf(datePicker));
		datePicker.click();
		email.sendKeys(usrData.get("EMAIL"));
		String genderId = "gender_" + usrData.get("GENDER").toLowerCase();
		WebElement gender = wdriver.findElement(By.id(genderId));
		gender.click();
		addRetailerpageDropDowns(usrData, "idType", "IDPROOFTYPE");
		Log.info("entering ID proof number");
		if (panNo.isDisplayed()) {
			panNo.sendKeys(usrData.get("IDNUMBER"));
		} else if (aadhaarNo.isDisplayed()) {
			aadhaarNo.sendKeys(usrData.get("IDNUMBER"));
		} else {
			idProof.sendKeys(usrData.get("IDNUMBER"));
		}
		pressTab();
		addRetailerpageDropDowns(usrData, "settlementBank", "SETTLEMENTBANK");
		addRetailerpageDropDowns(usrData, "settlementMode", "SETTLEMENTMODE");
		Log.info("entering account number");
		settlementAcNo.sendKeys(usrData.get("SETTLEMENTACNUMBER"));
		Log.info("entering account holder name");
		settlementAcHolderName.sendKeys(usrData.get("SETTLEMENTACHOLDERNAME"));
		Log.info("entering IFSC Code");
		settlementIFSC.sendKeys(usrData.get("IFSCCODE"));
		String onboardingFeesPaidId = "onboardingFeesPaid_" + usrData.get("FEESPAID").toLowerCase();
		WebElement onboardingFeesPaid = wdriver.findElement(By.id(onboardingFeesPaidId));
		Log.info("selecting '" + usrData.get("FEESPAID") + "'");
		onboardingFeesPaid.click();
		createRetailer.click();

		String mob = getStoredMobileFromIni();
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			Log.info("Retailer with mobile number " + mob + " is created");
			waitForSpinner();
			wait.until(ExpectedConditions.visibilityOf(pageTitle));
			Log.info(pageTitle.getText() + " page displayed");
		} catch (Exception e) {
			Log.info("Retailer with mobile number " + mob + " couldn't be created due to below error:");
			Log.info(errorMessage.getText());
		}
	}

	public void addRetailerpageDropDowns(Map<String, String> usrData, String dd, String columnData)
			throws AWTException {
		WebElement dropdown = wdriver.findElement(By.xpath("//*[@id='" + dd + "_chosen']/a"));
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Log.info("clicking on " + dropdown.getText() + " drop down");
		Actions action = new Actions(wdriver);
		action.moveToElement(dropdown).build().perform();
		dropdown.click();
		WebElement dropdownInput = wdriver.findElement(By.xpath("//*[@id='" + dd + "_chosen']/div/div/input"));
		wait.until(ExpectedConditions.visibilityOf(dropdownInput));
		Log.info("selecting '" + usrData.get(columnData) + "'");
		dropdownInput.sendKeys(usrData.get(columnData));
		pressEnter();
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
