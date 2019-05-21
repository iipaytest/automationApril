package gpmsUI.gpmsAutomation;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.loginPageObjects;
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class verifyMenuBarLinks {

	public static WebDriver driver;
	static Properties prop=new Properties();
	static PrintStream o;
	
	@Test
	public static void forgottenPassword() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\reportsDownloaded\\");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		//commonMethods.takeScreenShot(driver, "GPMS Version Verified");
		//String url=testInputGPMS.urlTST3redirector;
		
		
		driver.get(testInputGPMS.urlTST3redirector);
		if(driver.findElements(By.xpath(loginPageObjects.clientID)).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "Login Page not found");
		}
		
		driver.findElement(By.xpath(loginPageObjects.clientID)).clear();
		driver.findElement(By.xpath(loginPageObjects.clientID)).sendKeys(testInputGPMS.clientID);
		driver.findElement(By.xpath(loginPageObjects.submit)).click();
		
		if(driver.findElements(By.xpath(loginPageObjects.login)).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "Login Page not found");
			Assert.fail("Failed: Login Page Not working");
		}
		
		driver.findElement(By.xpath(loginPageObjects.forgottenPassword)).click();
		if(driver.findElements(By.xpath(loginPageObjects.requestResetCode)).size()==0) {
			System.out.println("Failed: Forgotten Password link is not working");
			commonMethods.takeScreenShot(driver, "Failed: Forgotten Password link is not working");
		}else {
			driver.findElement(By.xpath(loginPageObjects.emailAddress)).sendKeys("Test@iipay.com");
			driver.findElement(By.xpath(loginPageObjects.userID)).sendKeys("Testiipay");
			driver.findElement(By.xpath(loginPageObjects.requestResetCode)).click();
			if(driver.findElements(By.xpath(loginPageObjects.warning)).size()!=0) {
				String warning=driver.findElement(By.xpath(loginPageObjects.warning)).getText();
				System.out.println("Passed: Forgotten Password link is working "+warning);
				commonMethods.takeScreenShotOfElement(driver, "Failed Forgotten Password link is not working", driver.findElement(By.xpath(loginPageObjects.warningElement)));
			}else {
				System.out.println("Failed: Forgotten Password link is not working");
				commonMethods.takeScreenShotOfElement(driver, "Failed Forgotten Password link is not working", driver.findElement(By.xpath(loginPageObjects.warningElement)));
			}
		}
		
		driver.close();
	}
}
