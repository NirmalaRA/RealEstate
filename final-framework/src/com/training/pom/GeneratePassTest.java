package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GeneratePassTest {
	private WebDriver driver; 			
	public GeneratePassTest(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Clicking on generate password link
	@FindBy(xpath="//td/button[@type='button']")
	private WebElement generatePassLnk; 
	public void generatePassLnk() {
		this.generatePassLnk.click();	
	}
	
	//Clearing auto password and entering new password
	@FindBy(id="pass1")
	private WebElement newPasswrd;	
	public void generateNewPass(String newPasswrd) {
		this.newPasswrd.clear(); 
		this.newPasswrd.sendKeys(newPasswrd); 
	}
	
	//Checking password strength, Entering same password which is in medium strength 
	@FindBy(xpath="//div[@class='good']")
	private WebElement passStrength;	
	public void passStrength() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(passStrength));
		this.passStrength.isDisplayed();
	}
	
	//Checking update success message
	@FindBy(xpath="//div[@id='message']//strong")
	private WebElement successMsg;
	public void verifySuccessMsg() {
	Assert.assertEquals("Profile updated.", successMsg.getText());
	System.out.println("New password generated successfully!!");
	}	
}