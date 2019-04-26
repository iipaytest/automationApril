package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class mainTestClassGPMS {
	
	static WebDriver driver;
	
	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//commonMethods.takeScreenShot(driver, "GPMS Version Verified");
		//String url=testInputGPMS.urlTST3redirector;
		
		
		driver.get(testInputGPMS.urlTST3redirector);
		driver.findElement(By.cssSelector("input#ClientId")).clear();
		driver.findElement(By.cssSelector("input#ClientId")).sendKeys(testInputGPMS.clientID);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		
		
		driver.findElement(By.cssSelector("input#Username")).sendKeys(testInputGPMS.userName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(testInputGPMS.password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		
			

	
		
		
		
		
		
		
		
		
		
		
/*		menuBarLinks.goToUserDefinedOrgUnits(driver);
		int i=driver.findElements(By.xpath("//*[@id='customTypes']/tbody/tr")).size();
		System.out.println(i);
        for(int j=1; j<=i; j++) {
        	String s= driver.findElement(By.xpath("//*[@id='customTypes']/tbody/tr["+j+"]/td[1]/input")).getAttribute("defaultValue");
        	System.out.println(s);
        	if(testInputGPMS.newOrgUnit.contentEquals(s)) {
        		System.out.println(s);
        		driver.findElement(By.xpath("//*[@id='customTypes']/tbody/tr["+j+"]/td[2]/input")).click();
        		for(int x=0; x<2; x++) {
        			driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.newName)).sendKeys(testInputGPMS.newNamesReferences[x][x]);
        			driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.newReference)).sendKeys(testInputGPMS.newNamesReferences[x][x+1]);
        			driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.save)).click();
        			Thread.sleep(1000);
        			}
        	}
        }*/
        
     //   userDefinedOrgUnitAdminPageObjects.newUserOrgUnitConfiguration(driver, testInputGPMS.newOrgUnit, testInputGPMS.newNamesReferences);
        
	/*	driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed add PayDeds and Entitlements_Emp No dont exists");
			Assert.fail("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed add PayDeds and Entitlements_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add PayDeds and Entitlements, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				for(int x=0; x<testInputGPMS.payDedsToAdd.length; x++) {
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.visibleText(testInputGPMS.payDedsToAdd[x]))).click();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(employeeDetailsEPAPageObjects.dropDownPayments)));
					if(testInputGPMS.payDedsToAdd[x].contentEquals("Payments")) {
						for(int i=0;i<testInputGPMS.paymentsTypesToAdd.length;i++) {	
							commonMethods.selectFromList(driver, employeeDetailsEPAPageObjects.dropDownPayments, testInputGPMS.paymentsTypesToAdd[i][0]);
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
							commonMethods.selectFromList(driver, employeeDetailsEPAPageObjects.dropDownDeductions, testInputGPMS.deductionsTypesToAdd[i][0]);
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
		
      */  
        
	}

}	
			
		 
		
		
		
/*		menuBarLinks.goToCompanyAdmin(driver);
		menuBarLinks.goToSupportDetailsAdmin(driver);
		menuBarLinks.goToClientLogoAdmin(driver);
		menuBarLinks.goToBoxSettings(driver);
		menuBarLinks.goToTermsOfUseText(driver);
		menuBarLinks.goToGLReportConfigurations(driver);
		menuBarLinks.goToPaymentTypes(driver);
		menuBarLinks.goToEmpNoAutoGeneration(driver);
		menuBarLinks.goToElementTypeTranslations(driver);
		menuBarLinks.goToElementTypeMappings(driver);
		menuBarLinks.goToElementBreakdownConfigurations(driver);
		menuBarLinks.goToDocumentStorage(driver);
		menuBarLinks.goToWorkdayPECIPayrollMappings(driver);
		menuBarLinks.goToUploadReformatters(driver);
		menuBarLinks.goToPercentageCheckers(driver);
*/		
		
		//menuBarLinks.forEmployee(driver);
		//menuBarLinks.forPayroll(driver);
		//menuBarLinks.addEmployee(driver);
		//menuBarLinks.addPayroll(driver);
		
		
		
	
	



