package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

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
			
			
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.createPayrollAssignment)).size()==1) {
				System.out.println("Passed: New Employee added successfully");
				commonMethods.takeScreenShot(driver, "New Employee added successfully");
				System.out.println("	Employee details--\n	Employee No: "+driver.findElement(By.xpath(employeeDetailsPageObjects.employeeNo)).getAttribute("value")+
						",\n	Full Name: "+driver.findElement(By.xpath(employeeDetailsPageObjects.forename)).getAttribute("value")+" "+driver.findElement(By.xpath(employeeDetailsPageObjects.surname)).getAttribute("value")+
						",\n	DOB "+driver.findElement(By.xpath(employeeDetailsPageObjects.dateOfBirth)).getAttribute("value"));
			}else {
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
		
	//Adding new Org unit created
	userDefinedOrgUnitAdminPageObjects.createNewUserOrgUnit(driver, testInputGPMS.newOrgUnit);
	
	//Adding details for new Org unit created	
	userDefinedOrgUnitAdminPageObjects.newUserOrgUnitConfiguration(driver, testInputGPMS.newOrgUnit, testInputGPMS.newNamesReferences);
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
		
		if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollToBeSelected(testInputGPMS.payrollName))).size()==1) {
			System.out.println("Failed Add Payoll step, as  Payoll Name '"+ testInputGPMS.payrollName +"' already exists");
			commonMethods.takeScreenShot(driver, "Failed Add Payoll_Payroll Name dont exists");
			Assert.fail("Failed Add Payoll step, as  Payoll Name '"+ testInputGPMS.payrollName +"' already exists");
		}
		else {
			
			menuBarLinks.goToAddPayroll(driver);
			
			//Ruleset selection
			//commonMethods.selectFromList(driver, addNewPayrollPageObjects.ruleset, testInputGPMS.rulesetCountry+testInputGPMS.rulesetCurrency);
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
	//Editing/Adding pay periods for payroll
	public void editPayrollPeriods() throws AWTException, InterruptedException, IOException {
		
		menuBarLinks.goToForPayroll(driver);
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollName)).sendKeys(testInputGPMS.payrollName);
		driver.findElement(By.xpath(payrollSearchPageObjects.search)).click();
		Thread.sleep(500);
		
		if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollToBeSelected(testInputGPMS.payrollName))).size()==0) {
			System.out.println("Failed: Can not Edit Payoll Periods, as  Payoll Name '"+ testInputGPMS.payrollName +"' do not exists");
			commonMethods.takeScreenShot(driver, "Failed editing Payroll periods_Payroll name dont exists");
			Assert.fail("Failed: Can not Edit Payoll Periods, as  Payoll Name '"+ testInputGPMS.payrollName +"' do not exists");
		}
		else {
			
			driver.findElement(By.xpath(payrollSearchPageObjects.payrollToBeSelected(testInputGPMS.payrollName))).click();
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
			commonMethods.takeScreenShot(driver, "Failed creating Payroll Assignment_Emp No dont exists");
			Assert.fail("Failed: Cant create Payroll Assignement, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
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
				System.out.println("Failed: Cant create Payroll Assignement, as  Payroll Name'"+ testInputGPMS.payrollName +"' don't exists");
				commonMethods.takeScreenShot(driver, "Failed creating Payroll Assignment_Payroll Name dont exists");
				Assert.fail("Cant create Payroll Assignement, as  Payroll Name'"+ testInputGPMS.payrollName +"' don't exists");
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
				System.out.println("Failed: Create Payroll Assignement step");
				if(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage1)).isDisplayed()==true) System.out.println(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage1)).getAttribute("innerText"));
				if(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage2)).isDisplayed()==true) System.out.println(driver.findElement(By.xpath(createPayrollAssignmentPageObjects.errorMessage2)).getAttribute("innerText"));
				commonMethods.takeScreenShot(driver, "Failed Create Payroll Assignement step");
				Assert.fail("Failed: Create Payroll Assignement step");
			}else {
				System.out.println("Passed: Create Payroll Assignement Successful");
				commonMethods.takeScreenShot(driver, "Create Payroll Assignement Successful");
			}
			
			driver.findElement(By.xpath(createPayrollAssignmentPageObjects.backToEmployeeDetails)).click();
			
		}
	}

	@Test 
	//To add PayDeds and Entitlements
	public void addPayDeds() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
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
	
	@Test
	public void addPaymentsRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Payments_Emp No dont exists");
			Assert.fail("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Payments_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
			
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payments)).click();				
				int noOfPaymentsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many payments randomly
				System.out.println("Totals Payments added are: "+noOfPaymentsToAdd);
				for(int i=1; i<=noOfPaymentsToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.payDedsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, "indefinite");
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Payments Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}	

	@Test
	public void addDeductionsRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Deductions_Emp No dont exists");
			Assert.fail("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Deductions_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.deductions)).click();				
				int noOfDeductionsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many deductions randomly
				System.out.println("Totals Deductions added are: "+noOfDeductionsToAdd);
				for(int i=1; i<=noOfDeductionsToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.payDedsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, "indefinite");
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Deductions Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}
	
	@Test
	public void addUnitPayRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Unit Pay_Emp No dont exists");
			Assert.fail("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Unit Pay_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPay)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayAllTypes)).click(); Thread.sleep(1000);
				int noOfUnitPaysToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Unit Pays randomly
				System.out.println("Totals Unit Pays added are: "+noOfUnitPaysToAdd);
				for(int i=1; i<=noOfUnitPaysToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.unitPayDropDown);
					if(i==1) {
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayUnits)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(2, 6)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayRateOverride)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(10, 15)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					else {
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayUnits)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(2, 6)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayFinalAmountOverride)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(5, 10)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Unit Pays Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}
	
	
	@Test
	public void addEntitlementsTempRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Temp_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Temp_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTemp)).click();
				int noOfEntitlementsTempsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Temps randomly
				System.out.println("Totals Entitlement Temps added are: "+noOfEntitlementsTempsToAdd);
				for(int i=1; i<=noOfEntitlementsTempsToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTempAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(200, 300)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					else {
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTempAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(200, 300)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Entitlements Temp Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}
	
	
	@Test
	public void addEntitlementsPermRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Perm_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Perm_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPerm)).click();
				int noOfEntitlementsPermsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Perms randomly
				System.out.println("Totals Entitlement Perms added are: "+noOfEntitlementsPermsToAdd);
				for(int i=1; i<=noOfEntitlementsPermsToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, effectiveFrom);
						//driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(100, 200)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, "indefinite");
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(100, 200)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, effectiveFrom);
						
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000)));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Entitlements Perm Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}	
	}
	
	@Test
	public void addEntitlementsUnitPayRandom() throws IOException, AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(testInputGPMS.emplyeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		int warning = driver.findElements(By.xpath(menuPageObjects.warning)).size();
		
		if(warning==1) {
			System.out.println("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Unit Pay_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't exists");
		}else {
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Unit Pay_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPay)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayAllTypes)).click(); Thread.sleep(1000);
				int noOfEntitlementsUnitPaysToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Unit Pays randomly
				System.out.println("Totals Entitlement Unit Pays added are: "+noOfEntitlementsUnitPaysToAdd);
				for(int i=1; i<=noOfEntitlementsUnitPaysToAdd; i++) {
					commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayUnits)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(2, 6)));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayRateOverride)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(10, 15)));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
							commonMethods.successErrorMesssage(driver);
						}
						else {
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayUnits)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(2, 6)));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayFinalAmountOverride)).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(5, 10)));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
							commonMethods.successErrorMesssage(driver);
						}
						
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
						commonMethods.takeScreenShot(driver, "Entitlements Unit Pay Added");
					}
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				}
			}	
		}

}













