package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class basicDetails {
	
	public static WebDriver driver;
	static Properties prop=new Properties();
	static PrintStream o;
		
		public void variable() throws IOException {
			FileInputStream fis=new FileInputStream("C:\\Users\\sribur19\\eclipse-workspace-Test0307\\Test0307\\src\\userDetails.properties");
			prop.load(fis);
			o = new PrintStream(new File(testInputGPMS.gpmsTestOutputsLocation+"00 GPMS Test Output.txt"));
			System.setOut(o);
		}
		
		
		@Test (groups = { "regression" })
		//Open login page to given Client ID
		public void homePage() throws IOException {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\reportsDownloaded\\");
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			
			
			driver=new ChromeDriver(options);
			driver.manage().window().maximize();
			//variable(); //this to print console data into external txt file as defined in above variable() method
			
			driver.get(testInputGPMS.urlTST3redirector);
			if(driver.findElements(By.xpath(loginPageObjects.clientID)).size()==0) {
				System.out.println("Failed: Login Page Not working");
				commonMethods.takeScreenShot(driver, "Login Page not found");
				Assert.fail("Failed: Login Page Not working");
			}
			
			driver.findElement(By.xpath(loginPageObjects.clientID)).clear();
			driver.findElement(By.xpath(loginPageObjects.clientID)).sendKeys(testInputGPMS.clientID);
			driver.findElement(By.xpath(loginPageObjects.submit)).click();
			
			if(driver.findElements(By.xpath(loginPageObjects.login)).size()==0) {
				System.out.println("Failed: Login Page Not working");
				commonMethods.takeScreenShot(driver, "Login Page not found");
				Assert.fail("Failed: Login Page Not working");
			}
			
			
			String version=driver.findElement(By.xpath(loginPageObjects.version)).getText();	
			
			if(version.contains(testInputGPMS.versionNo) & version.contains(testInputGPMS.versionTC)) {
				System.out.println("Passed: GPMS current version: "+version);
				commonMethods.takeScreenShotOfElement(driver, "GPMS Version Verified", driver.findElement(By.xpath(loginPageObjects.versionElement)));
			}else {
				System.out.println("Failed: Expected version '"+testInputGPMS.versionTC+" / "+testInputGPMS.versionNo+"', present '"+version+"'");
				commonMethods.takeScreenShotOfElement(driver, "GPMS Version Incorrect", driver.findElement(By.xpath(loginPageObjects.versionElement)));
				Assert.fail("Failed: Expected version '"+testInputGPMS.versionTC+" / "+testInputGPMS.versionNo+"', present '"+version+"'");
			}
			
		}
		
		@Test (groups = { "regression" })
		//Login to TST3 for given Client ID and user credentials
		public void loginTST3() throws IOException {
			driver.findElement(By.xpath(loginPageObjects.userName)).sendKeys(testInputGPMS.userName);
			driver.findElement(By.xpath(loginPageObjects.password)).sendKeys(testInputGPMS.password);
			driver.findElement(By.xpath(loginPageObjects.login)).click();
			
			if(driver.findElements(By.xpath(menuPageObjects.search)).size()==1) {
				System.out.println("Passed: GPMS Login successful");
				commonMethods.takeScreenShot(driver, "GPMS Login successful");
			}else {
				System.out.println("Failed: GPMS Login failed");
				commonMethods.takeScreenShot(driver, "GPMS LogIn Failed");
				Assert.fail("Failed: GPMS LogIn Failed");
			}
		}
	

		@Test (groups = { "regression" })
		//Login to TST3 for given Client ID and user credentials
		public void VerifyMenuBarSearchOption() throws IOException, AWTException, InterruptedException {
			
			String url = "";
			
			menuBarLinks.goToForEmployee(driver);
			List<WebElement> links = driver.findElements(By.tagName("a"));
			Iterator<WebElement> it = links.iterator();
			while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            System.out.println(url);
			}
		}

		@Test (groups = { "regression" })
		//Login to TST3 for given Client ID and user credentials
		public void VerifyMenuBarAdminOption() throws IOException {
			
		}

		@Test (groups = { "regression" })
		//Login to TST3 for given Client ID and user credentials
		public void VerifyMenuBarReportsOption() throws IOException {
			
		}
		
		@Test (groups = { "regression" })
		//Login to TST3 for given Client ID and user credentials
		public void VerifyMenuBarSupportOption() throws IOException {
			
		}

}


