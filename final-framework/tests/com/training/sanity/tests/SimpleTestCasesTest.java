package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.GeneratePassTest;
import com.training.pom.LogOutAdminTest;
import com.training.pom.ProfileEdtTest;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SimpleTestCasesTest {

	private WebDriver driver; 
	private String baseUrl; 
	private ProfileEdtTest profileEdt ; 
	private GeneratePassTest generatePassTest;
	private LogOutAdminTest logOutAdminTest;
	private static Properties properties;
	private ScreenShot screenShot; 
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		profileEdt = new ProfileEdtTest(driver); 
		generatePassTest = new GeneratePassTest(driver);
		logOutAdminTest = new LogOutAdminTest(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test(priority=1)
	//To change last name and phone number
	public void profileLNPNEdt() {
			profileEdt.clickloginBtn();	
			profileEdt.sendUserName("admin");
			profileEdt.sendPasswd("adminuser@12345");
			profileEdt.clickSignINBtn();
			profileEdt.clickUserLink();
			profileEdt.clickMyProfileLnk();
			profileEdt.lastNameEdt("TestName");
			profileEdt.phoneNumEdt("9535514011");
			profileEdt.updateProfileBtn();
			profileEdt.verifySuccessMsg();
			screenShot.captureScreenShot("First");
	}
	
	@Test(priority=2)
	//To generate new password
	public void generatePassWd() {
			profileEdt.clickloginBtn();	
			profileEdt.sendUserName("admin");
			profileEdt.sendPasswd("adminuser@12345");
			profileEdt.clickSignINBtn();
			profileEdt.clickUserLink();
			profileEdt.clickMyProfileLnk();
			generatePassTest.generatePassLnk();
			generatePassTest.generateNewPass("adminuser@12345");
			generatePassTest.passStrength();
			profileEdt.updateProfileBtn();
			generatePassTest.verifySuccessMsg();
			screenShot.captureScreenShot("First");
	}
	
	@Test(priority=3)
	//To Check logout functionality
	public void logOutAdmin() {
			profileEdt.clickloginBtn();	
			profileEdt.sendUserName("admin");
			profileEdt.sendPasswd("adminuser@12345");
			profileEdt.clickSignINBtn();
			profileEdt.clickUserLink();
			logOutAdminTest.logOutLnk();
			logOutAdminTest.backToLoginPage();
			screenShot.captureScreenShot("First");
	}	
}