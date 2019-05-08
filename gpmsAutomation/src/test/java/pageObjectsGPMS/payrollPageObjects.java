package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class payrollPageObjects {

	
	public static final String payrollPageHeader="//*[@id='iipHeader_title']";
	
	public static final String payrollDetails="//*[@id='tblDetails']";
		public static final String payrollPeriodDetails="//*[@id='dgPeriodData']";
		public static final String payrollName="//*[@id='lblPayrollNameValue']";
		public static final String ruleset="//*[@id='lblRulesetValue']";
		public static final String currency="//*[@id='iipHeader_pageDataText3']";
		public static final String companyName="//*[@id='lblCompanyNameValue']";
		public static final String payFrequency="//*[@id='lblPayFrequencyValue']";
		public static final String payrollMode="//*[@id='lblPayrollModeValue']";
	
	public static final String payrollActions="//a[@id='btnActions_button']";
	public static final String payrollActionsList="//table[@id='payrollActionsDialog_contentTable']//a";
		public static final String deletePayroll="//*[(text()='Delete')]";
		public static final String editProperties="//*[(text()='Edit Properties')]";
		public static final String editPeriods="//*[(text()='Edit Periods')]";
		public static final String permissions="//*[(text()='Permissions')]";
		public static final String selfServicePayslip="//*[(text()='Self-Service Payslip Visibility')]";
		
		public static final String processingQueue="//*[(text()='Processing Queue')]";
		public static final String changeModeToInactive="//*[(text()='Change Mode to Inactive')]";
		public static final String changeModeToNonStandard="//*[(text()='Change Mode to Non-Standard')]";
		public static final String changeModeToObsolete="//*[(text()='Change Mode to Obsolete')]";
		
		public static final String exchangeRates="//*[(text()='Exchange Rates')]";
		public static final String reportPackages="//*[(text()='Report Packages')]";
		public static final String ersContributions="//*[(text()='Ers Contributions')]";
		public static final String companyBankAccount="//*[(text()='Company Bank Account')]";
		public static final String payrollReportLogo="//*[(text()='Payroll Report Logo')]";
		public static final String configurableChangesDataExport="//*[(text()='Configurable Changes Data Export')]";
		public static final String paySummaryNarrativeTranslations="//*[(text()='Pay Summary Narrative Translations')]";
		public static final String payslipOptions="//*[(text()='Payslip Options')]";
		
		public static final String payrollActionsClose="//*[contains(@id,'payrollActionsDialog_close')]";
	
	//Reports links after clicking period Actions 'Arrow' button
		public static final String reportsAccrualAIP="//*[contains(text(),'Accrual (AIP)')]";
		public static final String reportsAccrualESPP="//*[contains(text(),'Accrual (ESPP)')]";
		public static final String reportsChangesExport="//*[contains(text(),'Changes Export Report')]";
		public static final String reportsConfigChangeDataExport="//*[contains(text(),'Config Change Data Export Report')]";
		public static final String reportsConfigEBMultiPeriod="//*[contains(text(),'Config EB Multi Period')]";
		public static final String reportsConfigEBSinglePeriod="//*[contains(text(),'Config EB Single Period')]";
		public static final String reportsConfigurablePaymentSinglePeriod="//*[contains(text(),'Configurable Payment Report (single period)')]";
		public static final String reportsElementBreakdownMultiPeriod="//*[contains(text(),'Element Breakdown Report (multi-period)')]";
		public static final String reportsElementBreakdownSinglePeriod="//*[contains(text(),'Element Breakdown Report (single-period)')]";
		public static final String reportsEmployeeDataUploadTemplate="//*[contains(text(),'Employee Data Upload Template')]";
		public static final String reportsETypePercentageChangeMultiPeriod="//*[contains(text(),'EType Percentage Change Report (multi-period)')]";
		public static final String reportsGLCodingMultiPeriod="//*[contains(text(),'GL Coding Report (multi-period)')]";
		public static final String reportsGLCodingSinglePeriod="//*[contains(text(),'GL Coding Report (single-period)')]";
		public static final String reportsGTNChanges="//*[contains(text(),'GTN Changes Report')]";
		public static final String reportsPayStatement="//*[contains(text(),'Pay Statement Report')]";
		public static final String reportsProcessChecking="//*[contains(text(),'Process Checking Report')]";
		public static final String reportsSingleEmployeePayStatement="//*[contains(text(),'Single Employee Pay Statement Report')]";
		public static final String reportsUnitSummary="//*[contains(text(),'Unit Summary Report')]";
		public static final String reportsYTDBreakdown="//*[contains(text(),'YTD Breakdown Report')]";
	
		public static final String uploadsEmployeeUpload="//*[contains(text(),'Employee Upload')]";
		public static final String uploadsEEUploadWithReformatter="//*[contains(text(),'EE Upload with Reformatter')]";
	
	//Payroll table objects
		public static final String previousButton="//*[contains(text(),'[< Previous]')]";
		public static final String nextButton="//*[contains(text(),'[Next >]')]";
		
		public static final String lockPeriod="//*[(text()='Lock Period')]";
		public static final String processPeriod="//*[(text()='Process Period')]";
		public static final String resetPeriod="//*[(text()='Reset Period')]";
		public static final String confirmPeriod="//*[(text()='Confirm Period')]";
		public static final String unconfirmPeriod="//*[(text()='Unconfirm Period')]";
		
		public static final String viewEmployeesPayPeriodState="//*[(contains(@id,'ViewByState')) and (text()='View Employees')]";
		public static final String viewEmployeesStarters="//*[(contains(@id,'Starters')) and (text()='View Employees')]";
		public static final String viewEmployeesLeavers="//*[(contains(@id,'Leavers')) and (text()='View Employees')]";
		public static final String viewEmployeesAfterleavers="//*[(contains(@id,'ALP')) and (text()='View Employees')]";
		public static final String viewEmployeesHistoricleavers="//*[(contains(@id,'LBT')) and (text()='View Employees')]";
		
	
	//This will capture All Payroll Details Into Table
	public static String[][] captureAllPayrollDetailsIntoTable(WebDriver driver) throws AWTException, InterruptedException, IOException {
		List<WebElement> columns=driver.findElements(By.xpath(payrollPeriodDetails+"/tbody/tr"));
		List<WebElement> rows=driver.findElements(By.xpath(payrollPeriodDetails+"/tbody/tr[1]/td"));
		
		String[][] table = new String[columns.size()][rows.size()];
		Thread.sleep(1000);
		for (int i=1; i <= columns.size(); i++) {
			for (int j=1; j <= rows.size(); j++) {
				table[i-1][j-1] = driver.findElement(By.xpath(payrollPeriodDetails+"/tbody/tr["+i+"]/td["+j+"]")).getText();
			}
		}
		return table;
	}
	
	//This will capture All Payroll WebElements Into Table
	public static WebElement[][] captureAllPayrollWebelementsIntoTable(WebDriver driver) throws AWTException, InterruptedException, IOException {	
		List<WebElement> columns=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr"));
		List<WebElement> rows=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr[1]/td"));
		
		WebElement[][] element = new WebElement[columns.size()][rows.size()];
		
		for (int i=1; i < columns.size(); i++) {
			for (int j=1; j < rows.size(); j++) {
				element[i-1][j-1] = driver.findElement(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr["+i+"]/td["+j+"]"));
			}
		}	
		
		return element;
		
	}
	
	//This is check if the required pay period exists in page and returns that row number if true, row number will be 0 if pay period don't exists on this particular page
	public static Boolean isRequiredPeriodPresentOnPayrollPage(WebDriver driver, String payrollName, String taxYear, String periodNo) throws IOException, InterruptedException, AWTException {	 
		String[][] table = null;
		Boolean isRequiredPeriodPresentOnPayrollPage=false;
			
			ArrayList<String> periodNos = new ArrayList<String>();
			ArrayList<String> taxYears = new ArrayList<String>();
			
			table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);			//Will capture all data into a table[0][0]-table[13][13], including first row as header
			int noOfPeriods=table.length;													//Will get the length of table> (no.of period+1)
			
			for (int i=0; i<noOfPeriods; i++) {
				periodNos.add(table[i][3]);													//Will get all period number displayed in same order to array periodNos, with first object as header
				taxYears.add(table[i][2]);													//Will get all tax years displayed in same order to array taxYears, with first object as header
			  }
			
			for (int i=1; i<table.length; i++) {										//this for loop will checks the priodNo and tax years, when matched will capture the row number, that row number can be used in table[i][<required column>]
				
				if(periodNos.get(i).equals(periodNo)) {
					if(taxYears.get(i).equals(taxYear)) {
						isRequiredPeriodPresentOnPayrollPage=true;
						break;
					}
				}
			  }
			return isRequiredPeriodPresentOnPayrollPage;
		}
		
	//This method get the row number based on given taxYear and periodNo
	//rowNumber will remin 0, if matching taxYear and periodNo dont exists on the opened payroll page
	public static int getRowNumber(WebDriver driver, String taxYear, String periodNo) throws AWTException, InterruptedException, IOException{
		//Assuming payroll page opened
		String[][] table = null;
		int rowNumOfRequiredPeriod=0;
		ArrayList<String> periodNos = new ArrayList<String>();
		ArrayList<String> taxYears = new ArrayList<String>();
		table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		int noOfPeriods=table.length;													//Will get the length of table> (no.of period+1)
		
		for (int i=0; i<noOfPeriods; i++) {
			periodNos.add(table[i][3]);													//Will get all period number displayed in same order to array periodNos, with first object as header
			taxYears.add(table[i][2]);													//Will get all tax years displayed in same order to array taxYears, with first object as header
		  }
		
		for (int i=1; i<table.length; i++) {										//this for loop will checks the priodNo and tax years, when matched will capture the row number, that row number can be used in table[i][<required column>]
			
			if(periodNos.get(i).equals(periodNo)) {
				if(taxYears.get(i).equals(taxYear)) {
					rowNumOfRequiredPeriod=i;
					break;
				}
			}
		  }
		return rowNumOfRequiredPeriod;
	}
	
	//This to move Next and Previous payroll pages to get the row number of required taxYear and periodNo
	public static int getRequiredPeriodPayrollPageRowNumber(WebDriver driver, String payrollName, String taxYear, String periodNo) throws IOException, InterruptedException, AWTException {
		//This is click one time on Next button and see if the required pay period is present, 
			//or else click on previous button one time and see if the required pay period is present
			//return the row number on the page, row number=0, if pay period don't exists in one next and one previous page
		//Assuming we are on Payroll Page landing page
		
		int rowNumOfRequiredPeriod=0;
		
			if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)==true) {	
				rowNumOfRequiredPeriod=payrollPageObjects.getRowNumber(driver, taxYear, periodNo);
			}else {
				if(commonMethods.isClickable(driver, driver.findElement(By.xpath(payrollPageObjects.previousButton)))==true){
						driver.findElement(By.xpath(payrollPageObjects.previousButton)).click();
						if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)==true) {	
							rowNumOfRequiredPeriod=payrollPageObjects.getRowNumber(driver, taxYear, periodNo);
							}
				}
				if(rowNumOfRequiredPeriod==0) {
					if(commonMethods.isClickable(driver, driver.findElement(By.xpath(payrollPageObjects.nextButton)))==true){
						driver.findElement(By.xpath(payrollPageObjects.nextButton)).click();
						if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)==true) {	
							rowNumOfRequiredPeriod=payrollPageObjects.getRowNumber(driver, taxYear, periodNo);
							}
						if(rowNumOfRequiredPeriod==0) {
							if(commonMethods.isClickable(driver, driver.findElement(By.xpath(payrollPageObjects.nextButton)))==true){
								driver.findElement(By.xpath(payrollPageObjects.nextButton)).click();
								if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)==true) {	
									rowNumOfRequiredPeriod=payrollPageObjects.getRowNumber(driver, taxYear, periodNo);
									}
							}
						}
					}
				}
		} return rowNumOfRequiredPeriod;
	}
	
	//This will capture current active pay period row number on the payroll landing page 
	public static int getCurrentActivePayPeriodRowNumber(WebDriver driver, String payrollName) throws IOException, InterruptedException, AWTException {
		 
		String[][] table = null;
		int rowNumOfActivePayPeriod=0;
		ArrayList<Integer> dataInRow=new ArrayList<Integer>();
			
			table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);			//Will capture all data into a table[0][0]-table[13][13], including first row as header
			
			for(int i=table.length-1; i>1; i--) {
				if(!(Integer.parseInt(table[i][4])==0 && Integer.parseInt(table[i][5])==0 && Integer.parseInt(table[i][6])==0 && Integer.parseInt(table[i][7])==0))
					{	rowNumOfActivePayPeriod=i;
						break;
					}
			}
			
			
			//rowNumOfActivePayPeriod=table.length-2;
			return rowNumOfActivePayPeriod;		
	}

	
	//This method will return complete details of Pay Period row for matching taxYear and periodNo
	//This also navigates to Next and Previous payroll pages to find that row
	public static ArrayList<String> getDisplayedDetailsOfRequiredPayrollPeriodRow(WebDriver driver, int rowNumOfPayPeriod) throws IOException, InterruptedException, AWTException {
		//Assuming we are on Landing Page of that Payroll
		//Below method is also same, but returns the WebElements of same row
		
		String[][] table=null;
		ArrayList<String> dataInRow=new ArrayList<String>();
		
		table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		
		for(int j=0; j<table[0].length; j++) {
			dataInRow.add(table[rowNumOfPayPeriod][j]);
			}
		return dataInRow;
		
	}
	
	
	//This method will return complete List of WebElements of Pay Period row for matching taxYear and periodNo
	//This also navigates to Next and Previous payroll pages to find that row
	public static ArrayList<WebElement> getWebElementsOfRequiredPayrollPeriodRow(WebDriver driver, int rowNumOfPayPeriod) throws IOException, InterruptedException, AWTException {
		//Assuming we are on Landing Page of that Payroll
		//Above method is also same, but returns the details/values of same row as Strings
		
		WebElement[][] element=null;
		ArrayList<WebElement> webElementsInRow=new ArrayList<WebElement>();
			
		element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
				
		for(int j=0; j<element[0].length; j++) {
			webElementsInRow.add(element[rowNumOfPayPeriod][j]);
		}
		return webElementsInRow;
	}
	
	//This method will add period for given payroll name and details given in testInputFile
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
			commonMethods.takeScreenShot(driver, "Failed Payroll periods of given Payroll Year already exits");
			Assert.fail("Failed: Payroll periods of given Payroll Year already exits");
			}
		else if(driver.findElements(By.xpath(editPeriodsPageObjects.generate)).size()==0){
			System.out.println("Failed: Given Payroll Year dont exits in drop down list");
			commonMethods.takeScreenShot(driver, "Failed Given Payroll Year dont exits in drop down list");
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

	//This method will return specific WebElement of based on rowNumber and columnNumber
	public static WebElement getRequiredWebElement(WebDriver driver, int rowNumber, int columnNumber) throws IOException, InterruptedException, AWTException {
			//Assuming we are on Landing Page of that Payroll
			
			WebElement[][] element=null;	
			element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
			
			return element[rowNumber][columnNumber];
		}
	
	//This method will return specific WebElement of based on rowNumber and columnNumber
	public static String getRequiredData(WebDriver driver, int rowNumber, int columnNumber) throws IOException, InterruptedException, AWTException {
			//Assuming we are on Landing Page of that Payroll
				
			String[][] table=null;
			table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
				
			return table[rowNumber][columnNumber];
			}

}
