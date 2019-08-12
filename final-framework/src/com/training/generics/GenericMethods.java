package com.training.generics;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Naveen
 * @see this class will help when you want to do custom business logic, since  in POM we dont do 
 * 			dynamic elements available, when you want to iterate the table/accordion etc 
 * @since 17-Dec-2018 
 */
public class GenericMethods {
	private static WebDriver driver ; 
	
	public GenericMethods(WebDriver driver){
		GenericMethods.driver = driver;
	}
	
	
	/**
	 * 
	 * @param locator 
	 * @param type
	 * @see type is id, name, xpath, text, partialtext
	 * @see locator will be the element to be found on DOM 
	 * @return  WebElement
	 * this method shall give provided it has single enty in the DOM
	 */
	public WebElement getElement(String locator, String type){
		WebElement element  = null;
		type = type.toLowerCase();
		
		if(type.equals("id")){
			element  =  driver.findElement(By.id(locator));
		} else if(type.equals("css")){
			element = driver.findElement(By.cssSelector(locator));
		}else if (type.equals("name")){
			element  = driver.findElement(By.name(locator));
		}else if(type.equals("xpath")){
			element = driver.findElement(By.xpath(locator));
		}
		if(checkSingleEntry(locator, type)){
			System.out.println("Element Found and Returned");
			return element;
		}	
		System.out.println("Sorry Element not found, so not returned...");
		return null;


	}
	
	
	// shall give if it has multiple entries as a list in DOM 
	
	public List<WebElement> getElementsAsList(String locator, String type){
		type = type.toLowerCase();
		if(type.equals("id")){
			return driver.findElements(By.id(locator));
		}else if(type.equals("name")){
			return driver.findElements(By.name(locator));
		}else if(type.equals("xpath")){
			return driver.findElements(By.xpath(locator));
		}else if(type.equals("class")){
			return driver.findElements(By.className(locator));
		}// other TODO 
		return null;
	}
	
	// return true if element exists 
	// this method works for us when we have more than 1 element 
	// to be found for 
	public boolean isElementFound(String locator, String type){
		return getElementsAsList(locator, type).size()>0;
	}
	
	// this method gives true only where there is an single entry 
	// in the DOM 
	public boolean checkSingleEntry(String locator, String type){
		return getElementsAsList(locator, type).size() ==1;
	}
	
	public static void scrollToView(WebDriver driver, WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public static void jsClick(WebDriver driver, WebElement element) {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	}
	
	
	public static void jsScrollUp(WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor)driver;
	// if the element is on bottom.
	js.executeScript("scroll(0, 500)");
	}
	
	public static void jsScrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 //if the element is on top.
		js.executeScript("scroll(250, 0)");
	}
	
	public static WebElement mouseOver(WebDriver driver, WebElement element){
		try{
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
			return element;
		}catch(Exception e ){
			System.out.println("Unable to move to element " + e);
		}
		return null;
	}
	
	public static WebElement doubleClick(WebDriver driver, WebElement element){
		try{
			Actions act = new Actions(driver);
			act.doubleClick(element).build().perform();
			return element;
		}catch(Exception e ){
			System.out.println("Unable to double click to element " + e);
		}
		return null;
	}
	
	public static WebElement actClick(WebDriver driver, WebElement element){
		try{
			Actions act = new Actions(driver);
			act.click().build().perform();
			return element;
		}catch(Exception e ){
			System.out.println("Unable to double click to element " + e);
		}
		return null;
	}
	public static WebElement mouseClick(WebDriver driver, WebElement element){
		try{
			Actions act = new Actions(driver);
			act.click(element).build().perform();
			return element;
		}catch(Exception e ){
			System.out.println("Unable to click the element " + e);
		}
		return null;
	}
	
	public static WebElement scrollDown(WebDriver driver, WebElement element){
		try{
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
			element.click();
			act.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			return element;
			
		}catch(Exception e ){
			System.out.println("Unable to click the element " + e);
		}
		return null;
	}
	
	public static void checkAlertAccept(WebDriver driver) {
		try {
		Alert alert = driver.switchTo().alert();
		alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void selectBIndex(WebDriver driver, WebElement element, int index) {
		try {
		Select select = new Select(element);
		select.selectByIndex(index);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectBVisibleTxt(WebDriver driver, WebElement element, String text) {
		try {
		Select select = new Select(element);
		select.selectByVisibleText(text);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}