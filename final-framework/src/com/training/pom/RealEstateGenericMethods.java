package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RealEstateGenericMethods {
		private WebDriver driver; 			
		public RealEstateGenericMethods(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}

		//Clicking on Login/SignIn button . 
		@FindBy(xpath="//a[@class ='sign-in']")
		private WebElement loginBtn; 
		public void clickloginBtn() {
			this.loginBtn.click();;
		}
		
		//Checking login form
		@FindBy(xpath="//div[@class='tabs-container alt']//div[@id='tab1']")
		private WebElement loginForm;
		public void checkLoginForm() {
			this.loginForm.isDisplayed();
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
		
		//Check admin dashboard
		@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
		private WebElement dashBoard;
		public void checkDashBoard() {
			this.dashBoard.isDisplayed();
		}
		
		//Submitting login credentials
		@FindBy(xpath="//input[@name='login']")
		private WebElement signInBtn; 
		public void clickSignINBtn() {
			this.signInBtn.click();
		}
		
	}