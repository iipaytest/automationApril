package gpmsAutomation.pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import gpmsAutomation.reusableMethods.commonMethods;

public class userDefinedOrgUnitAdminPageObjects {
	
	public static final String userDefinedOrgsTable="//*[@id='customTypes']";
	public static final String save="//*[@id='btnSave']";
	public static final String newName="//*[@id='clientLookups_newRow0_txtName']";
	public static final String newReference="//*[@id='clientLookups_newRow0_txtReference']";
	public static final String newRow="//a[contains(text(), 'New Row')]";
	public static final String backButton="//div[@id='btnBack']";
	
	public static final String sucessful="//span[@id='lblSuccess']";
	public static final String error="//span[@id='lblError']";
	public static final String errorSummary="//div[@id='errorSummary']";
	public static final String warning="//span[@id='lblWarning']";
	
	public static void createNewUserOrgUnit(WebDriver driver, String newOrgUnitName) throws InterruptedException, IOException {
		int i=driver.findElements(By.xpath(userDefinedOrgsTable+"/tbody/tr")).size();
		Actions action = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(userDefinedOrgsTable+"/tbody/tr["+i+"]"));
		action.moveToElement(mainMenu).click().sendKeys(newOrgUnitName).perform();
		driver.findElement(By.xpath(save)).click();
		
		if(driver.findElement(By.xpath(sucessful)).getAttribute("innerText").contains("successfully")) {
			commonMethods.takeScreenShot(driver, "Passed Saving New Org unit");
			System.out.println("Passed: New Org unit saved successfully");
		}else {
			if(driver.findElement(By.xpath(error)).getAttribute("innerText").contains("ERROR")) {
				System.out.println(driver.findElement(By.xpath(error)).getAttribute("innerText"));}
			if(driver.findElement(By.xpath(warning)).getAttribute("innerText").contains("Warning")) {
				System.out.println(driver.findElement(By.xpath(warning)).getAttribute("innerText"));}
			commonMethods.takeScreenShot(driver, "Failed Saving New Org unit");
			Assert.fail("Failed: Saving New Org unit");
		}
		
	}
	
	public static void newUserOrgUnitConfiguration(WebDriver driver, String orgUnitName , String[] names, String[] references) throws InterruptedException, IOException, AWTException {
		
		menuBarLinks.goToUserDefinedOrgUnits(driver);
		int i=driver.findElements(By.xpath("//*[@id='customTypes']/tbody/tr")).size();
        for(int j=1; j<=i; j++) {
        	String s= driver.findElement(By.xpath("//*[@id='customTypes']/tbody/tr["+j+"]/td[1]/input")).getAttribute("defaultValue");
        	if(orgUnitName.equals(s)==true) {
        		driver.findElement(By.xpath("//*[@id='customTypes']/tbody/tr["+j+"]/td[2]/input")).click();
        		for(int x=0; x<names.length; x++) {
        			driver.findElement(By.xpath(newName)).sendKeys(names[x]);
        			driver.findElement(By.xpath(newReference)).sendKeys(references[x]);
        			driver.findElement(By.xpath(save)).click();
        			Thread.sleep(1000);
        			}
        		if(driver.findElement(By.xpath(sucessful)).getAttribute("innerText").contains("successfully")) {
    				commonMethods.takeScreenShot(driver, "Passed Saving New Org unit details");
    				System.out.println("Passed: New Org unit details saved successfully");
    			}else {
    				if(driver.findElement(By.xpath(error)).getAttribute("innerText").contains("ERROR")) {
    					System.out.println(driver.findElement(By.xpath(error)).getAttribute("innerText"));}
    				if(driver.findElement(By.xpath(warning)).getAttribute("innerText").contains("Warning")) {
    					System.out.println(driver.findElement(By.xpath(warning)).getAttribute("innerText"));}
    				commonMethods.takeScreenShot(driver, "Failed Saving New Org unit details");
    				Assert.fail("Failed: Saving New Org unit details");	
    			}
        		driver.findElement(By.xpath(backButton)).click();	
        	}
        }
		
		
	}
	
}
