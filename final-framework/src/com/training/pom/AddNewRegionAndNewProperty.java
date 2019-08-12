package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.GenericMethods;

public class AddNewRegionAndNewProperty {
	
	private WebDriver driver; 

		public AddNewRegionAndNewProperty(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	//Click on Regions link
	@FindBy(linkText="Regions")
	private WebElement regionsLnk;
	public void clickRegionsLnk() {
		this.regionsLnk.click();
	}
	
	//Verify Regions page Title
	@FindBy(xpath = "//h1[text()='Regions']")
	private WebElement pageTitle;
	public boolean confirmPageTtle() {
		return pageTitle.isDisplayed();
	}
	
	//Check New and existing region forms
	@FindBy(id="col-left")
	private WebElement addRegionForm;
	public boolean checkAddRegionForm() {
		return this.addRegionForm.isDisplayed();
	}
	
	@FindBy(id="col-right")
	private WebElement existingRegionForm;
	public boolean checkExistingRegionForm() {
		return this.existingRegionForm.isDisplayed();
	}
	
	//Find out if the Region is already present and delete if it exists
	@FindBy(xpath="//a[contains(text(),'Electronic city Test')]")
	private WebElement existingRegionNme;
	public boolean verifyExistingRegionNme() {
		boolean isNamePresent = existingRegionNme.isDisplayed();
		return isNamePresent;
	}	
	@FindBy(xpath="//a[contains(text(),'Electronic city Test')]/../../../child::th/child::input")
	private WebElement selectExisting;
	@FindBy(id="bulk-action-selector-bottom")
	private WebElement bulkActionDd;
	@FindBy(id="doaction2")
	private WebElement applyBtn;
	public void deleteExistingRegionNme(boolean True) {	
		selectExisting.click();
		GenericMethods.selectBIndex(driver, bulkActionDd, 1);
		applyBtn.click();
		}
	
	//Entering new Region name
	@FindBy(id="tag-name")
	private WebElement nameBox;
	public void sendNametxt(String regionName) {
		this.nameBox.clear();
		this.nameBox.sendKeys(regionName);
	}
	
	//Entering new Region slug
	@FindBy(id="tag-slug")
	private WebElement slugBox;
	public void sendSlugtxt(String slugtxt) {
		this.slugBox.clear();
		this.slugBox.sendKeys(slugtxt);
	}
	
	//Entering new Region parent
	@FindBy(id="parent")
	private WebElement parentRegion;
	public void selectParentRegion() {
		this.parentRegion.click();
		GenericMethods.selectBIndex(driver, parentRegion, 0);
	}
	
	//Entering new Region description
	@FindBy(id="tag-description")
	private WebElement descBox;
	public void sendDesctxt(String descTxt) {
		this.descBox.clear();
		this.descBox.sendKeys(descTxt);
	}
		
	//Submit new Region
	@FindBy(id="submit")
	private WebElement addNewFtBtn;
	public void clickAddNewRegionBtn() {
		this.addNewFtBtn.click();
		driver.navigate().refresh();
	}
	
	//Verify added Region name in table
	@FindBy(xpath="//a[contains(text(),'Electronic city Test')]")
	private WebElement addedRegioneNme;
	public String verifyaddedRegionName() {
		String regionName = addedRegioneNme.getText();
		return regionName;		    
	}
	
	//Verify added Region description in table
	@FindBy(xpath="//a[contains(text(),'Electronic city Test')]/ancestor::td/following-sibling::td[1]")
	private WebElement addedRegionDesc;
	public String verifyaddedRegionDesc() {
		String descTxt = addedRegionDesc.getText();
		return descTxt;		    
	}
	
	//Verify added Region Slug in table
	@FindBy(xpath="//a[contains(text(),'Electronic city Test')]/ancestor::td/following-sibling::td[2]")
	private WebElement addedRegionSlug;
	public String verifyaddedRegionSlug() {
		String slugtxt = addedRegionSlug.getText();
		return slugtxt;		    
	}
	
	//Verify added regions checkbox is selected
	@FindBy(id="regiondiv")
	private WebElement RegionTable;
	@FindBy(xpath="//div[@id='regiondiv']//label[contains(text(),'Electronic city Test')]/input")
	private WebElement selectAddedRegion;
	public boolean selectAddedRegion() {
		GenericMethods.mouseOver(driver, RegionTable);
		//WaitTypes.elementToBeClickable(driver, RegionTable, 30);
		selectAddedRegion.click();
		boolean isRegionSelected = selectAddedRegion.isSelected();
		return isRegionSelected;
	}
	
}