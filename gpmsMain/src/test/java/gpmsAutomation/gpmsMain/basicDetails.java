package gpmsAutomation.gpmsMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import gpmsAutomation.pageObjectsGPMS.menuPageObjects;
import gpmsAutomation.reusableMethods.commonMethods;
import gpmsAutomation.testInputs.testInputGPMS;

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
		
		
		@Test //Open login page to given Client ID
		public void homePage() throws IOException {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
			driver.get(testInputGPMS.urlTST3redirector);
			driver.findElement(By.cssSelector("input#ClientId")).sendKeys(testInputGPMS.clientID);
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			
			String version=driver.findElement(By.xpath("//img[@class='loginProviderImage']/parent::p/following-sibling::p[1]")).getText();	
			
			try {
				Assert.assertTrue(version.contains(testInputGPMS.versionNo) & version.contains(testInputGPMS.versionTC)); 
				System.out.println("Passed: GPMS current version: "+version);
				commonMethods.takeScreenShot(driver, "GPMS Version Verified");		
			}catch (Error e){
				System.out.println("Failed: Test was run on "+version+" |Expected version and TC are "+testInputGPMS.versionNo+" and "+testInputGPMS.versionTC);
				commonMethods.takeScreenShot(driver, "GPMS Version Incorrect");
				Assert.fail("Failed: Test was run on "+version+" |Expected version and TC are "+testInputGPMS.versionNo+" and "+testInputGPMS.versionTC);
			}
			
		
		}
		
		@Test //Login to TST3 for given Client ID and user credentials
		public void loginTST3() throws IOException {
			driver.findElement(By.cssSelector("input#Username")).sendKeys(testInputGPMS.userName);
			driver.findElement(By.cssSelector("input#Password")).sendKeys(testInputGPMS.password);
			driver.findElement(By.cssSelector("button[type='submit']")).click();
			
			try {
				Assert.assertEquals(1, driver.findElements(By.xpath(menuPageObjects.search)).size());
				System.out.println("Passed: GPMS Login successful");
				commonMethods.takeScreenShot(driver, "GPMS Login successful");
			}catch (Error e){
				System.out.println("Failed: GPMS Login failed");
				commonMethods.takeScreenShot(driver, "GPMS LogIn Failed");
				Assert.fail("Failed: GPMS LogIn Failed");
			}

		}
			
	}


