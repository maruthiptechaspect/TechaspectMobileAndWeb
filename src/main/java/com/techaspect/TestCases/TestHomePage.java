package com.techaspect.TestCases;


import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techaspect.base.Page;
import com.techaspect.pages.ContactPage;
import com.techaspect.pages.HomePage;
import com.techaspect.util.TestUtil;

public class TestHomePage extends Page{

	@Test
	public void isLogoPresent(){
		if(!TestUtil.isExecutable("isLogoPresent", Page.xls1))
			throw new SkipException("RunMode is set to No");
		HomePage hp = new HomePage().open();
		if(!CONFIG.getProperty("browser").equalsIgnoreCase("android-chrome"))
			hp.maxWindow();
		Assert.assertTrue(hp.checkLogo(), "Home Page Logo is not present");
		
	}
	
	@Test
	public void checkPageTitle(){
		if(!TestUtil.isExecutable("checkPageTitle", Page.xls1))
			throw new SkipException("RunMode is set to No");
		HomePage hp = new HomePage().open();
		if(!CONFIG.getProperty("browser").equalsIgnoreCase("android-chrome"))
			hp.maxWindow();	
		Assert.assertEquals(hp.getTitle(), CONFIG.getProperty("HomePageTitle"));
		}
	
	@Test(dataProvider ="getData")
	public void checkContactLink(Hashtable<String, String> data){
		if(!TestUtil.isExecutable("checkContactLink", Page.xls1))
			throw new SkipException("RunMode is set to No for test case");
		if(!data.get("Runmode").equals("Y"))
			throw new SkipException("RunMode is set to No for test data");
		
		HomePage hp = new HomePage().open();
		if(!CONFIG.getProperty("browser").equalsIgnoreCase("android-chrome"))
			hp.maxWindow();	
		ContactPage cp= hp.clickHeaderLink(CONFIG.getProperty("HeaderMenuLink"));
		cp.getInTouch(data.get("Name"), data.get("Company"), data.get("Job"), data.get("Business Email"), data.get("Phone"), data.get("Country"), data.get("Description"));
		Assert.assertEquals(cp.thankYouMsg(), CONFIG.getProperty("ThankyouMessage"));
		
	}
	
	
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("checkContactLink", Page.xls1);
	}
	
	@AfterClass
	public void closing(){
		closeBrowser();
	}
	
	
}
