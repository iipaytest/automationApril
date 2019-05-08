package gpmsUI.gpmsAutomation;

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
		
		
		@Test 
		//Open login page to given Client ID
		public void homePage() throws IOException {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
			driver.get(testInputGPMS.urlTST3redirector);
			if(driver.findElements(By.cssSelector("input#ClientId")).size()==0) {
				System.out.println("Failed: Login Page Not working");
				commonMethods.takeScreenShot(driver, "GPMS Version Incorrect");
				Assert.fail("Failed: Login Page Not working");
			}
			driver.findElement(By.cssSelector("input#ClientId")).sendKeys(testInputGPMS.clientID);
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			
			String version=driver.findElement(By.xpath("//img[@class='loginProviderImage']/parent::p/following-sibling::p[1]")).getText();	
			
			if(version.contains(testInputGPMS.versionNo) & version.contains(testInputGPMS.versionTC)) {
				System.out.println("Passed: GPMS current version: "+version);
				commonMethods.takeScreenShot(driver, "GPMS Version Verified");
			}else {
				System.out.println("Failed: Test was run on "+version+" |Expected TC and version are "+testInputGPMS.versionTC+" and "+testInputGPMS.versionNo);
				commonMethods.takeScreenShot(driver, "GPMS Version Incorrect");
				Assert.fail("Failed: Test was run on "+version+" |Expected TC and version are "+testInputGPMS.versionTC+" and "+testInputGPMS.versionNo);
			}
			
		}
		
		@Test
		//Login to TST3 for given Client ID and user credentials
		public void loginTST3() throws IOException {
			driver.findElement(By.cssSelector("input#Username")).sendKeys(testInputGPMS.userName);
			driver.findElement(By.cssSelector("input#Password")).sendKeys(testInputGPMS.password);
			driver.findElement(By.cssSelector("button[type='submit']")).click();
			
			if(driver.findElements(By.xpath(menuPageObjects.search)).size()==1) {
				System.out.println("Passed: GPMS Login successful");
				commonMethods.takeScreenShot(driver, "GPMS Login successful");
			}else {
				System.out.println("Failed: GPMS Login failed");
				commonMethods.takeScreenShot(driver, "GPMS LogIn Failed");
				Assert.fail("Failed: GPMS LogIn Failed");
			}
		}
	}


