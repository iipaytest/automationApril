package gpmsUI.gpmsAutomation;

import static org.testng.Assert.assertThrows;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class mainTestClassGPMS {
	
	static WebDriver driver;
	
	@SuppressWarnings("null")
	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//commonMethods.takeScreenShot(driver, "GPMS Version Verified");
		//String url=testInputGPMS.urlTST3redirector;
		
		
		driver.get(testInputGPMS.urlTST3redirector);
		if(driver.findElements(By.cssSelector("input#ClientId")).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "GPMS Version Incorrect");
			Assert.fail("Failed: Login Page Not working");
		}
		driver.findElement(By.cssSelector("input#ClientId")).clear();
		driver.findElement(By.cssSelector("input#ClientId")).sendKeys(testInputGPMS.clientID);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		if(driver.findElements(By.cssSelector("input#Username")).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "GPMS Version Incorrect");
			Assert.fail("Failed: Login Page Not working");
		}
		
		
		driver.findElement(By.cssSelector("input#Username")).sendKeys(testInputGPMS.userName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(testInputGPMS.password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		String elementType="Address To Send Payslip";
		String input="2DD";
		String startDate=null;
		String endDate=null;

		//Navigates to employee details page > edit payroll details page
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.employeePayrollDetails)).click();
		
		//selects required element type from edit payroll details drop down
		commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.dropDownEmployeePayrollDetails, elementType);
		
		//checks if we are on element type page, or there is a step between for selecting element type (exp: Entitlement (Unit Pay Perm)), navigate to required page by selecting random element type
		if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.save)).size()==0) {
			commonMethods.selectRandomFromList(driver, "//*[contains(@class, 'crimsonBorder')]");
			Thread.sleep(1000);
		}
		
		//Captures the payrollConstraints for concurrent/continuous details
		String payrollConstraints=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payrollDetailsConstraints)).getText();
		System.out.println(payrollConstraints);
		//Checks for details button, if exists will click on it
		if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).size()!=0) {
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).click();
		}
		
		//Will get the Start and End dates based on payrollConstraints and already details of existing element type 
		if(payrollConstraints=="Non-Concurrent,  Continuous") {
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
				Select startDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[2]//select")));
				
				Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
				endDatePrevious.selectByIndex(2);
				
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(endDatePrevious.getFirstSelectedOption().getText());
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
				
				startDate=startDateCurrent1.getFirstSelectedOption().getText();
				endDate="indefinite";
			}else {

				startDate=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select/option[1]")).getText();
				endDate="indefinite";
			}	
		}
		if(payrollConstraints.contains("Concurrent,  Continuous")) {
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
				Select startDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[2]//select")));
				
				Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
				endDatePrevious.selectByVisibleText(startDatePrevious.getFirstSelectedOption().getText());
				
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(startDatePrevious.getFirstSelectedOption().getText());
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
				
				startDate=startDateCurrent1.getFirstSelectedOption().getText();
				endDate="indefinite";
			}else {

				startDate=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select/option[1]")).getText();
				endDate="indefinite";
			}	
		}
		
		
		
		
/*		if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
			
			
			if(payrollConstraints.contains("Non-Concurrent") && payrollConstraints.contains("Non-Continuous")) {
				
			}
			
			if(payrollConstraints.contains("Non-Concurrent") && payrollConstraints.contains("Continuous")) {
				Select endDate=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
				endDate.selectByIndex(1);
				
				List<WebElement> mandatoryFields=driver.findElements(By.xpath("//*[contains(@class, 'crimsonBorder')]"));
				int i=mandatoryFields.size();
				int j=0;
				int x=1;
				//Iterator<WebElement> itr=mandatoryFields.iterator();
				for(WebElement we:mandatoryFields) {
					
					if(we.getTagName().contains("input")) {	
						if(we.getAttribute("id").contains("txtDate"))	{
							we.clear();
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							Date date=new Date();
							String todaysDate=dateFormat.format(date);
							we.sendKeys(todaysDate); 
						}else if(we.getAttribute("onkeypress").contains("EnsureDecimalNumeric(event, true)")) {
							we.clear();
							String number=String.valueOf(ThreadLocalRandom.current().nextInt(1, 10));
							we.sendKeys(number); 
						}else {
							we.clear();
							String randon="test";
							we.sendKeys(randon); 
						}
					}
					if(we.getTagName().contains("select")) {	
						Select period1=new Select(we);
						if (x==1) 	period1.selectByIndex(2);
						else 	period1.selectByIndex(1);		
						x++;						}
					j++; if(j>=i) {break;}
					}	
			}
			
			if(payrollConstraints.contains("Concurrent") && payrollConstraints.contains("Non-Continuous")) {
				
			}
			
			if(payrollConstraints.contains("Concurrent") && payrollConstraints.contains("Continuous")) {
				
			}
		}else {
		if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).size()!=0) {
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).click();
		}
		
*/		List<WebElement> mandatoryFields=driver.findElements(By.xpath("//*[contains(@class, 'crimsonBorder')]"));
		int i=mandatoryFields.size();
		int j=0;
		Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
		startDateCurrent1.selectByVisibleText(startDate);
		
		Select endDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[3]//select")));
		endDateCurrent1.selectByVisibleText(endDate);
		
		for(WebElement we:mandatoryFields) {
			if(j<=1) {}
			else {	if(we.getTagName().contains("input")) {	
					if(we.getAttribute("id").contains("txtDate"))	{
						we.clear();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date=new Date();
						String todaysDate=dateFormat.format(date);
						we.sendKeys(todaysDate); 
					}else if(we.getAttribute("onkeypress").contains("EnsureDecimalNumeric(event, true)")) {
						we.clear();
						String number=String.valueOf(ThreadLocalRandom.current().nextInt(1, 10));
						we.sendKeys(number); 
					}else {
						we.clear();
						String randon="test";
						we.sendKeys(randon); 
					}
				}
				if(we.getTagName().contains("select")) {	
					Select period=new Select(we); 
					period.selectByIndex(1);
					}
			}
			
			j++; if(j>=i) {break;}
			}

		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
		
		
	}
}
		
/*		if(stateOfPayroll[0]!=0) {
			awaitingProcess.click();
			driver.findElement(By.xpath(payrollPageObjects.lockPeriod)).click();
			String status=payrollQueuePageObjects.reportsInboxRefreshUntillComplete(driver);
			if(status.equalsIgnoreCase("Complete")) {	
				System.out.println("Passed: Lock Period function from Payroll level is successful for '"+payrollName+"' payroll for period "+periodName);
				}
				else { 
					System.out.println("Failed: Lock Period function from Payroll level for '"+payrollName+"' payroll for period "+periodName);	
					driver.findElement(By.xpath(payrollQueuePageObjects.runtimeDetailsSummary)).click();
					commonMethods.takeScreenShotOfElement(driver, "Payroll_pay period lock function failed", driver.findElement(By.xpath(payrollQueuePageObjects.summaryWindowFrame)));
					driver.findElement(By.xpath(payrollQueuePageObjects.summaryWindowClose)).click();
				}
			driver.findElement(By.xpath(payrollQueuePageObjects.backToPayroll)).click();
		}else {
			System.out.println("Info: Cant verify Lock Period function from Payroll level as there are no Awaiting employees exists in '"+payrollName+"' payroll for period "+periodName);
		}
		
		
		System.out.println("State of Employees in payroll: \n	Awaiting: "+stateOfPayroll[0]+"\n	Locked: "+stateOfPayroll[1]+"\n	Processed: "+stateOfPayroll[2]+"\n	Confirmed: "+stateOfPayroll[3]);
	*/
	/*	
		String payrollName=null;
		String payPeriodTaxyear=null;
		String payPeriodNo=null;
		String[][] table=null;
		WebElement[][] element=null;
		Object[][] rowData0Webelements1=null;
		String[][] currentPayrollStatus=null;
		ArrayList<String> headerRow=new ArrayList<String>();
		ArrayList<String> dataInRow=new ArrayList<String>();
		ArrayList<WebElement> webElementsInRow=new ArrayList<WebElement>();
		int rowNumOfPayPeriod=0;
		
	//Getting Payroll Name = required or active payroll
		if(testInputGPMS.requiredPayrollName!=null) {	payrollName=testInputGPMS.requiredPayrollName;	}
			else {	payrollName=testInputGPMS.payrollName; }
	
	//Opening that Payroll
		if(payrollSearchPageObjects.isPayrollExists(driver, payrollName)==true) {
			payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
			table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
			rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName);
			payPeriodTaxyear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName)][2];
			payPeriodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName)][3];
		}else {
			System.out.println("Failed: Payroll: '"+payrollName+"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed Payroll '"+payrollName+"' don't exists");
			Assert.fail("Failed: Payroll: '"+payrollName+"' don't exists");
		}
		
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
	
	//At this point, 	rowNumOfPayPeriod = required period row num, table=all data in that payroll page where required pay period is present
		System.out.println("Active/Required period: "+rowNumOfPayPeriod);
		table=payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
		element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
		
		for(int j=0; j<table[0].length; j++) {
			headerRow.add(table[0][j]);
			dataInRow.add(table[rowNumOfPayPeriod][j]);
			webElementsInRow.add(element[rowNumOfPayPeriod][j]);
			}
		webElementsInRow.get(0).click();
	*/	
		
		
		
	/*	
		if(testInputGPMS.requiredPayrollName!=null) {
			payrollName=testInputGPMS.requiredPayrollName;
			payPeriodTaxyear=testInputGPMS.requiredPayPeriodTaxYear;
			payPeriodNo=testInputGPMS.requiredPayPeriodNo;
			if(payrollSearchPageObjects.isPayrollExists(driver, payrollName)==true) {
				payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				rowNumOfPayPeriod=payrollPageObjects.getRequiredPeriodPayrollPageRowNumber(driver, payrollName, payPeriodTaxyear, payPeriodNo);
			}else {
				System.out.println("Failed: Payroll: '"+payrollName+"' don't exists");
				commonMethods.takeScreenShot(driver, "Failed Payroll '"+payrollName+"' don't exists");
				Assert.fail("Failed: Payroll: '"+payrollName+"' don't exists");
			}
		
		}else {
			payrollName=testInputGPMS.payrollName;
			if(payrollSearchPageObjects.isPayrollExists(driver, payrollName)==true) {
				payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver, payrollName);
			}else {
				System.out.println("Failed: Payroll: '"+payrollName+"' don't exists");
				commonMethods.takeScreenShot(driver, "Failed Payroll '"+payrollName+"' don't exists");
				Assert.fail("Failed: Payroll: '"+payrollName+"' don't exists");
			}
		}
		
		if(rowNumOfPayPeriod!=0) {
				
			System.out.println(rowNumOfPayPeriod);
			
		}else {
				System.out.println("Failed: Pay Period looking for, Tax Year: '"+payPeriodTaxyear+"' and Period Num: '"+payPeriodNo+"' in Payroll: '"+payrollName+"' don't exists");
				commonMethods.takeScreenShot(driver, "Failed Pay Period looking for, Tax Year '"+payPeriodTaxyear+"' and Period Num '"+payPeriodNo+"' in Payroll '"+payrollName+"' don't exists");
				Assert.fail("Failed: Pay Period looking for, Tax Year: '"+payPeriodTaxyear+"' and Period Num: '"+payPeriodNo+"' in Payroll: '"+payrollName+"' don't exists");
			}
			
	*/	
		
	
		
	//---------------	
		
		
		
/*			if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)!=0) {	rowNoOfRequiredPeriod=payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo);	System.out.println(rowNoOfRequiredPeriod);}
			else {	
				
				payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
				table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
				for(int i=1; i<table.length; i++) {
						System.out.println("hi");
						periods[i-1]=Integer.parseInt(table[i][3]);
						taxYears[i-1]=Integer.parseInt(table[i][3]);
					
				}
				System.out.println(periods);
				System.out.println(taxYears);
				
				if(periods[0]<=Integer.parseInt(taxYear)) {
					System.out.println("hi1");
					if(commonMethods.isClickable(driver, driver.findElement(By.xpath(payrollPageObjects.nextButton)))==true){
						System.out.println("hi1");
						driver.findElement(By.xpath(payrollPageObjects.nextButton)).click();
						if(payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo)!=0) {	rowNoOfRequiredPeriod=payrollPageObjects.isRequiredPeriodPresentOnPayrollPage(driver, payrollName, taxYear, periodNo);	}
						else	{
							System.out.println("Failed: Tax Year '"+taxYear +"' and Period No '"+periodNo+"' dont exists in '"+ payrollName +"'s payroll don't exists");
							commonMethods.takeScreenShot(driver, "Pay Period looking for don't Exists");
							Assert.fail("Failed: Tax Year '"+taxYear +"' and Period No '"+periodNo+"' dont exists in '"+ payrollName +"'s payroll don't exists");
						}
					}else	{
						System.out.println("Failed: Tax Year '"+taxYear +"' and Period No '"+periodNo+"' dont exists in '"+ payrollName +"'s payroll don't exists");
						commonMethods.takeScreenShot(driver, "Pay Period looking for don't Exists");
						Assert.fail("Failed: Tax Year '"+taxYear +"' and Period No '"+periodNo+"' dont exists in '"+ payrollName +"'s payroll don't exists");
					}
					
				}
				
			}
	*/		
			
		
	//	payrollSearchPageObjects.goToRequiredPayrollPage(driver, testInputGPMS.payrollName);
		
/*		String payrollName=testInputGPMS.payrollName;
		String taxYear="2019"; 
		String periodNo="11";
		String lookingPeriodNo=null;
		int aa = 0;
		String[][] table = null;
		WebElement[][] element=null;
		String[] rowData;
		ArrayList<String> mylist = new ArrayList<String>();
		
		
		if(payrollSearchPageObjects.isPayrollExists(driver, payrollName)) {
			
			payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
			
			table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
			element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
			
			System.out.println(table[3][3]);
			System.out.println(table[0].length);
			
			for (int i=1; i < table.length-1; i++) {
				if(element[i][3].getText().contains(periodNo) && element[i][4].getText()!=null) {
					if(element[i][2].getText().contains(taxYear)) {
						for (int j=1; j < table[0].length; j++)
							mylist.add(table[i][j]);
					}
				}
			}
			if(mylist.isEmpty()) {
				if(driver.findElement(By.xpath(payrollPageObjects.previousButton)).isEnabled()==true) {
					do {
						driver.findElement(By.xpath(payrollPageObjects.previousButton)).click();
						
						table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
						element=payrollPageObjects.captureAllPayrollWebelementsIntoTable(driver);
						
						for (int i=1; i < table.length-1; i++) {
							if(element[i][3].getText().contains(periodNo) && element[i][4].getText()!=null) {
								if(element[i][2].getText().contains(taxYear)) {
									for (int j=1; j < table[0].length; j++)
										mylist.add(table[i][j]);
									
								}
							}
						}break;
						
					}while(driver.findElement(By.xpath(payrollPageObjects.previousButton)).isEnabled());
				
				}else {System.out.println("period not found1");}
				
			}else {System.out.println("period not found2");}
			
			System.out.println(mylist);
			
			
			//while(driver.findElement(By.xpath(payrollPageObjects.previousButton)).isEnabled()) { driver.findElement(By.xpath(payrollPageObjects.previousButton)).click();	}
			
		
	}else System.out.println("Payroll dont exists");
		
		
		
		//This is to navigate of payroll page to required period and get webElements for that period row
		
		
	*/	
		
		
		/*
		 functionValidations class:	 
		 	lockPeriodPayrollLevel(), 
		 	processPeriodPayrollLevel(),	
		 	confirmPeriodPayrollLevel(), 
		 	unconfirmPeriodPayrollLevel(), 
		 	resetPeriodPayrollLevel(), 
		 	viewEmployeeFromPayrollLevel()
		 */
		
		/*
		 downloadReports class:	 
		 	downloadEmployeeDataUploadTemplateel(), 
		 	other all downloadable reports
		 */
		
 
        

		 
		
		
		
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
		
		
		
	
	



