package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.commonPageObjects;
import pageObjectsGPMS.menuBarLinks;
import pageObjectsGPMS.payrollPageObjects;
import pageObjectsGPMS.payrollSearchPageObjects;
import pageObjectsGPMS.reportsPageObjects;
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class downloadReports extends basicDetails{

	
	@Test
	//This is to download Employee Data Upload Template Function from Employee level
	public void downloadEmployeeDataUploadTemplate() throws AWTException, InterruptedException, IOException {
		
		String payrollName=null;
		String payPeriodTaxyear=null;
		String payPeriodNo=null;
		String[][] table=null;
		WebElement[][] element=null;
		int rowNumOfPayPeriod=0;
		
		//Getting Payroll Name = required or active payroll
			if(testInputGPMS.requiredPayrollName!=null) {	payrollName=testInputGPMS.requiredPayrollName;	}
				else {	payrollName=testInputGPMS.payrollName; }
			
		//Opening that Payroll
		payrollSearchPageObjects.isPayrollExists(driver, payrollName);
			payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
				rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName);
				payPeriodTaxyear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName)][2];
				payPeriodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName)][3];
			
				
		//Navigate to required Pay period (required or active payroll) page, and getting Row Number in that page
			if(testInputGPMS.requiredPayPeriodTaxYear!=null) {	payPeriodTaxyear=testInputGPMS.requiredPayPeriodTaxYear;	}
			if(testInputGPMS.requiredPayPeriodNo!=null) {	payPeriodNo=testInputGPMS.requiredPayPeriodNo;	}	
			
			if(payrollPageObjects.getRequiredPeriodPayrollPageRowNumber(driver, payrollName, payPeriodTaxyear, payPeriodNo)!=0) {
				rowNumOfPayPeriod=payrollPageObjects.getRequiredPeriodPayrollPageRowNumber(driver, payrollName, payPeriodTaxyear, payPeriodNo);
			}else {
				System.out.println("Failed: Pay Period looking for, Tax Year: '"+payPeriodTaxyear+"' and Period Num: '"+payPeriodNo+"' in Payroll: '"+payrollName+"' don't exists");
				commonMethods.takeScreenShot(driver, "Failed Pay Period looking for, Tax Year '"+payPeriodTaxyear+"' and Period Num '"+payPeriodNo+"' in Payroll '"+payrollName+"' don't exists");
				Assert.fail("Failed: Pay Period looking for, Tax Year: '"+payPeriodTaxyear+"' and Period Num: '"+payPeriodNo+"' in Payroll: '"+payrollName+"' don't exists");
			}
		
		//At this point, 	rowNumOfPayPeriod = required period row num, table=all data in that payroll page where required pay period is present, payroll page is navigated to view required period
			System.out.println("Active/Required period: "+rowNumOfPayPeriod);
			table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
			element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
		
		
		element[rowNumOfPayPeriod][0].click();
		driver.findElement(By.xpath(payrollPageObjects.reportsEmployeeDataUploadTemplate)).click();
		driver.findElement(By.xpath(commonPageObjects.submitButton)).click();
		reportsPageObjects.reportsInboxRefreshUntillComplete(driver);
		reportsPageObjects.reportsInboxReportDownload(driver);
			
	}
}
