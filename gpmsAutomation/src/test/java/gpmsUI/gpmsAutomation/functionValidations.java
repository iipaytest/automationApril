package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class functionValidations extends basicDetails {

	
	@Test
	//This is verify Lock, Process, Confirm, Unconfirm and Reset period Function from payroll level
	public void lockProcessConfirmPeriodsFromPayrollLevel() throws AWTException, InterruptedException, IOException {
		
		String payrollName=testInputGPMS.payrollName;;
		String taxYear=null;
		String periodNo=null;
		String periodName=null;
		int countOfEmployeesInPayPeriod=0;
		String[][] table=null;
		int rowNumOfPayPeriod=0;
		int[] stateOfPayroll=new int[4];
		Boolean isLockPeriodFunctionValidated=false;
		Boolean isProcessPeriodFunctionValidated=false;
		Boolean isConfirmPeriodFunctionValidated=false;
		Boolean isUnconfirmPeriodFunctionValidated=false;
		Boolean isResetPeriodFunctionValidated=false;
		
		
		//Navigate to required Payroll
		payrollSearchPageObjects.isPayrollExists(driver, payrollName);
		payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
		
		//Capture details of payroll landing page into a table and get rowNumber, taxYear and periodNo of active payroll
		table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver);
		taxYear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][2];
		periodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][3];
		ArrayList<String> rowData=payrollPageObjects.getDisplayedDetailsOfRequiredPayrollPeriodRow(driver, rowNumOfPayPeriod);
		periodName=rowData.get(1);
		
		
		//Gets the employee in Awaiting, Lock, process and Confirmed count into stateOfPayroll[0]--stateOfPayroll[3]
		//Get the total counts of employees in that payroll as countOfEmployeesInPayPeriod
		for(int j=0; j<4; j++) {
			stateOfPayroll[j]=Integer.parseInt(rowData.get(j+4));
			countOfEmployeesInPayPeriod=countOfEmployeesInPayPeriod+Integer.parseInt(rowData.get(j+4));
		}
		
		//Check if that payroll has any employees, else test fails and terminates
		if(rowNumOfPayPeriod==0 || countOfEmployeesInPayPeriod==0) {
			System.out.println("Failed: Cant verify Lock, Process, Confirm, Unconfirm or Reset function from Payroll level as there are no employees exists in '"+payrollName+"' payroll for period "+periodName);
			commonMethods.takeScreenShot(driver, "Failed Lock, Process, Confirm, Unconfirm or Reset function verification_as No employee exists in Payroll");
			Assert.fail("Failed: Cant verify Lock, Process, Confirm, Unconfirm or Reset function from Payroll level as there are no employees exists in '"+payrollName+"' payroll for period "+periodName);
		}
		
		//This j will let us know the state of pay period, Awaiting j=0, Locked j=1, processed j=2, 
		int j=0;
			for(int z=2; z>=0; z--) {
				if(stateOfPayroll[z]==0) {
					j=z;
				}else {
					j=z; break;
				}
			}	
			
		//This if-else statements will bring required pay period in a payroll to Awaiting Processing
		//And also validates Reset period function and return isRestPeriodFunctionValidated=true
			if(j==1) {//That mean employees are in locked state
				try{
					payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 5).click();
					driver.findElement(By.xpath(payrollPageObjects.resetPeriod)).click();
					String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
					if(reportStatus.equalsIgnoreCase("completed")==false) {
						payrollQueuePageObjects.getReportStatusMessages(driver);
					}
					driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
					isResetPeriodFunctionValidated=true;
				}catch(Exception e) {
					System.out.println("Failed: Reset function is not working as expected");
					commonMethods.takeScreenShot(driver, "Validaing Reset function");
				}
				
			}else if(j==2) {//That mean employees are in processed state
				try{
					payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 6).click();
					driver.findElement(By.xpath(payrollPageObjects.resetPeriod)).click();
					String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
					if(reportStatus.equalsIgnoreCase("completed")==false) {
						payrollQueuePageObjects.getReportStatusMessages(driver);
					}
					driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
					isResetPeriodFunctionValidated=true;
				}catch(Exception e) {
					System.out.println("Failed: Reset function is not working as expected");
					commonMethods.takeScreenShot(driver, "Validaing Reset function");
				}	
			}
		
		//This try-catch loop will lock payroll and validates Lock period function, return isLockPeriodFunctionValidated=true
		
			try{
				payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 4).click();
				driver.findElement(By.xpath(payrollPageObjects.lockPeriod)).click();
				String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
				if(reportStatus.equalsIgnoreCase("completed")==false) {
					payrollQueuePageObjects.getReportStatusMessages(driver);	
				}
				driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
				isLockPeriodFunctionValidated=true;
			}catch(Exception e) {
				System.out.println("Failed: Lock function is not working as expected");
				commonMethods.takeScreenShot(driver, "Validaing Lock function");
			}
		
		if(isLockPeriodFunctionValidated==true)	{
			System.out.println("Passed: Validated Lock Period function from payroll level");
		}else {
			System.out.println("Info: Didn't validate Lock Period function from payroll level, as awaiting employees=0");
		}
			
			
		//This if block will only works if reset function is validated in above case , return isResetPeriodFunctionValidated=true
		if(isResetPeriodFunctionValidated!=true) {
				try{
					payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 5).click();
					driver.findElement(By.xpath(payrollPageObjects.resetPeriod)).click();
					String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
					if(reportStatus.equalsIgnoreCase("completed")==false) {
						payrollQueuePageObjects.getReportStatusMessages(driver);
					}
					driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
					isResetPeriodFunctionValidated=true;
					payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 4).click();
					driver.findElement(By.xpath(payrollPageObjects.lockPeriod)).click();
					payrollQueuePageObjects.refreshUntillComplete(driver);
					driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
				}catch(Exception e) {
					System.out.println("Failed: Reset function is not working as expected");
					commonMethods.takeScreenShot(driver, "Validaing Reset function");
				}
			}
		if(isResetPeriodFunctionValidated==true)	{	System.out.println("Passed: validated Reset Period function from payroll level");	}
		
		//This try-catch loop will process payroll and validates Process period function, return isProcessPeriodFunctionValidated=true
			try{
		
				payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 5).click();
				driver.findElement(By.xpath(payrollPageObjects.processPeriod)).click();
				String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
				if(reportStatus.equalsIgnoreCase("completed")==false) {
					payrollQueuePageObjects.getReportStatusMessages(driver);
				}
				driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
				isProcessPeriodFunctionValidated=true;
			}catch(Exception e) {
				System.out.println("Failed: Process function is not working as expected");
				commonMethods.takeScreenShot(driver, "Validaing Process function");
			}
		if(isProcessPeriodFunctionValidated==true)		{
			System.out.println("Passed: Validated Process Period function from payroll level");
		}else {
			System.out.println("Info: Didn't validate Process Period function from payroll level, as Locked employees=0");
		}
		
		//This try-catch loop will Confirm payroll and validates Confirm period function, return isConfirmPeriodFunctionValidated=true
			try{
				payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 6).click();
				driver.findElement(By.xpath(payrollPageObjects.confirmPeriod)).click();
				String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
				if(reportStatus.equalsIgnoreCase("completed")==false) {
					payrollQueuePageObjects.getReportStatusMessages(driver);
				}
				driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
				isConfirmPeriodFunctionValidated=true;
			}catch(Exception e) {
				System.out.println("Failed: Confirm function is not working as expected");
				commonMethods.takeScreenShot(driver, "Validaing Confirm function");
			}
		if(isConfirmPeriodFunctionValidated==true)		{
			System.out.println("Passed: Validated Confirm Period function from payroll level");
		}else {
			System.out.println("Info: Didn't validate Confirm Period function from payroll level, as Proceeded employees=0");
		}	
			
	}
	
	@Test
	//This is verify Unconfirm Function from Employee level
	public void unconfirmEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);	
		driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
		employeeDetailsPageObjects.isCurrentAssignmentExists(driver);
		
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
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
					System.out.println("Failed: Unconfirm function from employee level not available, as Employee is not in Confirmed Status");
					commonMethods.takeScreenShot(driver, "Unconfirm fuction from employee level not available");
					Assert.fail("Failed: Unconfirm function from employee level not available, as Employee is not in Confirmed Status");
				}
	}
	
	@Test
	//This is verify Reset Function from Employee level
	public void resetEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
		employeeDetailsPageObjects.isCurrentAssignmentExists(driver);		
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
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
					System.out.println("Failed: Reset function from employee level not available, as Employee is in Lock Status");
					commonMethods.takeScreenShot(driver, "Reset fuction from employee level not available");
					Assert.fail("Failed: Reset function from employee level not available, as Employee is in Lock Status");
				}	
	}
	
	@Test
	//This is verify Lock Function from Employee level
	public void lockEmployeeLevel() throws AWTException, InterruptedException, IOException {
	
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
		employeeDetailsPageObjects.isCurrentAssignmentExists(driver);
				
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
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButtonClose)).click();
					System.out.println("Failed: Lock function from employee level not available, as Employee is in Reset Status");
					commonMethods.takeScreenShot(driver, "Lock fuction from employee level not available");
					Assert.fail("Failed: Lock function from employee level not available, as Employee is in Reset Status");
				}
	}
	
	@Test
	//This is verify unconfirm period Function from payroll level
	public void unconfirmPeriodPayrollLevel() throws AWTException, InterruptedException, IOException {
		
		String payrollName=testInputGPMS.payrollName;
				
		//Navigate to required Payroll
		payrollSearchPageObjects.isPayrollExists(driver, payrollName);
		payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				
		//Capture details of payroll landing page into a table and get rowNumber, taxYear and periodNo of active payroll
		String[][] table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		int rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)-1;
		String taxYear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][2];
		String periodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][3];
		ArrayList<String> rowData=payrollPageObjects.getDisplayedDetailsOfRequiredPayrollPeriodRow(driver, rowNumOfPayPeriod);
		String periodName=rowData.get(1);
		Boolean isUnconfirmPeriodFunctionValidated=false;
		
		try{
			payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 7).click();
			driver.findElement(By.xpath(payrollPageObjects.unconfirmPeriod)).click();
			driver.switchTo().alert().accept();
			String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
			if(reportStatus.equalsIgnoreCase("completed")==false) {
				payrollQueuePageObjects.getReportStatusMessages(driver);
			}
			driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
			isUnconfirmPeriodFunctionValidated=true;
		}catch(Exception e) {
			System.out.println("Failed: Unconfirm function is not working as expected");
			commonMethods.takeScreenShot(driver, "Validaing Unconfirm function");
		}
		if(isUnconfirmPeriodFunctionValidated==true)	{
			System.out.println("Passed: Validated Unconfirm Period function from payroll level");
		}else {
			System.out.println("Info: Didn't validate Unconfirm Period function from payroll level, as Confirmed employees=0");
		}
	}
	
	@Test
	//This is verify Reset period Function from payroll level
	public void resetPeriodPayrollLevel() throws AWTException, InterruptedException, IOException {
		String payrollName=testInputGPMS.payrollName;
		
		//Navigate to required Payroll
		payrollSearchPageObjects.isPayrollExists(driver, payrollName);
		payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				
		//Capture details of payroll landing page into a table and get rowNumber, taxYear and periodNo of active payroll
		String[][] table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		int rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)-1;
		String taxYear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][2];
		String periodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][3];
		ArrayList<String> rowData=payrollPageObjects.getDisplayedDetailsOfRequiredPayrollPeriodRow(driver, rowNumOfPayPeriod);
		String periodName=rowData.get(1);
		Boolean isResetPeriodFunctionValidated=false;
		
		try{
			payrollPageObjects.getRequiredWebElement(driver, rowNumOfPayPeriod, 6).click();
			driver.findElement(By.xpath(payrollPageObjects.resetPeriod)).click();
			String reportStatus=payrollQueuePageObjects.refreshUntillComplete(driver);
			if(reportStatus.equalsIgnoreCase("completed")==false) {
				payrollQueuePageObjects.getReportStatusMessages(driver);
			}
			driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
			isResetPeriodFunctionValidated=true;
		}catch(Exception e) {
			System.out.println("Failed: Reset function is not working as expected");
			commonMethods.takeScreenShot(driver, "Validaing Reset function");
		}
		
		if(isResetPeriodFunctionValidated==true)	{
			System.out.println("Passed: Validated Reset Period function from payroll level");
		}
		
	}
		

}
