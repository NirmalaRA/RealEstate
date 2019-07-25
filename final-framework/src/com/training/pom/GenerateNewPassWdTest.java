package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenerateNewPassWdTest {
	private WebDriver driver; 			
	public GenerateNewPassWdTest(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Clicking on generate password link
	@FindBy(xpath="//td/button[@type='button']")
	private WebElement generatePassLnk; 
	public void generatePassLnk() {
		this.generatePassLnk.isDisplayed();
		this.generatePassLnk.click();	
	}
	
	//Clearing auto password and entering new password
	@FindBy(id="pass1")
	private WebElement generatePasswrd;	
	@FindBy(xpath="//button[@aria-label='Hide password']")
	private WebElement hideBtn;
	@FindBy(xpath="//button[@aria-label='Cancel password change']")
	private WebElement cancelBtn;
	@FindBy(id="pass-strength-result")
	private WebElement autoPassStrenght;
	public void generateNewPass(String newPasswrd) {
		generatePasswrd.isDisplayed();
		hideBtn.isDisplayed();
		cancelBtn.isDisplayed();
		this.generatePasswrd.clear(); 
		this.generatePasswrd.sendKeys(newPasswrd); 
	}
	
	//Checking password strength, Entering same password which is in medium strength 
	@FindBy(xpath="//div[@class='good']")
	private WebElement manualPassStrength;	
	public void passStrength() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(manualPassStrength));
		this.manualPassStrength.isDisplayed();
	}
}