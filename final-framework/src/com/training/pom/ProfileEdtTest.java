package com.training.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileEdtTest {
		private WebDriver driver; 			
		public ProfileEdtTest(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
			
		//Moving mouse pointer to User link
		@FindBy(xpath="//li[@id='wp-admin-bar-my-account']/a[@href='http://realestate.upskills.in/wp-admin/profile.php']")
		private WebElement userLnk; 	
		public void clickUserLink() {
		Actions act = new Actions(driver);
		act.moveToElement(userLnk).build().perform();
		}
		
		//Checking options under User
		@FindAll({
		@FindBy(id="wp-admin-bar-user-info"),
		@FindBy(id="wp-admin-bar-edit-profile"),
		@FindBy(id="wp-admin-bar-logout")
		})
		private List<WebElement> userOptions;
		public int verifyUserOptions() {
			int numOfUserOptions = userOptions.size();
			return numOfUserOptions;
		}

		//Clicking on Edit My Profile
		@FindBy(linkText="Edit My Profile")
		private WebElement myProfileLnk; 	
		public void clickMyProfileLnk() {
			this.myProfileLnk.click();
		}
		
		//Confirming my profile page title
		@FindBy(xpath="//div[@id='profile-page']//h1")
		private WebElement profilePageTtle;
			public String confirmMyProfilePage() {
				this.profilePageTtle.isDisplayed();
				String profilePageTitle = profilePageTtle.getText();
				return profilePageTitle;
		}
		
		//Confirming logged in user name
		@FindBy(xpath="//tr[@class='user-user-login-wrap']//input[@value='admin']")
		private WebElement confirmUserName;
		public String confirmUserName() {
			String userName = confirmUserName.getAttribute("value");
			return userName;
		}
		
		//Editing last name
		@FindBy(id="last_name")
		private WebElement lastName; 
		public void lastNameEdt(String lastName) {
			this.lastName.clear(); 
			this.lastName.sendKeys(lastName);
		}
		
		//Editing phone number
		@FindBy(id="phone")
		private WebElement phoneNum; 
		public void phoneNumEdt(String phoneNum) {
			this.phoneNum.clear(); 
			this.phoneNum.sendKeys(phoneNum); 
		}
		
		//Clicking on update profile button
		@FindBy(id="submit")
		private WebElement updateProfile; 
		public void updateProfileBtn() {
			this.updateProfile.click();
		}
		
		//Checking update success message
		@FindBy(xpath="//div[@id='message']//strong")
		private WebElement successMsg;
		public String verifySuccessMsg() {
			String successMessage = successMsg.getText();
			return successMessage;
		}	
	}