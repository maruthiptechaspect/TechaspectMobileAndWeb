package com.techaspect.base;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.techaspect.util.Xls_Reader;

public class Page {

	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties OR =null;
	public static Xls_Reader xls1= new Xls_Reader(System.getProperty("user.dir")+"//src//main//java//com//techaspect//xls//Test Cases.xlsx");
	
	
	public Page() {
		
		//I. READING PROPERTIES FILE
		if (driver == null) {
			CONFIG = new Properties();
			OR = new Properties();
			String path = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\techaspect\\config\\config.properties";
			try {
				// config
				FileInputStream fs = new FileInputStream(path);
				CONFIG.load(fs);

				// OR
				fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\techaspect\\config\\OR.properties");
				OR.load(fs);
		
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
			
			//II. SELECTING THE BROWSER FOR WEB / MOBILE
			if (CONFIG.getProperty("browser").equals("ff")) {
				this.driver = new FirefoxDriver();
			} else if (CONFIG.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe");
				this.driver = new ChromeDriver();
			} else if (CONFIG.getProperty("browser").equals("ie")) {
				this.driver = new InternetExplorerDriver();
			} else if (CONFIG.getProperty("browser").equals("android-chrome")) {
				//RemoteWebDriver driver = null;
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				capabilities.setCapability("deviceName", "Samsung S5");
				capabilities.setCapability("platformVersion", "4.4.2");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("app", "chrome");
				try {
					this.driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		
	}

	//III. METHODS
	//***********1. RETURNS CURRENT URL OF WEBDRIVER***********
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	//***********2. RETURNS CURRENT PAGE SOURCE***********
	public String getPageSource() {
		return driver.getPageSource();
	}
	
	//***********3. RETURNS TITLE OF CURRENT PAGE***********
	public String getTitle() {
		return driver.getTitle();
	}
	
	//***********4. CLICKS ON ELEMENT USING IDENTIFIER PROVIDED***********
	public boolean click(By by) {
		WebElement webElement = driver.findElement(by);
		try {
			webElement.click();
		} catch (WebDriverException e) {
			return false;
		}
		return true;
	}

	//***********5. CLICKS ON ELEMENT USING CSS PROPERTY***********
	public boolean click(String css) {
		return click(By.cssSelector(css));
	}

	//***********6. RETURNS AN ATTRIBUTE VALUE OF AN ELEMENT USING IDENTIFIER PROVIDED***********
	public String getAttribute(By by, String attribute) {
		return driver.findElement(by).getAttribute(attribute);
	}

	//***********7. RETURNS AN ATTRIBUTE VALUE OF AN ELEMENT USING CSS***********
	public String getAttribute(String cssSelector, String attribute) {
		return getAttribute(By.cssSelector(cssSelector), attribute);
	}

	//***********8. RETURNS TEXT ON AN ELEMENT USING IDENTIFIER PROVIDED***********
	public String getText(By by) {
		return driver.findElement(by).getText().trim();
	}

	//***********9. RETURNS TEXT ON AN ELEMENT USING CSS***********
	public String getText(String cssSelector) {
		return getText(By.cssSelector(cssSelector));
	}
	
	//***********10. CHECKS WHETHER AN ELEMENT IS PRESENT USING IDENTIFIER PROVIDED***********
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
		}
		return false;
	}

	//***********11. CHECKS WHETHER AN ELEMENT IS PRESENT USING CSS***********
	public boolean isElementPresent(String cssSelectory) {
		return isElementPresent(By.cssSelector(cssSelectory));
	}

	//***********12. CHECKS WHETHER AN ELEMENT IS VISIBLE USING IDENTIFIER PROVIDED***********
	public boolean isElementVisible(By by) {
		try {
			WebElement e = driver.findElement(by);
			return e.isDisplayed();
		} catch (NoSuchElementException e) {
		}
		return false;
	}

	//***********13. CHECKS WHETHER AN ELEMENT IS VISIBLE USING CSS***********
	public boolean isElementVisible(String cssSelectory) {
		return isElementVisible(By.cssSelector(cssSelectory));
	}

	//***********14. WAITS FOR SPECIFIC TIME PROVIDED***********
	public void wait(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//***********15. CLOSES THE BROWSER***********
	public void closeBrowser() {
		if (driver!= null) {
			driver.quit();
		}
	}

	//***********16. DELETES ALL THE COOKIES OF THE BROWSER***********
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}
	
	//***********17. SETS TEXT IN A TEXTBOX USING THE IDENTIFIER PROVIDED***********
	public boolean setText(By by, String text) {
		if (isElementPresent(by)) {
			WebElement webElement = driver.findElement(by);
			webElement.clear();
			webElement.click();
			webElement.sendKeys(new CharSequence[] { text });
			return true;
		}
		return false;
	}

	//***********18. SETS TEXT IN A TEXTBOX USING CSS***********
	public boolean setText(String cssSelector, String text) {
		return setText(By.cssSelector(cssSelector), text);
	}
	
	//***********19. MAXIMIZES THE WINDOW***********
	public void maxWindow(){
		driver.manage().window().maximize();
	}
	
	//***********20. SETS TEXT IN A TEXTBOX USING xpath***********
		public void enterText(String xpathExpression, String text) {
			driver.findElement(By.xpath(xpathExpression)).sendKeys(text);
	}
		
	//***********21. SETS TEXT IN A TEXTBOX USING xpath***********
	public void enterTextByClass(String className, String text) {
			driver.findElement(By.className(className)).sendKeys(text);
	}
	
	//***********22. RETURNS TOMORROW DATE***********
	public String getTomorrowDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		Date date = new Date();
		Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
		return dateFormat.format(tomorrow);
	}
	
	//***********23. RETURNS CURRENT DATE***********
		public String getCurrentDate(){
			DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			Date date = new Date();
			return dateFormat.format(date);
		}
		
	//***********24. TAKES SCREENSHOT******************
		public static void takeScreenshot(String fileName) {

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	
}
