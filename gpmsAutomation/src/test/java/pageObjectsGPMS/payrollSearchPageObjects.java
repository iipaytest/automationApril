package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class payrollSearchPageObjects {
	public static final String payrollName="//input[@id='txtPayrollName']";
	public static final String resultsPerPage="//select[@id='lstResultsPerPage_DropDown']";
	public static final String search="//input[@id='btnSearch']";
	
	
	public static final String payrollToBeSelected(String payrollName) {	
		String payrollToBeSelected="//*[contains(text(), '"+payrollName+"')]";
		return payrollToBeSelected;
	}
	
	public static final String employeeNo="//input[@id='txtEmployeeNumber']";
	public static final String startDate="//input[@id='calStartDate_txtDate']";
	public static final String dOB="//input[@id='calDOB_txtDate']";
	public static final String gender="//select[@id='lstGender_DropDown']";
	
	
	public static Boolean isPayrollExists(WebDriver driver, String payrollName) throws InterruptedException, AWTException, IOException {		
		menuBarLinks.goToForPayroll(driver);
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollName)).sendKeys(payrollName);
		driver.findElement(By.xpath(payrollSearchPageObjects.search)).click();
		Thread.sleep(500);
		Boolean passed=null;
			
			if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollToBeSelected(payrollName))).size()!=0){
				passed=true;
			}else {
				passed=false;
				System.out.println("Failed: As  Payoll Name '"+ testInputGPMS.payrollName +"' don't exists");
				commonMethods.takeScreenShot(driver, "Payroll Details dont Exists");
				Assert.fail("Failed: As  Payoll Name '"+ testInputGPMS.payrollName +"' don't exists");
			}
			
		return passed;
		
	}
	
	public static void goToRequiredPayrollPage(WebDriver driver, String payrollName) throws AWTException, InterruptedException, IOException {	
		//Assuming Payroll has been searched first	(isPayrollExists method performed)
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollToBeSelected(payrollName))).click();
		
			
	}
}
