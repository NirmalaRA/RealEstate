package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProfileEdtTest {
		private WebDriver driver; 			
		public ProfileEdtTest(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//Clicking on Login/SignIn button
		@FindBy(xpath="//a[@class ='sign-in']")
		private WebElement loginBtn; 
		public void clickloginBtn() {
			this.loginBtn.click();;
		}
		
		//Entering user name
		@FindBy(id="user_login")
		private WebElement userName;
		public void sendUserName(String userName) {
			this.userName.clear(); 
			this.userName.sendKeys(userName); 
		}
		
		//Entering password
		@FindBy(id="user_pass")
		private WebElement passWord;	
		public void sendPasswd(String passWord) {
			this.passWord.clear(); 
			this.passWord.sendKeys(passWord); 
		}
		
		//Submitting login credentials
		@FindBy(xpath="//input[@name='login']")
		private WebElement signInBtn; 
		public void clickSignINBtn() {
			this.signInBtn.click();
		}
		
		//Moving mouse pointer to User link
		@FindBy(xpath="//li[@id='wp-admin-bar-my-account']/a[@href='http://realestate.upskills.in/wp-admin/profile.php']")
		private WebElement userLnk; 	
		public void clickUserLink() {
		Actions act = new Actions(driver);
		act.moveToElement(userLnk).build().perform();
		}
		
		//Clicking on Edit My Profile
		@FindBy(linkText="Edit My Profile")
		private WebElement myProfileLnk; 	
		public void clickMyProfileLnk() {
			this.myProfileLnk.click();
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
		public void verifySuccessMsg() {
		Assert.assertEquals("Profile updated.", successMsg.getText());
		System.out.println("LastName and PhoneNumber has been updated successfully!!");
		}	
	}