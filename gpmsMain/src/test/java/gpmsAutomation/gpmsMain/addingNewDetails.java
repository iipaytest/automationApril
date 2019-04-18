package gpmsAutomation.gpmsMain;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import gpmsAutomation.pageObjectsGPMS.addNewEmployeePageObjects;
import gpmsAutomation.pageObjectsGPMS.addNewPayrollPageObjects;
import gpmsAutomation.pageObjectsGPMS.companyAdminPageObjects;
import gpmsAutomation.pageObjectsGPMS.createPayrollAssignmentPageObjects;
import gpmsAutomation.pageObjectsGPMS.elementTypeSelectionPageObjects;
import gpmsAutomation.pageObjectsGPMS.employeeDetailsPageObjects;
import gpmsAutomation.pageObjectsGPMS.menuBarLinks;
import gpmsAutomation.pageObjectsGPMS.menuPageObjects;
import gpmsAutomation.pageObjectsGPMS.payrollPageObjects;
import gpmsAutomation.pageObjectsGPMS.payrollSearchPageObjects;
import gpmsAutomation.pageObjectsGPMS.userDefinedOrgUnitAdminPageObjects;
import gpmsAutomation.reusableMethods.commonMethods;
import gpmsAutomation.testInputs.testInputGPMS;



public class addingNewDetails extends basicDetails {

	@Test 
	//Adding New employee to TST3 for given details in testInputGPMS class
	public void addEmployee() throws AWTException, InterruptedException, IOException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		
		if(driver.findElements(By.xpath(menuPageObjects.warning)).size()==0) {
			System.out.println("Failed: Add Employee step, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' already exists");
			commonMethods.takeScreenShot(driver, "Employee Details Exists");
			Assert.fail("Failed: Add Employee step, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' already exists");
		}else {
			driver.findElement(By.xpath(menuPageObjects.warning)).click();
			menuBarLinks.goToAddEmployee(driver);
			driver.findElement(By.xpath(addNewEmployeePageObjects.title)).sendKeys(testInputGPMS.title);
			driver.findElement(By.xpath(addNewEmployeePageObjects.forename)).sendKeys(testInputGPMS.forename);
			driver.findElement(By.xpath(addNewEmployeePageObjects.surname)).sendKeys(testInputGPMS.surname);
			driver.findElement(By.xpath(addNewEmployeePageObjects.employeeNo)).sendKeys(testInputGPMS.emplyeeNo);
			driver.findElement(By.xpath(addNewEmployeePageObjects.startDate)).sendKeys(testInputGPMS.startDate);
			driver.findElement(By.xpath(addNewEmployeePageObjects.dOB)).sendKeys(testInputGPMS.dOB);
			driver.findElement(By.xpath(addNewEmployeePageObjects.gender)).sendKeys(testInputGPMS.gender);
			
			driver.findElement(By.xpath(addNewEmployeePageObjects.addressRef)).clear();
			driver.findElement(By.xpath(addNewEmployeePageObjects.addressRef)).sendKeys(testInputGPMS.addressRef);
			driver.findElement(By.xpath(addNewEmployeePageObjects.addressLine1)).sendKeys(testInputGPMS.addressLine1);
			driver.findElement(By.xpath(addNewEmployeePageObjects.postalTown)).sendKeys(testInputGPMS.postalTown);
			driver.findElement(By.xpath(addNewEmployeePageObjects.country)).sendKeys(testInputGPMS.country);
			
			driver.findElement(By.xpath(addNewEmployeePageObjects.save)).click();
			
			try {
				//commonMethods.waitForPageLoad(driver, driver.findElement(employeeDetailsPageObjects.createPayrollAssignment));
				Assert.assertEquals(1, driver.findElements(By.xpath(employeeDetailsPageObjects.createPayrollAssignment)).size());
				System.out.println("Passed: New Employee added successfully");
				commonMethods.takeScreenShot(driver, "New Employee added successfully");
				System.out.println("	Employee details--\n	Employee No: "+driver.findElement(By.xpath(employeeDetailsPageObjects.employeeNo)).getAttribute("value")+
						",\n	Full Name: "+driver.findElement(By.xpath(employeeDetailsPageObjects.forename)).getAttribute("value")+" "+driver.findElement(By.xpath(employeeDetailsPageObjects.surname)).getAttribute("value")+
						",\n	DOB "+driver.findElement(By.xpath(employeeDetailsPageObjects.dateOfBirth)).getAttribute("value"));
			}catch (Error e) {
				System.out.println("Failed: Creating New Employee Record, Error Messages are as below");
				System.out.println(driver.findElement(By.xpath(addNewEmployeePageObjects.errorMessage)).getText());
				commonMethods.takeScreenShot(driver, "New Employee record creation failed");
				Assert.fail("Failed: Creating New Employee Record -- "+driver.findElement(By.xpath(addNewEmployeePageObjects.errorMessage)).getText());
			}
		}	
		
	}
	

	@Test 
	//Adding new company 
	public void addNewCompany() throws IOException, AWTException, InterruptedException {
		
		menuBarLinks.goToCompanyAdmin(driver);
		
		if(driver.findElements(By.xpath(companyAdminPageObjects.newCompany)).size()==0) {
			System.out.println("Failed adding new Company, as we could not able to navigate to Company Selection Page");
			commonMethods.takeScreenShot(driver, "Failed adding new Company_Navigation error");
			Assert.fail("Failed adding new Company, as we could able to navigate to Company Selection Page");
		}else {
			driver.findElement(By.xpath(companyAdminPageObjects.newCompany)).click();
			driver.findElement(By.xpath(companyAdminPageObjects.companyName)).sendKeys(testInputGPMS.newCompanyName);
			driver.findElement(By.xpath(companyAdminPageObjects.addressLine1)).sendKeys(testInputGPMS.companyAddressLine1);
			driver.findElement(By.xpath(companyAdminPageObjects.postalTown)).sendKeys(testInputGPMS.companyPostalTown);
			driver.findElement(By.xpath(companyAdminPageObjects.country)).sendKeys(testInputGPMS.companyCountry);
			
			driver.findElement(By.xpath(companyAdminPageObjects.save)).click();
			if(driver.findElement(By.xpath(companyAdminPageObjects.success)).getAttribute("innerText").contains("Changes successfully saved")) {
				System.out.println("Passed adding new Company Details");
				commonMethods.takeScreenShot(driver, "Passed adding new Company Details");
				driver.findElement(By.xpath(companyAdminPageObjects.returnToCompanySelection)).click();
			}else {
				System.out.println("Failed adding new Company Details");
				if(driver.findElement(By.xpath(companyAdminPageObjects.error)).getAttribute("innerText").contains("ERROR")) {
				System.out.println(driver.findElement(By.xpath(companyAdminPageObjects.error)).getAttribute("innerText"));}
				System.out.println(driver.findElement(By.xpath(companyAdminPageObjects.errorsummary)).getAttribute("innerText"));
				commonMethods.takeScreenShot(driver, "Failed adding new Company Details");
				Assert.fail("Failed adding new Company Details");
			}
		}
	}
	
	
	@Test 
	//Adding new Org Unit
	public void addNewOrgUnit() throws IOException, AWTException, InterruptedException {
		
		menuBarLinks.goToUserDefinedOrgUnits(driver);
	
		
		int i=driver.findElements(By.xpath(userDefinedOrgUnitAdminPageObjects.userDefinedOrgsTable+"/tbody/tr")).size();
		Actions action = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.userDefinedOrgsTable+"/tbody/tr["+i+"]"));
		action.moveToElement(mainMenu).click().sendKeys(testInputGPMS.newOrgUnit).perform();
		driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.save)).click();
		
		if(driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.sucessful)).getAttribute("innerText").contains("successfully")) {
			commonMethods.takeScreenShot(driver, "Passed Saving New Org unit");
			System.out.println("Passed: New Org unit saved successfully");
		}else {
			if(driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.error)).getAttribute("innerText").contains("ERROR")) {
				System.out.println(driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.error)).getAttribute("innerText"));}
			if(driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.warning)).getAttribute("innerText").contains("Warning")) {
				System.out.println(driver.findElement(By.xpath(userDefinedOrgUnitAdminPageObjects.warning)).getAttribute("innerText"));}
			commonMethods.takeScreenShot(driver, "Failed Saving New Org unit");
			Assert.fail("Failed: Saving New Org unit");
		}
	
	//Adding details for new Org unit created	
		userDefinedOrgUnitAdminPageObjects.newUserOrgUnitConfiguration(driver, testInputGPMS.newOrgUnit, testInputGPMS.newNames, testInputGPMS.newReferences);
	}
	
	
	@Test
	//Adding new Element Type
	public void addNewElementType() throws IOException, AWTException, InterruptedException {
		menuBarLinks.goToElementTypeAdmin(driver);
		driver.findElement(By.xpath(elementTypeSelectionPageObjects.addNewElement)).click();
		driver.findElement(By.xpath(elementTypeSelectionPageObjects.nameElementType)).sendKeys(testInputGPMS.newElementTypeName);
		List <WebElement> fromParties=driver.findElements(By.xpath(elementTypeSelectionPageObjects.fromParty+"/option"));
		for(WebElement option :fromParties) {
			if (option.getText().contains(testInputGPMS.newElementTypeFromParty)) {
	        option.click();
	        break;
			}
		}
		List <WebElement> toParties=driver.findElements(By.xpath(elementTypeSelectionPageObjects.toParty+"/option"));
		for(WebElement option :toParties) {
			if (option.getText().contains(testInputGPMS.newElementTypeToParty)) {
	        option.click();
	        break;
			}
		}
		if(testInputGPMS.newElementTypeDescription!=null) {driver.findElement(By.xpath(elementTypeSelectionPageObjects.descriptionElementType)).sendKeys(testInputGPMS.newElementTypeDescription);}
		if(testInputGPMS.newElementTypeConsolidation!=null) {driver.findElement(By.xpath(elementTypeSelectionPageObjects.consolidationETypeName)).sendKeys(testInputGPMS.newElementTypeConsolidation);}
		if(testInputGPMS.newElementTypeNegate==true) {driver.findElement(By.xpath(elementTypeSelectionPageObjects.negateCheckBox)).click();}
		if(testInputGPMS.newElementTypeObsolete==true) {driver.findElement(By.xpath(elementTypeSelectionPageObjects.obsoleteCheckBox)).click();}
		driver.findElement(By.xpath(elementTypeSelectionPageObjects.save)).click();
		
		if(driver.findElement(By.xpath(elementTypeSelectionPageObjects.sucessful)).getAttribute("innerText").contains("successfully")) {
			commonMethods.takeScreenShot(driver, "Passed Saving New Element Type");
			System.out.println("Passed: New Element Type saved successfully");
		}else {
			System.out.println("Failed: Adding new Element Type");
			if(driver.findElement(By.xpath(elementTypeSelectionPageObjects.error)).getAttribute("innerText").contains(" ")){System.out.println("	Error Message: "+driver.findElement(By.xpath(elementTypeSelectionPageObjects.error)).getAttribute("innerText"));}
			if(driver.findElement(By.xpath(elementTypeSelectionPageObjects.warning)).getAttribute("innerText").contains(" ")){System.out.println("	Warning Message: "+driver.findElement(By.xpath(elementTypeSelectionPageObjects.warning)).getAttribute("innerText"));}
			if(driver.findElement(By.xpath(elementTypeSelectionPageObjects.errorSummary)).getAttribute("innerText").contains(" ")){System.out.println("	Error Summary: "+driver.findElement(By.xpath(elementTypeSelectionPageObjects.errorSummary)).getAttribute("innerText"));}
			commonMethods.takeScreenShot(driver, "Failed Saving New Element Type");
			Assert.fail("Failed: Saving New Element Type");
		}
	}

	
	@Test
	//Adding new Payroll
	public void addPayroll() throws AWTException, InterruptedException, IOException {
		
		menuBarLinks.goToForPayroll(driver);
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollName)).sendKeys(testInputGPMS.payrollName);
		driver.findElement(By.xpath(payrollSearchPageObjects.search)).click();
		Thread.sleep(500);
		
		if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollClick(testInputGPMS.payrollName))).size()==1) {
			System.out.println("Failed Add Payoll step, as  Payoll Name '"+ testInputGPMS.payrollName +"' already exists");
			Assert.fail("Failed Add Payoll step, as  Payoll Name '"+ testInputGPMS.payrollName +"' already exists");
		}
		else {
			
			menuBarLinks.goToAddPayroll(driver);
			
			//Ruleset selection
			List <WebElement> rulesets=driver.findElements(By.xpath(addNewPayrollPageObjects.ruleset+"/option"));
			for(WebElement option :rulesets) {
				if (option.getText().contains(testInputGPMS.rulesetCountry) && option.getText().contains(testInputGPMS.rulesetCurrency)) {
		        option.click();
		        break;
				}
			}
			
			//Payroll Name type in 
			driver.findElement(By.xpath(addNewPayrollPageObjects.payrollName)).sendKeys(testInputGPMS.payrollName);
			
			//Company Name selection
			List <WebElement> companies=driver.findElements(By.xpath(addNewPayrollPageObjects.companyName+"/option"));
			if(testInputGPMS.companyName!=null) {
			for(WebElement option :companies) {
				if (option.getText().equalsIgnoreCase(testInputGPMS.companyName)) {
		        option.click();
		        break;
				}
			}}else {
				for(WebElement option :companies) {
					if (option.getText().equalsIgnoreCase(testInputGPMS.newCompanyName)) {
			        option.click();
			        break;
					}
				}
			}
			
			//Pay Frequency selection
			List <WebElement> payFrquencies=driver.findElements(By.xpath(addNewPayrollPageObjects.payFrequency+"/option"));
			for(WebElement option :payFrquencies) {
				if (option.getText().equalsIgnoreCase(testInputGPMS.payFrequency)) {
		        option.click();
		        break;
				}
			}
			
			driver.findElement(By.xpath(addNewPayrollPageObjects.save)).click();
			if(driver.findElements(By.xpath(addNewPayrollPageObjects.editPeriods)).size()==1) {
				System.out.println("Passed: Payroll: '"+ testInputGPMS.payrollName+"' Created successful");
				commonMethods.takeScreenShot(driver, "Payroll '"+ testInputGPMS.payrollName+"' Created successful");
			}else {
				System.out.println("Failed: Payroll: '"+ testInputGPMS.payrollName+"' creation");
				commonMethods.takeScreenShot(driver, "Failed Payroll '"+ testInputGPMS.payrollName+"' creation");
				Assert.fail("Failed: Adding '"+ testInputGPMS.payrollName+"' Payroll");
			}
		}
		
	}

	
	@Test
	//Add new Payroll Periods to existing payroll
	public void editPayrollPeriods() throws AWTException, InterruptedException, IOException {
		
		menuBarLinks.goToForPayroll(driver);
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollName)).sendKeys(testInputGPMS.payrollName);
		driver.findElement(By.xpath(payrollSearchPageObjects.search)).click();
		Thread.sleep(500);
		
		if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollClick(testInputGPMS.payrollName))).size()==0) {
			System.out.println("Failed: Can not Edit Payoll Periods, as  Payoll Name '"+ testInputGPMS.payrollName +"' do not exists");
			Assert.assertEquals(0, driver.findElements(By.xpath(payrollSearchPageObjects.payrollClick(testInputGPMS.payrollName))).size());
		}
		else {
			
			driver.findElement(By.xpath(payrollSearchPageObjects.payrollClick(testInputGPMS.payrollName))).click();
			driver.findElement(By.xpath(payrollPageObjects.payrollActions)).click();
			driver.findElement(By.xpath(payrollPageObjects.editPeriods)).click();
			commonMethods.editPeriods(driver, testInputGPMS.payrollName, testInputGPMS.payrollYearPeriod);
		}
	}

	
	@Test 
	//To Create Payroll Assignment for existing employee
	public void createPayrollAssignment() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant create Payroll Assignement, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			Assert.assertEquals("1", warning);
		}else {
			
			driver.findElement(By.xpath(employeeDetailsPageObjects.createPayrollAssignment)).click();
			
			List <WebElement> payrolls=driver.findElements(By.xpath(createPayrollAssignmentPageObjects.payroll+"/option"));
			for(WebElement option :payrolls) {
				if (option.getText().contains(testInputGPMS.payrollName)) {
		        option.click();
		        break;
				}
			}
			Thread.sleep(500);
			if(driver.findElements(By.xpath(createPayrollAssignmentPageObjects.paymentType)).size()==0) {
				System.out.println("Cant create Payroll Assignement, as  Payroll Name'"+ testInputGPMS.payrollName +"' don't exists");
				Assert.assertEquals(1, 0);
			}
			driver.findElement(By.xpath(createPayrollAssignmentPageObjects.payrollStartDate)).sendKeys(testInputGPMS.startDate);
			Thread.sleep(500);
			driver.findElement(By.xpath(createPayrollAssignmentPageObjects.actualStartDate)).sendKeys(testInputGPMS.startDate);
			Thread.sleep(500);
			List <WebElement> employeeAddresses=driver.findElements(By.xpath(createPayrollAssignmentPageObjects.employeeAddress+"/option"));
			for(WebElement option :employeeAddresses) {
				if (option.getText().contains(testInputGPMS.addressRef)) {
		        option.click();
		        break;
				}
			}
			Thread.sleep(500);
			
			List <WebElement> paymentTypes=driver.findElements(By.xpath(createPayrollAssignmentPageObjects.paymentType+"/option"));
			for(WebElement option :paymentTypes) {
				if (option.getText().contains(testInputGPMS.paymentType)) {
		        option.click();
		        break;
				}
			}
			Thread.sleep(500);
			List <WebElement> splitTypes=driver.findElements(By.xpath(createPayrollAssignmentPageObjects.splitType+"/option"));
			for(WebElement option :splitTypes) {
				if (option.getText().contains(testInputGPMS.splitType)) {
		        option.click();
		        break;
				}
			}
			Thread.sleep(500);
			driver.findElement(By.xpath(createPayrollAssignmentPageObjects.save)).click();	
			Thread.sleep(500);
			
			if(driver.findElements(By.xpath(createPayrollAssignmentPageObjects.employeePayrollDetails)).size()==0) {
				System.out.println("Failed Create Payroll Assignement step");
				if(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage1)).isDisplayed()==true) System.out.println(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage1)).getAttribute("innerText"));
				if(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage2)).isDisplayed()==true) System.out.println(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage2)).getAttribute("innerText"));
				commonMethods.takeScreenShot(driver, "Failed Create Payroll Assignement step");
				Assert.fail("Failed Create Payroll Assignement step");
			}else {
				System.out.println("Passed: Create Payroll Assignement Successful");
				commonMethods.takeScreenShot(driver, "Create Payroll Assignement Successful");
			}
			
			driver.findElement(By.xpath(createPayrollAssignmentPageObjects.backToEmployeeDetails)).click();
			
		}
	}

	
}
