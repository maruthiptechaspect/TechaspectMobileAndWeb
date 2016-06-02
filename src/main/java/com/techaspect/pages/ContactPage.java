package com.techaspect.pages;



import org.openqa.selenium.By;

import com.techaspect.base.Page;

public class ContactPage extends Page{
	
	public void  getInTouch(String name, String company, String job, String businessEmail, String phone, String country, String description){
		//FILL FORM
		enterText(OR.getProperty("name"), name);
		enterText(OR.getProperty("company"), company);
		enterText(OR.getProperty("job"), job);
		enterText(OR.getProperty("businessEmail"), businessEmail);
		enterText(OR.getProperty("phone"), phone);
		enterTextByClass(OR.getProperty("country"), country);
		enterText(OR.getProperty("description"), description);
		//enterText(OR.getProperty("completionDate"), getTomorrowDate());
		click(By.xpath(OR.getProperty("getInTouchSubmit")));
	}
	
	public String thankYouMsg(){
		wait(5000);
		return getText(By.className("scfTitleBorder"));
		
	}
	
	public void getLocations(){
		
	}
	

}
