package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutAdminTest {
		private WebDriver driver; 			
		public LogOutAdminTest(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//Clicking on log out link
		@FindBy(linkText="Log Out")
		private WebElement logOutLnk; 	
		public void logOutLnk() {
			this.logOutLnk.click();
		}	
	}