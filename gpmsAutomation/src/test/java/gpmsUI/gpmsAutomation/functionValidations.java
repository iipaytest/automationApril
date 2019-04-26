package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class functionValidations extends basicDetails {

	
	@Test
	//This is verify Lock Function from Employee level
	public void lockEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant verify Lock Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed verifying Lock Function from Employee level_Emp No dont exists");
			Assert.fail("Failed: Cant verify Lock Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant verify Lock Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed verifying Lock Function from Employee level_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant verify Lock Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {
				
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.lock)).isDisplayed()) {
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.lock)).click();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)));
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)).click(); Thread.sleep(500);
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
					if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.reset)).isDisplayed()) {	System.out.println("Passed: Lock function from employee level working"); }
					else {
						System.out.println("Failed: Lock function from employee level not working");
						commonMethods.takeScreenShot(driver, "Lock fuction from employee level not working");
						Assert.fail("Failed: Lock function from employee level not working");
					}
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
				}else {
					
					System.out.println("Failed: Lock function from employee level not available, as Employee is in Reset Status");
					commonMethods.takeScreenShot(driver, "Lock fuction from employee level not available");
					Assert.fail("Failed: Lock function from employee level not available, as Employee is in Reset Status");
				}
			}
		}
	}
	
	
	@Test
	//This is verify Reset Function from Employee level
	public void resetEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed verifying Reset Function from Employee level_Emp No dont exists");
			Assert.fail("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed verifying Reset Function from Employee level_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {
				
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.reset)).isDisplayed()) {
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.reset)).click();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)));
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)).click(); Thread.sleep(500);
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
					if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.lock)).isDisplayed()) {	System.out.println("Passed: Reset function from employee level working"); }
					else {
						System.out.println("Failed: Reset function from employee level not working");
						commonMethods.takeScreenShot(driver, "Reset fuction from employee level not working");
						Assert.fail("Failed: Reset function from employee level not working");
					}
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
				}else {
					System.out.println("Failed: Reset function from employee level not available, as Employee is in Lock Status");
					commonMethods.takeScreenShot(driver, "Reset fuction from employee level not available");
					Assert.fail("Failed: Reset function from employee level not available, as Employee is in Lock Status");
				}
			}
		}
	}
	
	
	@Test
	//This is verify Unconfirm Function from Employee level
	public void unconfirmEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant verify Unconfirm Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed verifying Unconfirm Function from Employee level_Emp No dont exists");
			Assert.fail("Failed: Cant verify Unconfirm Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant verify Unconfirm Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed verifying Unconfirm Function from Employee level_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant verify Unconfirm Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {
				
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unconfirm)).isDisplayed()) {
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unconfirm)).click();
					driver.switchTo().alert().accept();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)));
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.refresh)).click(); Thread.sleep(500);
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
					if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.reset)).isDisplayed()) {	System.out.println("Passed: Unconfirm function from employee level working"); }
					else {
						System.out.println("Failed: Unconfirm function from employee level not working");
						commonMethods.takeScreenShot(driver, "Unconfirm fuction from employee level not working");
						Assert.fail("Failed: Unconfirm function from employee level not working");
					}
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
				}else {
					System.out.println("Failed: Unconfirm function from employee level not available, as Employee is not in Confirmed Status");
					commonMethods.takeScreenShot(driver, "Unconfirm fuction from employee level not available");
					Assert.fail("Failed: Unconfirm function from employee level not available, as Employee is not in Confirmed Status");
				}
			}
		}
	}

	
	
	
	
	
	
}
