package com.training.pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.GenericMethods;
import com.trianing.waits.WaitTypes;

public class AddNewProperty {
	
	private WebDriver driver; 			
	public AddNewProperty(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Delete same name property to avoid multiple test result
	@FindBy(xpath="//tbody[@id='the-list']/tr/td//a[contains(text(),'Test Property')]/ancestor::tr/th/input")
	private WebElement checkOldProp;
	@FindBy(id="bulk-action-selector-top")
	private WebElement bulkAcnBtn;
	@FindBy(id="doaction")
	private WebElement applyBtn;
	public void checkPropDelete() {
		try {
		boolean isPresent = checkOldProp.isDisplayed();
		if(isPresent==true) {
			this.checkOldProp.click();
			GenericMethods.selectBVisibleTxt(driver, bulkAcnBtn, "Move to Trash");
			this.applyBtn.click();}
		}catch (NoSuchElementException e) {
			System.out.println("No Same name property to delete");
		}
	}
	
	//Click on Properties link
	@FindBy(linkText="Properties")
	private WebElement propertyLnk;
	public void clickPropertyLnk() {
		this.propertyLnk.click();
	}
	
	//Click on 'Add New' link
	@FindBy(linkText="Add New")
	private WebElement addNewLnk;
	public void clickAddNewLnk() {
		this.addNewLnk.click();
	}
	
	//Verify Property page title
	@FindBy(xpath = "//h1[text()='Add Property']")
	private WebElement pageTitle;
	public boolean confirmPageTtle() {
		boolean isDisplayed = this.pageTitle.isDisplayed();
		return isDisplayed;
	}
	
	//Enter property title
	@FindBy(id="title")
	private WebElement titleBox;
	public void sendTitleTxt(String propertyTitle) {
		this.titleBox.clear();
		this.titleBox.sendKeys(propertyTitle);
	}
	
	//Enter property description
	@FindBy(id="content")
	private WebElement textBox;
	public void sendDetailTxt(String propertyDetail) {
		this.textBox.clear();
		this.textBox.sendKeys(propertyDetail);
	}
	
	//Click on publish button
	@FindBy(id="publish")
	private WebElement publishBtn;
	public void clickPublishBtn() {
		WaitTypes.waitForElement(driver, publishBtn, 40);
		this.publishBtn.click();
	}
	
	//Verify success message
	@FindBy(id="message")
	private WebElement successMsg;
	public String verifySuccessMsg() {
		WaitTypes.waitForElement(driver, successMsg, 40);
		String successMessage = successMsg.getText();
		return successMessage;
	}
	
	//Click on 'View post' link
	@FindBy(linkText="View post")
	private WebElement viewPostLink;
	public void clickViewPost() {
		this.viewPostLink.click();
	}
	
	//Verify posted title
	@FindBy(className="property-title")
	private WebElement postTitle;
	public String verifyPostTitle() {
		String titleCheck = postTitle.getText();
		return titleCheck;
	}
		
	//Verify posted description
	@FindBy(xpath="//div[@class='property-description print-only']//p")
	private WebElement postDescription;
	public String verifypostDescription() {
		String DescriptionCheck = postDescription.getText();
		return DescriptionCheck;
	}
}