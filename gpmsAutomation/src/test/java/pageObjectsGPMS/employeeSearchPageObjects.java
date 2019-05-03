package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class employeeSearchPageObjects {

	public static final String surname="//*[@id='txtLastName']";
	public static final String employeeNumber="//*[@id='txtEmpNum']";
	public static final String emailAddress="//*[@id='txtEmailAddress']";
	public static final String forename="//*[@id='txtFirstName']";
	public static final String payroll="//*[@id='lstPayrollID_DropDown']";
	public static final String dateComparison="//*[@id='lstEmpDateComparisonType_DropDown']";
	public static final String date="//*[@id='dpEmpDateComparison_txtDate']";
	public static final String search="//*[@id='btnSearch']";
	
	public static final String missingEmailAddress="//*[@id='cbEmptyEmailSearch']";
	
	public static final String employeeToBeSelected(String employeeNo) {	
		String employeeToBeSelected="//*[contains(text(), '"+employeeNo+"')]";
		return employeeToBeSelected;
	}
	
	public static Boolean isEmployeeExists(WebDriver driver, String employeeNo) throws InterruptedException, AWTException, IOException {		
		menuBarLinks.goToForEmployee(driver);
		driver.findElement(By.xpath(employeeNumber)).sendKeys(employeeNo);
		driver.findElement(By.xpath(search)).click();
		Thread.sleep(500);
		Boolean passed=null;
			
		if(driver.findElements(By.xpath(employeeToBeSelected(employeeNo))).size()!=0){
			passed=true;
		}else {
			passed=false;
			System.out.println("Failed: As  Payoll Name '"+ testInputGPMS.payrollName +"' don't exists");
			commonMethods.takeScreenShot(driver, "Payroll Details dont Exists");
			Assert.fail("Failed: As  Payoll Name '"+ testInputGPMS.payrollName +"' don't exists");
		}	
		
		return passed;	
	}
	
	public static void goToRequiredEmployeePage(WebDriver driver, String employeeNo) throws AWTException, InterruptedException, IOException {
		
		menuBarLinks.goToForEmployee(driver);
		driver.findElement(By.xpath(employeeNumber)).sendKeys(employeeNo);
		driver.findElement(By.xpath(search)).click();
		Thread.sleep(500);
		
			driver.findElement(By.xpath(employeeToBeSelected(employeeNo))).click();
			
	}
	
}
