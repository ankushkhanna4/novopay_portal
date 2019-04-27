	
package tests;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.JavaUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FlowMapper {
	public AndroidDriver<MobileElement> mdriver;
	public WebDriver wdriver;
	private String sheetName = "FLOWMAPPER";
	private JavaUtils javaUtils = new JavaUtils();
	private Map<String, String> usrData;
	private Object obj;
	private String errMsg;
	private String testCaseID = "";
	private String stepNo = "";
	private String className = "";
	private String currentPackage = "";
	private String classNameWithPackage, workbook = "UITestData", pack;
	private Set<String> flows;
	private BasePage wBasePage = new BasePage(wdriver);
	

	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
		System.out.println(System.currentTimeMillis());
	}

	@Test(dataProvider = "getData")
	public void flowMapperTest(HashMap<String, String> usrData) throws Throwable {
		this.usrData = usrData;
		System.out.println("Excuting flow: " + usrData.get("TCID"));
		for (String flowTestID : flows) {
			if ((!usrData.get(flowTestID).equalsIgnoreCase("SKIP")) && (!usrData.get(flowTestID).isEmpty())) {
				testCaseID = usrData.get(flowTestID);
				currentPackage = getClass().getPackage().getName();
				className = testCaseID.split("_")[0];

				classNameWithPackage = currentPackage + ".api." + className;
				Class<?> flow = null;
				stepNo=flowTestID;
				try {
					flow = Class.forName(classNameWithPackage);
					pack = "api";
				} catch (ClassNotFoundException e) {
					classNameWithPackage = currentPackage + ".ui." + className;
					flow = Class.forName(classNameWithPackage);
					pack = "ui";
				}

				String pattern = Character.toLowerCase((className + "Test").charAt(0))
						+ (className + "Test").substring(1, (className).length());
				Pattern r = Pattern.compile(pattern);

				try {
					obj = flow.newInstance();
					Method[] method = obj.getClass().getDeclaredMethods();
					for (int i = 0; i < method.length; i++) {
						String message = method[i].toString();
						Matcher m = r.matcher(message);
						if (m.find()) {
							String sheetname = obj.getClass().getDeclaredField("sheetname").get(obj).toString();
							String workbook = obj.getClass().getDeclaredField("workbook").get(obj).toString();
							// write if condition to switch if api class is
							if (pack.equals("api")) {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));
								method[i].invoke(obj, data);
							} else {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));

								Field mobileDriver = obj.getClass().getDeclaredField("mdriver");
								Field webDriver = obj.getClass().getDeclaredField("wdriver");
								mobileDriver.set(obj, mdriver);
								webDriver.set(obj, wdriver);
							
								method[i].invoke(obj, data);
								
								mdriver = (AndroidDriver<MobileElement>) mobileDriver.get(obj);
								wdriver = (WebDriver) webDriver.get(obj);
							}

						}
					}

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (Exception e) {
					throw e.getCause();
				}
			}
		}
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] data = javaUtils.returnAllUniqueValuesInMap(workbook, sheetName, "no-check");
		if (data.length != 0) {
			
			HashMap<String, String> datamap = (HashMap<String, String>) data[0][0];
			flows=new TreeSet<>(datamap.keySet());
			flows = new TreeSet<>(datamap.keySet().stream().filter(s -> s.toLowerCase().startsWith("step"))
					.collect(Collectors.toSet()));
		}
		return data;
	}

	@AfterClass
	public void killDriver() {

		if (wdriver != null) {
			wBasePage.closeBrowser();
		}
	}

	// STORING EXECUTION RESULTS IN EXCEL
	@AfterMethod
	public void result(ITestResult result) throws InvalidFormatException, IOException {

		String failureReason = "";
		String clientBuildNumber = "Novopay Retail WebApp";
		String testStartTime=javaUtils.getTestExcutionTime(result.getStartMillis());
		String testEndTime=javaUtils.getTestExcutionTime(result.getEndMillis());
		wBasePage.captureScreenshotOnFailedTest(result, testCaseID);
		if (!result.isSuccess()) {
			failureReason = errMsg;
			failureReason = "Failed in: "+stepNo+": "+testCaseID+": "+result.getThrowable() + "";
		}
		String[] execeutionDtls = { JavaUtils.buildNo, clientBuildNumber, usrData.get("DESCRIPTION"),
				"FLOWMAPPER ID : " + usrData.get("TCID"), usrData.get("DESCRIPTION"),
				javaUtils.getExecutionResultStatus(result.getStatus()), failureReason, testStartTime, testEndTime };
		javaUtils.writeExecutionStatusToExcel(execeutionDtls);
	}
}