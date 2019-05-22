package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import gpmsReports.addingDetailsInEmployeeUploadTemplate;
import pageObjectsGPMS.*;
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class downloadUploadReports extends basicDetails{

	//public static String fileName=null;
	public static final String filePath=System.getProperty("user.dir")+"\\reportsDownloaded\\";
	
	
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
				rowNumOfPayPeriod=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver);
				payPeriodTaxyear=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][2];
				payPeriodNo=table[payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver)][3];
			
				
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
			
	}
	
	@Test
	public void adddDetailsInEmployeeDataUploadTemplate() throws AWTException, InterruptedException, IOException {
		
		String fileName=reportsPageObjects.reportsInboxReportDownloadandGetReportName(driver, filePath);
		System.out.println(fileName);
		System.out.println(filePath);
		
		List<String> payments=new ArrayList<String>();
		List<String> deductions=new ArrayList<String>();
		List<String> unitPays=new ArrayList<String>();
		List<String> notionalPerm=new ArrayList<String>();
		List<String> notionalTemp=new ArrayList<String>();
		List<String> entitlementsPerm=new ArrayList<String>();
		List<String> entitlementsTemp=new ArrayList<String>();
		List<String> entitlementsUnit=new ArrayList<String>();
 		
 		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
			
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Payments_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				
				
			//Capture all payments into List<String>
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payments)).click();
				int noOfPayments=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.payDedsDropDown+"/option")).size();
				for(int j=1; j<=noOfPayments; j++) {
					payments.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all deductions into List<String>	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.deductions)).click();
				int noOfDeductions=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.payDedsDropDown+"/option")).size();
				for(int j=1; j<=noOfDeductions; j++) {
					deductions.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Unit Pays into List<String>	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPay)).click();
				int noOfUnitPay=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.unitPayDropDown+"/option")).size();
				for(int j=1; j<=noOfUnitPay; j++) {
					unitPays.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Notional Amounts Perm and Notional Amounts Temp into List<String>	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmounts)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountPermButton)).click(); Thread.sleep(1000);
				int noOfNotionalAmountsPerm=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option")).size();
				for(int j=1; j<=noOfNotionalAmountsPerm; j++) {
					notionalPerm.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountTempButton)).click();  Thread.sleep(1000);
				int noOfNotionalAmountsTemp=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option")).size();
				for(int j=1; j<=noOfNotionalAmountsTemp; j++) {
					notionalTemp.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Entitlements Temp into List<String>		
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTemp)).click();
				int noOfEntitlementsTemp=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option")).size();
				for(int j=1; j<=noOfEntitlementsTemp; j++) {
					entitlementsTemp.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Entitlements Perm into List<String>		
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPerm)).click();
				int noOfEntitlementsPerm=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option")).size();
				for(int j=1; j<=noOfEntitlementsPerm; j++) {
					entitlementsPerm.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Entitlements Unit Pay into List<String>		
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPay)).click();
				int noOfEntitlementsUnitPay=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option")).size();
				for(int j=1; j<=noOfEntitlementsUnitPay; j++) {
					entitlementsUnit.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			}
			
			Date date = new Date(); 											
			String strDateFormat = "MMMdd"; 									
			SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
			String upladKey=objSDF.format(date);
			
			File file=new File(filePath+fileName);
			FileInputStream inputStream = new FileInputStream(file);
			
			@SuppressWarnings("resource")
			XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
			
			//To get the current period value
			Sheet payrollValidaion=employeeUploadTemplate.getSheet("Payroll Validation");
			Row payrollValidationDetailsRow=payrollValidaion.getRow(1);
			Cell periodName=payrollValidationDetailsRow.getCell(2);
			String currentPeriod=periodName.getStringCellValue();
			
			//To set upload key as today's date MMMdd
			Cell uploadKey=payrollValidationDetailsRow.getCell(3);
			uploadKey.setCellValue(upladKey);
			
			ArrayList<String> sheetsNames=new ArrayList<String>();
			int noOfSheets=employeeUploadTemplate.getNumberOfSheets();
			for(int i=0; i<noOfSheets; i++) {
				sheetsNames.add(employeeUploadTemplate.getSheetName(i));
			}
			
			inputStream.close();
			FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
			employeeUploadTemplate.write(outputStream);
			outputStream.close();

			//Adding new employees
			int noOfEmployeesToAdd=2;
			ArrayList<ArrayList<String>> newEmpDetails=addingDetailsInEmployeeUploadTemplate.addingEmployeesDuplicateInMasterData(filePath, fileName, noOfEmployeesToAdd);
			ArrayList<String> newEmpNosAdded=newEmpDetails.get(0);
			
			String entitlementPermSheet="Perm Entitlements";
			String entitlementTempSheet="Temp Entitlements";
			String entitlementUnitSheet="Unit Entitlements";
			String notionalPermSheet="Perm Notional Amounts";
			String notionalTempSheet="Temp Notional Amounts";
			String paydedsPermSheet="Perm Paydeds";
			String paydedsTempSheet="Temp Paydeds";
			String unitPaysSheet="Unit Pays";
			
			for(String sheetName: sheetsNames) {	
				if(sheetName.equals(entitlementPermSheet))	{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, entitlementPermSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(entitlementTempSheet))	{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, entitlementTempSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(entitlementUnitSheet))	{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, entitlementUnitSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(notionalPermSheet))		{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, notionalPermSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(notionalTempSheet))		{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, notionalTempSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(paydedsPermSheet))		{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, paydedsPermSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(paydedsTempSheet))		{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, paydedsTempSheet, newEmpDetails, noOfEmployeesToAdd);	}
				if(sheetName.equals(unitPaysSheet))			{	addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, unitPaysSheet, newEmpDetails, noOfEmployeesToAdd);		}
			}
			
			//based on the no of employee added to mater data Sheet above, add same employees to other all entitlements, paydeds, notional and unit pay sheet
			//and also add random entitlements, paydeds, notional and unit pay values to each employee
			for(int i=0; i<newEmpNosAdded.size(); i++) {
				for(String sheetName: sheetsNames) {
					if(sheetName.equals(entitlementPermSheet))	{
						//pick two random number in size of entitlementsPerm for two different entitlements
						int randomNo1=ThreadLocalRandom.current().nextInt(3, entitlementsPerm.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsPerm.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsPerm.size());	}
						
						String entitlement=entitlementsPerm.get(randomNo1);
						System.out.println(entitlement);
						Object[] entitlementPerm1= {entitlement, currentPeriod, ThreadLocalRandom.current().nextInt(100, 150)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementPermSheet, newEmpNosAdded.get(i), entitlementPerm1);
						
						entitlement=entitlementsPerm.get(randomNo2);
						System.out.println(entitlement);
						Object[] entitlementPerm2= {entitlement, "", ThreadLocalRandom.current().nextInt(150, 200)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementPermSheet, newEmpNosAdded.get(i), entitlementPerm2);
					}
					if(sheetName.equals(entitlementTempSheet))	{
						//pick two random number in size of entitlementsTemp for two different entitlements
						int randomNo1=ThreadLocalRandom.current().nextInt(3, entitlementsTemp.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsTemp.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsTemp.size());	}
						
						String entitlement=entitlementsTemp.get(randomNo1);
						System.out.println(entitlement);
						Object[] entitlementTemp1= {entitlement, ThreadLocalRandom.current().nextInt(100, 150)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementTempSheet, newEmpNosAdded.get(i), entitlementTemp1);
						
						entitlement=entitlementsTemp.get(randomNo2);
						System.out.println(entitlement);
						Object[] entitlementTemp2= {entitlement, ThreadLocalRandom.current().nextInt(150, 200)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementTempSheet, newEmpNosAdded.get(i), entitlementTemp2);
					}
					if(sheetName.equals(entitlementUnitSheet))	{
						//pick two random number in size of entitlementsUnit for two different entitlements
						int randomNo1=ThreadLocalRandom.current().nextInt(3, entitlementsUnit.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsUnit.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, entitlementsUnit.size());	}
						
						String entitlement=entitlementsUnit.get(randomNo1);
						System.out.println(entitlement);
						Object[] entitlementUnit1= {entitlement, ThreadLocalRandom.current().nextInt(2, 6), ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementUnitSheet, newEmpNosAdded.get(i), entitlementUnit1);
						
						entitlement=entitlementsUnit.get(randomNo2);
						System.out.println(entitlement);
						Object[] entitlementUnit2= {entitlement, ThreadLocalRandom.current().nextInt(2, 6), ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, entitlementUnitSheet, newEmpNosAdded.get(i), entitlementUnit2);
					}
					if(sheetName.equals(notionalPermSheet))		{
						//pick two random number in size of notionalPerm for two different notionals
						int randomNo1=ThreadLocalRandom.current().nextInt(3, notionalPerm.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, notionalPerm.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, notionalPerm.size());	}
						
						String entitlement=notionalPerm.get(randomNo1);
						System.out.println(entitlement);
						Object[] notionalPerm1= {entitlement, currentPeriod, ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, notionalPermSheet, newEmpNosAdded.get(i), notionalPerm1);
						
						entitlement=notionalPerm.get(randomNo2);
						System.out.println(entitlement);
						Object[] notionalPerm2= {entitlement, "", ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, notionalPermSheet, newEmpNosAdded.get(i), notionalPerm2);
					}
					if(sheetName.equals(notionalTempSheet))		{
						//pick two random number in size of notionalTemp for two different notionals
						int randomNo1=ThreadLocalRandom.current().nextInt(3, notionalTemp.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, notionalTemp.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, notionalTemp.size());	}
						
						String entitlement=notionalTemp.get(randomNo1);
						System.out.println(entitlement);
						Object[] notionalTemp1= {entitlement, ThreadLocalRandom.current().nextInt(100, 150)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, notionalTempSheet, newEmpNosAdded.get(i), notionalTemp1);
						
						entitlement=notionalTemp.get(randomNo2);
						System.out.println(entitlement);
						Object[] notionalTemp2= {entitlement, ThreadLocalRandom.current().nextInt(150, 200)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, notionalTempSheet, newEmpNosAdded.get(i), notionalTemp2);
					}
					if(sheetName.equals(paydedsPermSheet))		{
						//pick two random number from Payments size and deductions size
						int randomNo1=ThreadLocalRandom.current().nextInt(3, payments.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, deductions.size());
						
						String entitlement=payments.get(randomNo1);
						System.out.println(entitlement);
						Object[] paymentsPerm= {entitlement, currentPeriod, ThreadLocalRandom.current().nextInt(100, 150)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, paydedsPermSheet, newEmpNosAdded.get(i), paymentsPerm);
						
						entitlement=deductions.get(randomNo2);
						System.out.println(entitlement);
						Object[] deductionPerm= {entitlement, currentPeriod, ThreadLocalRandom.current().nextInt(150, 200)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, paydedsPermSheet, newEmpNosAdded.get(i), deductionPerm);
					}
					if(sheetName.equals(paydedsTempSheet))		{
						//pick two random number from Payments size and deductions size
						int randomNo1=ThreadLocalRandom.current().nextInt(3, payments.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, deductions.size());
						
						String entitlement=payments.get(randomNo1);
						System.out.println(entitlement);
						Object[] paymentTemp= {entitlement, ThreadLocalRandom.current().nextInt(100, 150)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, paydedsTempSheet, newEmpNosAdded.get(i), paymentTemp);
						
						entitlement=deductions.get(randomNo2);
						System.out.println(entitlement);
						Object[] deductionsTemp= {entitlement, ThreadLocalRandom.current().nextInt(150, 200)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, paydedsTempSheet, newEmpNosAdded.get(i), deductionsTemp);
					}
					if(sheetName.equals(unitPaysSheet))			{
						//pick two random number in size of unitPays for two different Unit Pays
						int randomNo1=ThreadLocalRandom.current().nextInt(3, unitPays.size());
						int randomNo2=ThreadLocalRandom.current().nextInt(3, unitPays.size());
						while(randomNo1==randomNo2) { randomNo2=ThreadLocalRandom.current().nextInt(3, unitPays.size());	}
						
						String entitlement=unitPays.get(randomNo1);
						System.out.println(entitlement);
						Object[] unitPays1= {entitlement, ThreadLocalRandom.current().nextInt(2, 6), ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, unitPaysSheet, newEmpNosAdded.get(i), unitPays1);
						
						entitlement=unitPays.get(randomNo2);
						System.out.println(entitlement);
						Object[] unitPays2= {entitlement, ThreadLocalRandom.current().nextInt(2, 6), ThreadLocalRandom.current().nextInt(5, 12)};
						addingDetailsInEmployeeUploadTemplate.addingETypes(filePath, fileName, unitPaysSheet, newEmpNosAdded.get(i), unitPays2);
						
					}
					
				}
				
			}
			
			
			String payrollName=testInputGPMS.payrollName;
		 	
	/*	 	file=new File(filePath+fileName);
			inputStream = new FileInputStream(file);
			
			employeeUploadTemplate = new XSSFWorkbook(inputStream);
			
			Date date = new Date(); 											
			String strDateFormat = "MMMdd"; 									
			SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
			String upladKey=objSDF.format(date);
			
			Sheet payrollValidaion=employeeUploadTemplate.getSheet("Payroll Validation");
			Row payrollValidationDetailsRow=payrollValidaion.getRow(1);
			Cell uploadKey=payrollValidationDetailsRow.getCell(3);
			uploadKey.setCellValue(upladKey);
			
			inputStream.close();
			FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
			employeeUploadTemplate.write(outputStream);
			outputStream.close();
	*/	 	
			payrollSearchPageObjects.isPayrollExists(driver, payrollName);
			payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
			int activePeriodRowNumber=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver);
			System.out.println(activePeriodRowNumber);
			List<WebElement> activeRowElements=payrollPageObjects.getWebElementsOfRequiredPayrollPeriodRow(driver, activePeriodRowNumber);
			activeRowElements.get(0).click();
			driver.findElement(By.xpath(payrollPageObjects.uploadsEmployeeUpload)).click();
			driver.findElement(By.xpath(uploadFilesPageObjects.payrollFileBrowseTextInput)).sendKeys(filePath+fileName);
			
			Thread.sleep(1000);
			driver.findElement(By.xpath(uploadFilesPageObjects.payrollFileUploadFromLocalFile)).click();
	   	 	String status=reportsPageObjects.reportsInboxRefreshUntillComplete(driver);	
	   	 	Boolean isStatusComplete=reportsPageObjects.isReportStatusComplete(driver);
	   	 	if(isStatusComplete==false) {
	   	 		String errorMessage=reportsPageObjects.reportsInboxErrorMessages(driver);
	   	 		System.out.println("Upload Failed, status message: "+status+", Message Summary: "+errorMessage);	
	   	 	}else {
	   	 		System.out.println("Upload successful, status message: "+status);
	   	 	}
	
	}

/*	@Test
	public void uploadEmployeeDataUploadTemplate() throws AWTException, InterruptedException, IOException {
		
		String payrollName=testInputGPMS.payrollName;
	 	
	 	File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		
		Date date = new Date(); 											
		String strDateFormat = "MMMdd"; 									
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		String upladKey=objSDF.format(date);
		
		Sheet payrollValidaion=employeeUploadTemplate.getSheet("Payroll Validation");
		Row payrollValidationDetailsRow=payrollValidaion.getRow(1);
		Cell uploadKey=payrollValidationDetailsRow.getCell(3);
		uploadKey.setCellValue(upladKey);
		
		inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		outputStream.close();
	 	
		payrollSearchPageObjects.isPayrollExists(driver, payrollName);
		payrollSearchPageObjects.goToRequiredPayrollPage(driver, payrollName);
		int activePeriodRowNumber=payrollPageObjects.getCurrentActivePayPeriodRowNumber(driver);
		System.out.println(activePeriodRowNumber);
		List<WebElement> activeRowElements=payrollPageObjects.getWebElementsOfRequiredPayrollPeriodRow(driver, activePeriodRowNumber);
		activeRowElements.get(0).click();
		driver.findElement(By.xpath(payrollPageObjects.uploadsEmployeeUpload)).click();
		driver.findElement(By.xpath(uploadFilesPageObjects.payrollFileBrowseTextInput)).sendKeys(filePath+fileName);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(uploadFilesPageObjects.payrollFileUploadFromLocalFile)).click();
   	 	String status=reportsPageObjects.reportsInboxRefreshUntillComplete(driver);	
   	 	Boolean isStatusComplete=reportsPageObjects.isReportStatusComplete(driver);
   	 	if(isStatusComplete==false) {
   	 		String errorMessage=reportsPageObjects.reportsInboxErrorMessages(driver);
   	 		System.out.println("Upload Failed, status message: "+status+", Message Summary: "+errorMessage);	
   	 	}else {
   	 		System.out.println("Upload successful, status message: "+status);
   	 	}
	}
*/
}