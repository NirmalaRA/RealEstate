package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.GenerateNewPassWdTest;
import com.training.pom.LogOutAdminTest;
import com.training.pom.ProfileEdtTest;
import com.training.pom.RealEstateGenericMethods;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SimpleTestCasesTest {

	private WebDriver driver; 
	private String baseUrl; 
	private ProfileEdtTest profileEdt ; 
	private GenerateNewPassWdTest generatePassTest;
	private LogOutAdminTest logOutAdminTest;
	private RealEstateGenericMethods genericMethods;
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
		generatePassTest = new GenerateNewPassWdTest(driver);
		logOutAdminTest = new LogOutAdminTest(driver);
		genericMethods = new RealEstateGenericMethods(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		//Thread.sleep(3000);
		driver.quit();
	}
	
	@Test(priority=1)
	//To change last name and phone number --> Test case : RETC_013
	public void profileLNPNEdt() {
			genericMethods.clickloginBtn();	
			genericMethods.sendUserName("admin");
			genericMethods.sendPasswd("adminuser@12345");
			genericMethods.clickSignINBtn();
			profileEdt.clickUserLink();
			profileEdt.verifyUserOptions();
			Assert.assertEquals(3,profileEdt.verifyUserOptions());
			profileEdt.clickMyProfileLnk();
			Assert.assertEquals(profileEdt.confirmMyProfilePage(),"Profile");
			Assert.assertEquals(profileEdt.confirmUserName(),"admin");
			profileEdt.lastNameEdt("manzoor"); 
			profileEdt.phoneNumEdt("9876543210");
			profileEdt.updateProfileBtn();
			profileEdt.verifySuccessMsg();
			Assert.assertEquals(profileEdt.verifySuccessMsg(),"Profile updated.");
			System.out.println("Test case : RETC_013 is passed");
			screenShot.captureScreenShot("RETC_013");
	}
	
	@Test(priority=2)
	//To generate new password --> Test cases : RETC_014
	public void generatePassWd() {
			genericMethods.clickloginBtn();	
			genericMethods.sendUserName("admin");
			genericMethods.sendPasswd("adminuser@12345");
			genericMethods.clickSignINBtn();
			profileEdt.clickUserLink();
			profileEdt.verifyUserOptions();
			Assert.assertEquals(3,profileEdt.verifyUserOptions());
			profileEdt.clickMyProfileLnk();
			Assert.assertEquals(profileEdt.confirmMyProfilePage(),"Profile");
			Assert.assertEquals(profileEdt.confirmUserName(),"admin");
			generatePassTest.generatePassLnk();
			generatePassTest.generateNewPass("adminuser@12345");
			generatePassTest.passStrength();
			profileEdt.updateProfileBtn();
			profileEdt.verifySuccessMsg();
			Assert.assertEquals(profileEdt.verifySuccessMsg(),"Profile updated.");
			System.out.println("Test case : RETC_014 is passed");
			screenShot.captureScreenShot("RETC_014");
	}
	
	@Test(priority=3)
	//To Check logout functionality --> Test Case : RETC_015
	public void logOutAdmin() {
			genericMethods.clickloginBtn();
			genericMethods.checkLoginForm();
			genericMethods.sendUserName("admin");
			genericMethods.sendPasswd("adminuser@12345");
			genericMethods.clickSignINBtn();
			genericMethods.checkDashBoard();
			profileEdt.clickUserLink();
			profileEdt.verifyUserOptions();
			logOutAdminTest.logOutLnk();	
			genericMethods.checkLoginForm();
			System.out.println("Test case : RETC_015 is passed");
			screenShot.captureScreenShot("RETC_015");
	}	
}