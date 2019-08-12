package com.training.sanity.tests;

import static org.testng.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.AddNewFeatureAndNewProperty;
import com.training.pom.AddNewRegionAndNewProperty;
import com.training.pom.AddNewProperty;
import com.training.pom.RealEstateGenericMethods;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MediumTestCases {

	private WebDriver driver; 
	private String baseUrl; 
	private AddNewProperty addNewProperty ; 
	private AddNewFeatureAndNewProperty addNewFeature;
	private AddNewRegionAndNewProperty addNewPropRegion;
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
		addNewProperty = new AddNewProperty(driver); 
		addNewFeature = new AddNewFeatureAndNewProperty(driver);
		addNewPropRegion = new AddNewRegionAndNewProperty(driver);
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
	
	String propertyDetail = "This property is just for testing purpose. All the details of this property are dummy test data";
	String propertyTitle = "Test Property";
	
	@Test(priority=1)
	//To add new property to the real estate site and verifying the post. Test case : RETC_043
	public void addNewProperty() {
		try {
			//Login as admin
			genericMethods.clickloginBtn();
			genericMethods.sendUserName("admin");
			genericMethods.sendPasswd("adminuser@123");
			genericMethods.clickSignINBtn();
			//Delete existing property in the same name
			addNewProperty.clickPropertyLnk();
			addNewProperty.checkPropDelete();
			//Adding new property and publishing it
			addNewProperty.clickAddNewLnk();
			assertTrue(addNewProperty.confirmPageTtle());
			addNewProperty.sendTitleTxt(propertyTitle);
			addNewProperty.sendDetailTxt(propertyDetail);
			addNewProperty.clickPublishBtn();
			//Confirm success message and verify added property details
			assertTrue(addNewProperty.verifySuccessMsg().contains("Post published. "));
			addNewProperty.clickViewPost();
			assertTrue(addNewProperty.verifyPostTitle().contains("Test Property"));
			Assert.assertEquals(addNewProperty.verifypostDescription(), propertyDetail);
			System.out.println("RETC_043 : Added new property Sucessfully");
			screenShot.captureScreenShot("RETC_043");
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		}
		
		@Test(priority=2)
//Add new feature and add new property to the real estate site by selecting newly added feature. Test case : RETC_044
		public void addNewPropWithNewFeature() {
			try {
				String featureName = "New Launches Test";
				String featureDesc = "New Launches of villas, apartments, flats";
				String featureSlug = "launch";
				//Login as admin
				genericMethods.clickloginBtn();
				genericMethods.sendUserName("admin");
				genericMethods.sendPasswd("adminuser@123");
				genericMethods.clickSignINBtn();
				//Delete existing property in the same name
				addNewProperty.clickPropertyLnk();
				addNewProperty.checkPropDelete();
				//Checking features page and deleting existing feature in the same name
				addNewFeature.clickFeaturesLnk();
				assertTrue(addNewFeature.confirmPageTtle());
				assertTrue(addNewFeature.checkAddFeatureForm());
				assertTrue(addNewFeature.checkExistingFeatureForm());
				addNewFeature.deleteExistingFeatureNme(addNewFeature.verifyExistingFeatureNme());
				//Adding new feature
				addNewFeature.sendNameTxt(featureName);
				addNewFeature.sendSlugTxt(featureSlug);
				addNewFeature.sendDescTxt(featureDesc);
				addNewFeature.clickAddNewFeatureBtn();
				//Verifying newly added feature
				Assert.assertEquals(addNewFeature.verifyAddedFeatureName(), featureName);
				Assert.assertEquals(addNewFeature.verifyAddedFeatureSlug(), featureSlug);
				Assert.assertEquals(addNewFeature.verifyAddedFeatureDesc(), featureDesc);
				//Adding new property
				addNewProperty.clickAddNewLnk();
				assertTrue(addNewProperty.confirmPageTtle());
				addNewProperty.sendTitleTxt(propertyTitle);
				addNewProperty.sendDetailTxt(propertyDetail);
				//Selecting the new feature added above
				Assert.assertEquals(addNewFeature.selectAddedFeature(),true);
				//Publishing the property and checking success message
				GenericMethods.jsScrollDown(driver);
				addNewProperty.clickPublishBtn();
				assertTrue(addNewProperty.verifySuccessMsg().contains("Post published. "));
				System.out.println("RETC_044 : Added new property with newly added feature");
				screenShot.captureScreenShot("RETC_044");
			}catch(Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
	}
		
		@Test(priority=3)
//Add new region and add new property to the real estate site by selecting newly added region. Test case : RETC_045
		public void addNewPropWithNewRegion() {
			try {
				String regionName = "Electronic city Test";
				String regionDesc = "New Launches of villas, apartments, flats";
				String regionSlug = "electronic-city-test";
				//Login as admin
				genericMethods.clickloginBtn();
				genericMethods.sendUserName("admin");
				genericMethods.sendPasswd("adminuser@123");
				genericMethods.clickSignINBtn();
				//Delete existing property in the same name
				addNewProperty.clickPropertyLnk();
				addNewProperty.checkPropDelete();
				//Checking region page and deleting existing region in the same name
				addNewPropRegion.clickRegionsLnk();
				assertTrue(addNewPropRegion.confirmPageTtle());
				assertTrue(addNewPropRegion.checkAddRegionForm());
				assertTrue(addNewPropRegion.checkExistingRegionForm());
				addNewPropRegion.deleteExistingRegionNme(addNewPropRegion.verifyExistingRegionNme());
				//Adding new region
				addNewPropRegion.sendNametxt(regionName);
				addNewPropRegion.sendSlugtxt(regionSlug);
				addNewPropRegion.sendDesctxt(regionDesc);
				addNewPropRegion.clickAddNewRegionBtn();
				//Verifying newly added region
				Assert.assertEquals(addNewPropRegion.verifyaddedRegionName(), regionName);
				Assert.assertEquals(addNewPropRegion.verifyaddedRegionSlug(), regionSlug);
				Assert.assertEquals(addNewPropRegion.verifyaddedRegionDesc(), regionDesc);
				//Adding new property
				addNewProperty.clickAddNewLnk();
				assertTrue(addNewProperty.confirmPageTtle());
				addNewProperty.sendTitleTxt(propertyTitle);
				addNewProperty.sendDetailTxt(propertyDetail);
				//Selecting the new region added above
				Assert.assertEquals(addNewPropRegion.selectAddedRegion(),true);
				//Publishing the property and checking success message
				GenericMethods.jsScrollDown(driver);
				addNewProperty.clickPublishBtn();
				assertTrue(addNewProperty.verifySuccessMsg().contains("Post published. "));
				System.out.println("RETC_045 : Added new property with newly added region");
				screenShot.captureScreenShot("RETC_045");
			}catch(Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
	}
}