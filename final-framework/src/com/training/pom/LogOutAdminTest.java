package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.GenericMethods;
import com.trianing.waits.WaitTypes;

public class LogOutAdminTest {
		private WebDriver driver; 			
		public LogOutAdminTest(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//Clicking on log out link
		@FindBy(id="wp-admin-bar-logout")
		private WebElement logOutLnk; 	
		public void logOutLnk() {
			WaitTypes.waitForElement(driver, logOutLnk, 40);
			GenericMethods.mouseOver(driver, logOutLnk);
			GenericMethods.mouseClick(driver, logOutLnk);
			//this.logOutLnk.click();
		}	
	}