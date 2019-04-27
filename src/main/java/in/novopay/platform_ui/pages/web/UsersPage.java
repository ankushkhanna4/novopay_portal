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
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;

public class UsersPage extends BasePage {

	DBUtils dbUtils = new DBUtils();
	WebDriverWait wait = new WebDriverWait(wdriver, 30);

	@FindBy(xpath = "//*[@id='btnAddUser']")
	WebElement addUser;

	@FindBy(xpath = "//*[@id='userListContent']//h2")
	WebElement usersList;

	@FindBy(xpath = "//*[@id='userForm']//h2")
	WebElement userDetails;

	@FindBy(xpath = "//*[contains(text(),'Choose a organization...')]")
	WebElement orgDropdown;

	@FindBy(xpath = "//*[contains(text(),'Choose a organization...')]/parent::a/following-sibling::div/div/input")
	WebElement orgDropDownInput;
	
	@FindBy(xpath = "//*[@id='organization']")
	WebElement orgTextBox;

	@FindBy(xpath = "//*[contains(text(),'Choose a organization...')]/parent::a/following-sibling::div//li")
	WebElement orgDropDownName;

	@FindBy(xpath = "//*[@id='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//*[@value='MALE']/parent::label")
	WebElement male;

	@FindBy(xpath = "//*[@value='FEMALE']/parent::label")
	WebElement female;

	@FindBy(xpath = "//*[@id='ALLOW_DEVICE_REGISTRATION']")
	WebElement deviceReg;

	@FindBy(xpath = "//*[@id='MSISDN']")
	WebElement phoneNumber;

	@FindBy(xpath = "//*[@id='oAddressLineOne']")
	WebElement addressLine1;

	@FindBy(xpath = "//*[@id='oAddressLineTwo']")
	WebElement addressLine2;

	@FindBy(xpath = "//*[@id='oAddressLineThree']")
	WebElement addressLine3;

	@FindBy(xpath = "//*[contains(text(),'Choose a state...')]")
	WebElement stateDropDown;

	@FindBy(xpath = "//*[contains(text(),'Choose a state...')]/parent::a/following-sibling::div/div/input")
	WebElement stateDropDownInput;

	@FindBy(xpath = "//*[contains(text(),'Choose a state...')]/parent::a/following-sibling::div//li")
	WebElement stateDropDownName;

	@FindBy(xpath = "//*[contains(text(),'Choose a district...')]")
	WebElement districtDropDown;

	@FindBy(xpath = "//*[contains(text(),'Choose a district...')]/parent::a/following-sibling::div/div/input")
	WebElement districtDropDownInput;

	@FindBy(xpath = "//*[contains(text(),'Choose a district...')]/parent::a/following-sibling::div//li")
	WebElement districtDropDownName;

	@FindBy(xpath = "//*[contains(text(),'Choose a subdistrict...')]")
	WebElement subdistrictDropDown;

	@FindBy(xpath = "//*[contains(text(),'Choose a subdistrict...')]/parent::a/following-sibling::div/div/input")
	WebElement subdistrictDropDownInput;

	@FindBy(xpath = "//*[contains(text(),'Choose a subdistrict...')]/parent::a/following-sibling::div//li")
	WebElement subdistrictDropDownName;

	@FindBy(xpath = "//*[contains(text(),'Choose a city...')]")
	WebElement cityDropDown;

	@FindBy(xpath = "//*[contains(text(),'Choose a city...')]/parent::a/following-sibling::div/div/input")
	WebElement cityDropDownInput;

	@FindBy(xpath = "//*[contains(text(),'Choose a city...')]/parent::a/following-sibling::div//li")
	WebElement cityDropDownName;

	@FindBy(xpath = "//*[@id='oPinCode']")
	WebElement pinCode;

	@FindBy(xpath = "//*[@id='saveButton']")
	WebElement saveButton;

	@FindBy(xpath = "//*[@id='comments']")
	WebElement comments;

	@FindBy(xpath = "//*[@id='approveButton']")
	WebElement approveButton;

	@FindBy(xpath = "//*[@id='rejectButton']")
	WebElement rejectButton;

	@FindBy(xpath = "//*[@id='submit-dialog']//span[contains(@class,'fa-spinner')]/parent::div/parent::div")
	WebElement submitMessage;
	
	@FindBy(xpath = "//*[@id='success-dialog']//span[contains(@class,'fa-spinner')]/parent::div/parent::div")
	WebElement successMessage;

	// Load all objects in HomePage
	public UsersPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void users(Map<String, String> usrData) throws InterruptedException, AWTException, ClassNotFoundException {
		if (usrData.get("ACTION").equalsIgnoreCase("CREATE")) {
			wait.until(ExpectedConditions.elementToBeClickable(addUser));
			clickInvisibleElement(addUser);
			Log.info("Add user button clicked");
			usersDetails(usrData);
			waitForSpinner();
			successMessage();
			wait.until(ExpectedConditions.visibilityOf(usersList));
			Log.info(usersList.getText() + " page displayed");
		}
		if (usrData.get("ACTION").equalsIgnoreCase("APPROVE")) {
			actionButton(getMobileNumberFromIni("GetNum"));
			usersDetailsAction(usrData);
			successMessage();
			wait.until(ExpectedConditions.visibilityOf(usersList));
			Log.info(usersList.getText() + " page displayed");
			if (usrData.get("UPDATEMPIN").equalsIgnoreCase("YES")) {
				dbUtils.updateMPIN(getMobileNumberFromIni("GetNum"));
			}
		}
	}

	public void usersDetails(Map<String, String> usrData) {
		wait.until(ExpectedConditions.visibilityOf(userDetails));
		Log.info(userDetails.getText() + " page displayed");
		/*wait.until(ExpectedConditions.visibilityOf(orgDropdown));
		orgDropdown.click();
		wait.until(ExpectedConditions.visibilityOf(orgDropDownInput));
		orgDropDownInput.click();
		orgDropDownInput.sendKeys(usrData.get("ORGANIZATION"));
		wait.until(ExpectedConditions.visibilityOf(orgDropDownName));
		orgDropDownName.click();*/
		wait.until(ExpectedConditions.visibilityOf(orgTextBox));
		orgTextBox.click();
		orgTextBox.sendKeys(getNameFromIni(usrData.get("ORGANIZATION")));
		wait.until(ExpectedConditions.visibilityOf(firstName));
		firstName.click();
		firstName.sendKeys(getNameFromIni(usrData.get("FIRSTNAME")));
		if (usrData.get("GENDER").equalsIgnoreCase("MALE")) {
			male.click();
		} else {
			female.click();
		}
		wait.until(ExpectedConditions.visibilityOf(deviceReg));
		deviceReg.click();
		deviceReg.sendKeys(usrData.get("DEVICEREG"));
		wait.until(ExpectedConditions.visibilityOf(phoneNumber));
		phoneNumber.click();
		phoneNumber.sendKeys(getMobileNumberFromIni(usrData.get("PHONENUMBER")));
		wait.until(ExpectedConditions.visibilityOf(addressLine1));
		addressLine1.click();
		addressLine1.sendKeys(usrData.get("ADDRESSLINE1"));
		wait.until(ExpectedConditions.visibilityOf(addressLine2));
		addressLine2.click();
		addressLine2.sendKeys(usrData.get("ADDRESSLINE2"));
		wait.until(ExpectedConditions.visibilityOf(addressLine3));
		addressLine3.click();
		addressLine3.sendKeys(usrData.get("ADDRESSLINE3"));
		wait.until(ExpectedConditions.visibilityOf(stateDropDown));
		stateDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(stateDropDownInput));
		stateDropDownInput.click();
		// stateDropDownInput.sendKeys(usrData.get("STATE"));
		wait.until(ExpectedConditions.visibilityOf(stateDropDownName));
		stateDropDownName.click();
		wait.until(ExpectedConditions.visibilityOf(districtDropDown));
		districtDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(districtDropDownInput));
		districtDropDownInput.click();
		// districtDropDownInput.sendKeys(usrData.get("DISTRICT"));
		wait.until(ExpectedConditions.visibilityOf(districtDropDownName));
		districtDropDownName.click();
		wait.until(ExpectedConditions.visibilityOf(subdistrictDropDown));
		subdistrictDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(subdistrictDropDownInput));
		subdistrictDropDownInput.click();
		// subdistrictDropDownInput.sendKeys(usrData.get("SUBDISTRICT"));
		wait.until(ExpectedConditions.visibilityOf(subdistrictDropDownName));
		subdistrictDropDownName.click();
		wait.until(ExpectedConditions.visibilityOf(cityDropDown));
		cityDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(cityDropDownInput));
		cityDropDownInput.click();
		// cityDropDownInput.sendKeys(usrData.get("CITY"));
		wait.until(ExpectedConditions.visibilityOf(cityDropDownName));
		cityDropDownName.click();
		wait.until(ExpectedConditions.visibilityOf(pinCode));
		pinCode.click();
		pinCode.sendKeys(usrData.get("PINCODE"));
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
	}

	public void usersDetailsAction(Map<String, String> usrData) {
		wait.until(ExpectedConditions.visibilityOf(userDetails));
		Log.info(userDetails.getText() + " page displayed");
		wait.until(ExpectedConditions.visibilityOf(comments));
		clickInvisibleElement(comments);
		comments.sendKeys(usrData.get("COMMENTS"));
		Log.info("Comments entered");
		if (usrData.get("ACTION").equalsIgnoreCase("APPROVE")) {
			wait.until(ExpectedConditions.visibilityOf(approveButton));
			approveButton.click();
			Log.info("Approve button clicked");
		} else if (usrData.get("ACTION").equalsIgnoreCase("REJECT")) {
			wait.until(ExpectedConditions.visibilityOf(rejectButton));
			rejectButton.click();
			Log.info("Reject button clicked");
		}
	}

	public void actionButton(String mobNum) {
		String actionbuttonXpath = "//*[contains(text(),'" + mobNum
				+ "')]/parent::td/following-sibling::td/span/a[contains(@id,'showUserDetails')]";
		WebElement actionButtonElement = wdriver.findElement(By.xpath(actionbuttonXpath));
		wait.until(ExpectedConditions.visibilityOf(actionButtonElement));
		clickInvisibleElement(actionButtonElement);
	}

	public void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) wdriver;
		jse.executeScript("scroll(0, 250);");
	}

	public void waitForSpinner() {
//		wait.until(ExpectedConditions.visibilityOf(submitMessage));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("submit-dialog")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(
//				By.xpath("//*[@id='submit-dialog']//span[contains(@class,'fa-spinner')]/parent::div/parent::div")));
		Log.info("Please Wait...");
	}
	
	public void successMessage() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOf(successMessage));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("success-dialog")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(
//				By.xpath("//*[@id='success-dialog']//span[contains(@class,'fa-spinner')]/parent::div/parent::div")));
		Log.info("Success message displayed");
		Thread.sleep(5000);
	}
}
