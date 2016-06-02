package com.techaspect.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.techaspect.base.Page;

public class HomePage extends Page{
	
			
	public HomePage open() {
		if (null == driver) {
			return null;
		}
		String ss_url = CONFIG.getProperty("testSiteUrl");
		System.out.println("Open Home Page - " + ss_url);
		try {
			driver.get(ss_url);
		} catch (UnreachableBrowserException ex) {
			System.out.println("IGNORE UnreachableBrowserException");
		}
		return new HomePage();
	}

	public boolean checkLogo(){
		return isElementPresent(By.xpath(OR.getProperty("logo")));
		
	}
	
	public ContactPage clickHeaderLink(String text){
		List<WebElement> dropdownItems; 
		if(CONFIG.getProperty("browser").equalsIgnoreCase("android-chrome")){
			click(By.id(OR.getProperty("mobileMenuButton")));
		dropdownItems = driver.findElements(By.cssSelector(OR.getProperty("mobileDropdownValues")));
		}
			else{
			dropdownItems = driver.findElements(By.cssSelector(OR.getProperty("webDropdownValues")));
			}
			System.out.println(dropdownItems.size());
		    	   for(int i=0; i<dropdownItems.size(); i++){
			    	   System.out.println(dropdownItems.get(i).getText());
			    	   if(dropdownItems.get(i).getText().equals(text)){
			    		   dropdownItems.get(i).click();
			    	   }
		}
		return new ContactPage();
	}
	
	
	/*
	public WebContentManagementPage clickWebContentManagement(){
		driver.findElement(By.linkText("Web Content Management")).click();
		return new WebContentManagementPage();
	}
	
	public CommercePage clickCommerce(){
		driver.findElement(By.linkText("Commerce")).click();
			return new CommercePage();
	}
	
	*/

}
