package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.training.generics.GenericMethods;

public class AddNewFeatureAndNewProperty {
	
	private WebDriver driver; 

		public AddNewFeatureAndNewProperty(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Click on features link
	@FindBy(linkText="Features")
	private WebElement featuresLnk;
	public void clickFeaturesLnk() {
		this.featuresLnk.click();
	}
	
	//verify page title
	@FindBy(xpath = "//h1[text()='Features']")
	private WebElement pageTitle;
	public boolean confirmPageTtle() {
		boolean isDisplayed = pageTitle.isDisplayed();
		return isDisplayed;
	}
	
	//Check Existing and New form
	@FindBy(className="form-wrap")
	private WebElement addFeatureForm;
	public boolean checkAddFeatureForm() {
		boolean featureIsDisplayed = this.addFeatureForm.isDisplayed();
		return featureIsDisplayed;
	}
	
	@FindBy(id="posts-filter")
	private WebElement existingFeatureForm;
	public boolean checkExistingFeatureForm() {
		boolean formIsDisplayed = this.existingFeatureForm.isDisplayed();
		return formIsDisplayed;
	}
	
	//Find out if the feature is already present and delete if it exists
	@FindBy(xpath="//a[contains(text(),'New Launches Test')]")
	private WebElement existingFeatureNme;
	public boolean verifyExistingFeatureNme() {
		boolean isNamePresent = existingFeatureNme.isDisplayed();
		return isNamePresent;
	}
	@FindBy(xpath="//a[contains(text(),'New Launches Test')]/../../../child::th/child::input")
	private WebElement selectExisting;
	@FindBy(id="bulk-action-selector-bottom")
	private WebElement bulkActionDd;
	@FindBy(id="doaction2")
	private WebElement applyBtn;
	public void deleteExistingFeatureNme(boolean True) {	
			selectExisting.click();
			GenericMethods.selectBIndex(driver, bulkActionDd, 1);
			applyBtn.click();
		}
	
	//Entering new feature name
	@FindBy(id="tag-name")
	private WebElement nameBox;
	public void sendNameTxt(String featureName) {
		this.nameBox.clear();
		this.nameBox.sendKeys(featureName);
	}
	
	//Entering new feature description
	@FindBy(id="tag-description")
	private WebElement descBox;
	public void sendDescTxt(String descTxt) {
		this.descBox.clear();
		this.descBox.sendKeys(descTxt);
	}
	
	//Entering new feature slug
	@FindBy(id="tag-slug")
	private WebElement slugBox;
	public void sendSlugTxt(String slugtxt) {
		this.slugBox.clear();
		this.slugBox.sendKeys(slugtxt);
	}
	
	//Submit new feature
	@FindBy(id="submit")
	private WebElement addNewFtBtn;
	public void clickAddNewFeatureBtn() {
		this.addNewFtBtn.click();
		driver.navigate().refresh();
	}
	
	//Verify added feature name in table
	@FindBy(xpath="//a[contains(text(),'New Launches Test')]")
	private WebElement addedFeatureNme;
	public String verifyAddedFeatureName() {
		String featureName = addedFeatureNme.getText();
		return featureName;		    
	}
	
	//Verify added feature description in table
	@FindBy(xpath="//a[contains(text(),'New Launches Test')]/ancestor::td/following-sibling::td[1]")
	private WebElement addedFeatureDesc;
	public String verifyAddedFeatureDesc() {
		String descTxt = addedFeatureDesc.getText();
		return descTxt;		    
	}
	
	//Verify added feature slug in table
	@FindBy(xpath="//a[contains(text(),'New Launches Test')]/ancestor::td/following-sibling::td[2]")
	private WebElement addedFeatureSlug;
	public String verifyAddedFeatureSlug() {
		String slugtxt = addedFeatureSlug.getText();
		return slugtxt;		    
	}
	
	//Verify added feature checkbox is selected
	@FindBy(id="property_featurediv")
	private WebElement featureTable;
	@FindBy(xpath="//label[contains(text(),'New Launches Test')]/input")
	private WebElement selectAddedFture;
	public boolean selectAddedFeature() {
		GenericMethods.jsScrollUp(driver);
		selectAddedFture.click();
		boolean isFtSelected = selectAddedFture.isSelected();
		return isFtSelected;
	}
	
}