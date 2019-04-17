package gpmsAutomation.reusableMethods;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import gpmsAutomation.pageObjectsGPMS.editPeriodsPageObjects;
import gpmsAutomation.pageObjectsGPMS.payrollPageObjects;
import gpmsAutomation.testInputs.testInputGPMS;

public class commonMethods {
	
	static File shot;
	
	public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
		
		shot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(shot,new File(testInputGPMS.gpmsTestOutputsLocation+" "+fileName+".jpg"));
		
	}
	
	public static void waitForPageLoad(WebDriver driver, WebElement elementName) throws IOException {
		
		WebDriverWait d=new WebDriverWait(driver,10);
		d.until(ExpectedConditions.visibilityOf(elementName));
		
	}
	
	public static void editPeriods(WebDriver driver, String payrollName, String payrollYear) throws IOException, InterruptedException, AWTException {
		
		List <WebElement> payrollYears=driver.findElements(By.xpath(editPeriodsPageObjects.payrollYear+"/option"));
		for(WebElement option :payrollYears) {
			if (option.getText().contains(payrollYear)) {
	        option.click();
	        break;
			}
		}
		Thread.sleep(500);
		if(driver.findElements(By.xpath(editPeriodsPageObjects.save)).size()==1) {
			System.out.println("Failed: Payroll periods of given Payroll Year already exits");
			Assert.fail("Failed: Payroll periods of given Payroll Year already exits");
			}
		else if(driver.findElements(By.xpath(editPeriodsPageObjects.generate)).size()==0){
			System.out.println("Failed: Given Payroll Year dont exits in drop down list");
			Assert.fail("Failed: Given Payroll Year dont exits in drop down list");
			}
		else {
			if(testInputGPMS.payDaybutton==true) {
				driver.findElement(By.xpath(editPeriodsPageObjects.payDay)).click();		
				List <WebElement> payDays=driver.findElements(By.xpath(editPeriodsPageObjects.payDayDropDown+"/option"));
				for(WebElement option :payDays) {	if (option.getText().contains(testInputGPMS.payDayToSelect)) {	option.click();		break;	}	}
				if(testInputGPMS.payDayFirst==true) driver.findElement(By.xpath(editPeriodsPageObjects.payDayFirst)).click();
				else driver.findElement(By.xpath(editPeriodsPageObjects.payDayLast)).click();		
			}
			else {
				driver.findElement(By.xpath(editPeriodsPageObjects.payOffset)).click();
				driver.findElement(By.xpath(editPeriodsPageObjects.payOffset)).sendKeys(testInputGPMS.payOffsetToGive);
				if(testInputGPMS.payOffsetAfter==true) driver.findElement(By.xpath(editPeriodsPageObjects.payOffsetAfter)).click();
				else driver.findElement(By.xpath(editPeriodsPageObjects.payOffsetBefore)).click();
			}
		}
		driver.findElement(By.xpath(editPeriodsPageObjects.bankTransferOffset)).sendKeys(testInputGPMS.bankTransferOffset);
		driver.findElement(By.xpath(editPeriodsPageObjects.generate)).click();
		if(driver.findElements(By.xpath(editPeriodsPageObjects.save)).size()==0) {
			System.out.println("Failed: Pay Period not generted for Payroll-Error Message: "+driver.findElement(By.xpath(editPeriodsPageObjects.errorMessage)).getAttribute("innerText"));
			Assert.fail("Failed: Pay Period not generted for Payroll-Error Message: "+driver.findElement(By.xpath(editPeriodsPageObjects.errorMessage)).getAttribute("innerText"));
		}else {
			commonMethods.takeScreenShot(driver, "Generated Periods");
			driver.findElement(By.xpath(editPeriodsPageObjects.save)).click();
			if(!driver.findElement(By.xpath(payrollPageObjects.payrollPageHeader)).getAttribute("innerText").contentEquals("Payroll Page")) {
				System.out.println("Failed: Generated Periods was not saved-Error Message"+driver.findElement(By.xpath(editPeriodsPageObjects.errorMessage)).getAttribute("innerText"));
				commonMethods.takeScreenShot(driver, "Failed Generated Periods was not saved-Error Message"+driver.findElement(By.xpath(editPeriodsPageObjects.errorMessage)).getAttribute("innerText"));
				Assert.fail("Failed: Generated Periods was not saved-Error Message"+driver.findElement(By.xpath(editPeriodsPageObjects.errorMessage)).getAttribute("innerText"));
			}else {
				System.out.println("Passed: Generated Periods for year "+payrollYear+" in "+payrollName+" payroll");
				commonMethods.takeScreenShot(driver, "Passed Generated Periods for year "+payrollYear+" in "+payrollName+" payroll");	
			}
		}
		Thread.sleep(1500);
	}

	public static WebElement selectFromList(WebDriver driver, String elementName, String textToMatch) {
		
		WebElement option=null;
		List <WebElement> list=driver.findElements(By.xpath(elementName+"/option"));
		for(WebElement select :list) {
			if (select.getText().contains(textToMatch)) {
				option= select;
	        break;
			}
		}
		return option;
	}
	
	public static void errorMesssage(WebDriver driver, String fileName) {
		
	}
}
