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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.Log;

public class RetailerDocumentsPage extends BasePage {

	ReadDataFromExcel readData = new ReadDataFromExcel();

	WebDriverWait wait = new WebDriverWait(wdriver, 30);

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/h1")
	WebElement pageTitle;

	@FindBy(xpath = "//button")
	WebElement sendDocumentsButton;

	@FindBy(xpath = "//div[contains(@class,'noty_type_')]")
	WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'noty_type_')]")
	WebElement errorMessage;

	// Load all objects in HomePage
	public RetailerDocumentsPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void retailerDocuments(Map<String, String> usrData) throws InterruptedException, AWTException, IOException {

		if (usrData.get("PANCARD").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 2, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 2, 22));
		}
		if (usrData.get("AADHAARCARD").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 3, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 3, 22));
		}
		if (usrData.get("SHOPESTACTDOC").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 4, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 4, 22));
		}
		if (usrData.get("NPAGREEMENT").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 5, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 5, 22));
		}
		if (usrData.get("RETONBOARDINGFORM").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 6, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 6, 22));
		}
		if (usrData.get("AFFIDAVITFORM").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 7, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 7, 22));
		}
		if (usrData.get("POA").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 8, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 8, 22));
		}
		if (usrData.get("LOCALINTFORM").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 9, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 9, 22));
		}
		if (usrData.get("IDPROOFPAGES").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 10, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 10, 22));
		}
		if (usrData.get("SHOPESTLICENSE").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 11, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 11, 22));
		}
		if (usrData.get("M/STURNOVERSURROGATEDOC").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 12, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 12, 22));
		}
		if (usrData.get("OWNERPHOTO").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 13, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 13, 22));
		}
		if (usrData.get("CANCELLEDCHEQUE").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 14, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 14, 22));
		}
		if (usrData.get("SHOPPHOTO").equalsIgnoreCase("YES")) {
			uploadFile(readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 15, 23),
					readData.readExcel("./test-data", "UITestData.xlsx", "RetailerDocumentsPage", 15, 22));
		}
//		Thread.sleep(500);
//
//		for (int i = 0; i < 5; i++) {
//			pageUp();
//		}
//		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(sendDocumentsButton));
		Log.info("clicking on Send for Documents Verification button");
		clickInvisibleElement(sendDocumentsButton);

		String mob = getStoredMobileFromIni();
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			Log.info("Retailer with mobile number " + mob + " is pending onboarded");
			waitForSpinner();
			wait.until(ExpectedConditions.visibilityOf(pageTitle));
			Log.info(pageTitle.getText() + " page displayed");
		} catch (Exception e) {
			Log.info("Retailer with mobile number " + mob + " couldn't be pending onboarded due to below error:");
			Log.info(errorMessage.getText());
		}
	}

	public void uploadFile(String docID, String docName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(wdriver.findElement(By.id(docID))));
		Log.info("selecting document for " + docName);
		WebElement docInput = wdriver.findElement(By.xpath("//*[@id='"+docID+"']/parent::legend/input"));
		docInput.sendKeys("C:/Users/ANKUSH/Pictures/RetailerDocuments/" + docName + ".jpg");
		Log.info(docName + " document selected");
//		Thread.sleep(1000);
		String uploadXPath = "//*[@id='" + docID
				+ "']/parent::legend/following-sibling::div//span[contains(@class,'upload')]";
		WebElement upload = wdriver.findElement(By.xpath(uploadXPath));
		wait.until(ExpectedConditions.elementToBeClickable(upload));
		Log.info("uploading " + docName);
		upload.click();
		waitForSpinner();
//		uploadFileStatus();
	}

	public void uploadFileStatus() {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			Log.info("File uploaded");
			waitForSpinner();
		} catch (Exception e) {
			Log.info("File could not be uploaded due to below error:");
			Log.info(errorMessage.getText());
		}
	}

	public void waitForSpinner() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-container']")));
		Log.info("spinner load complete");
	}

	public void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) wdriver;
		jse.executeScript("scroll(0, 250);");
	}

	public void pageDown() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public void pageUp() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	
	public void AutoIt(WebElement idProofPages, WebElement uploadIdProof) throws InterruptedException, IOException {
	wait.until(ExpectedConditions.elementToBeClickable(idProofPages));
	Log.info("selecting files for Id Proof Pages");
	idProofPages.click();
	Thread.sleep(500);
	String uploadFile = "C:\\Users\\Ankush\\Documents\\AutoIt Scripts\\UploadFile.exe";
	Runtime.getRuntime().exec(uploadFile);
	Thread.sleep(500);
	wait.until(ExpectedConditions.elementToBeClickable(uploadIdProof));
	Log.info("uploading Id Proof Pages");
	uploadIdProof.click();

	uploadFileStatus();
	}

}
