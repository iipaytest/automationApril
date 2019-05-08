package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.employeeDetailsEPAPageObjects;
import pageObjectsGPMS.employeeDetailsPageObjects;
import pageObjectsGPMS.employeeSearchPageObjects;
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class addingPayDedsEntitlementsUserDefined extends basicDetails {

	@Test 
	//To add PayDeds and Entitlements
	public void addPayDeds() throws IOException, AWTException, InterruptedException {
		
		if(employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo)==false) {
			System.out.println("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed add PayDeds and Entitlements_Emp No dont exists");
			Assert.fail("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
		
		}else{
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed add PayDeds and Entitlements_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				for(int x=0; x<testInputGPMS.payDedsToAdd.length; x++) {
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.visibleText(testInputGPMS.payDedsToAdd[x]))).click();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsDropDown)));
					if(testInputGPMS.payDedsToAdd[x].contentEquals("Payments")) {
						for(int i=0;i<testInputGPMS.paymentsTypesToAdd.length;i++) {	
							commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsDropDown, testInputGPMS.paymentsTypesToAdd[i][0]);
							commonMethods.addPayDedsDetails(driver, testInputGPMS.paymentsTypesToAdd[i][1], testInputGPMS.paymentsTypesToAdd[i][2], testInputGPMS.paymentsTypesToAdd[i][3]);
							if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.success)).getAttribute("innerText").contains("Changes successfully saved")) {
								System.out.println("Passed: Adding Payments '"+ testInputGPMS.paymentsTypesToAdd[i][0] +"'");
								driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
							}else {
								System.out.println("Failed: Adding Payments '"+ testInputGPMS.paymentsTypesToAdd[0][0] +"'");
								System.out.println(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.error)).getAttribute("innerText"));
								driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
							}
						}	
					}else if(testInputGPMS.payDedsToAdd[x].contentEquals("Deductions")) {
						for(int i=0;i<testInputGPMS.deductionsTypesToAdd.length;i++) {	
							commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsDropDown, testInputGPMS.deductionsTypesToAdd[i][0]);
							commonMethods.addPayDedsDetails(driver, testInputGPMS.deductionsTypesToAdd[i][1], testInputGPMS.deductionsTypesToAdd[i][2], testInputGPMS.deductionsTypesToAdd[i][3]);
							if(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.success)).getAttribute("innerText").contains("Changes successfully saved")) {
								System.out.println("Passed: Adding Deductions '"+ testInputGPMS.deductionsTypesToAdd[i][0] +"'");
								driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
							}else {
								System.out.println("Failed: Adding Deductions '"+ testInputGPMS.deductionsTypesToAdd[0][0] +"'");
								System.out.println(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.error)).getAttribute("innerText"));
								driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
							}
						}
					}else {System.out.println("No PayDeds to add");}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				}
				
			}
		}
		
	}	
	
	
}
