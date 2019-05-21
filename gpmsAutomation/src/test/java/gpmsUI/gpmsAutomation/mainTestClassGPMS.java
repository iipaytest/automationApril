package gpmsUI.gpmsAutomation;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import gpmsReports.addingDetailsInEmployeeUploadTemplate;
import gpmsReports.reportsCommonMethods;
import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.*;

public class mainTestClassGPMS {
	
	static WebDriver driver;
	
	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\reportsDownloaded\\");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		//commonMethods.takeScreenShot(driver, "GPMS Version Verified");
		//String url=testInputGPMS.urlTST3redirector;
		
		
		driver.get(testInputGPMS.urlTST3redirector);
		if(driver.findElements(By.xpath(loginPageObjects.clientID)).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "Login Page not found");
		}
		
		driver.findElement(By.xpath(loginPageObjects.clientID)).clear();
		driver.findElement(By.xpath(loginPageObjects.clientID)).sendKeys(testInputGPMS.clientID);
		driver.findElement(By.xpath(loginPageObjects.submit)).click();
		
		if(driver.findElements(By.xpath(loginPageObjects.login)).size()==0) {
			System.out.println("Failed: Login Page Not working");
			commonMethods.takeScreenShot(driver, "Login Page not found");
			Assert.fail("Failed: Login Page Not working");
		}
		
		driver.findElement(By.xpath(loginPageObjects.userName)).sendKeys(testInputGPMS.userName);
		driver.findElement(By.xpath(loginPageObjects.password)).sendKeys(testInputGPMS.password);
		driver.findElement(By.xpath(loginPageObjects.login)).click();
		
		if(driver.findElements(By.xpath(menuPageObjects.search)).size()!=1) {
			System.out.println("Failed: GPMS Login failed");
			commonMethods.takeScreenShot(driver, "GPMS LogIn Failed");
			Assert.fail("Failed: GPMS LogIn Failed");
		}
		
		String forEmployeeHeader="Employee Search";
		String forPayrollHeader="Payroll Search";
		String addEmployeeHeader="Add New Employee";
		String addPayrollHeader="Add New Payroll";
		String userAdminHeader="User Selection";
		String userSecurityConfigHeader= "User Security Configuration";
		String elementTypeAdminHeader= "Element Type Selection";
		String elementTypeGroupsHeader= "Element Type Groups";
		String notionalAmountETpyeAdminHeader= "Notional Amount EType Selection";
		String userQueueAdminHeader= "User Queue";
		
		try {
/*			menuBarLinks.goToForEmployee(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(forEmployeeHeader)) {
				System.out.println("Passed: "+forEmployeeHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+forEmployeeHeader+" page");
			}
			
			menuBarLinks.goToForPayroll(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(forPayrollHeader)) {
				System.out.println("Passed: "+forPayrollHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+forPayrollHeader+" page");
			}
			
			menuBarLinks.goToAddEmployee(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(addEmployeeHeader)) {
				System.out.println("Passed: "+addEmployeeHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+addEmployeeHeader+" page");
			}
			
			menuBarLinks.goToAddPayroll(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(addPayrollHeader)) {
				System.out.println("Passed: "+addPayrollHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+addPayrollHeader+" page");
			}
			
			menuBarLinks.goToUserAdmin(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(userAdminHeader)) {
				System.out.println("Passed: "+userAdminHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+userAdminHeader+" page");
			}
			
			menuBarLinks.goToUserSecurityConfig(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(userSecurityConfigHeader)) {
				System.out.println("Passed: "+userSecurityConfigHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+userSecurityConfigHeader+" page");
			}
			
			menuBarLinks.goToElementTypeAdmin(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(elementTypeAdminHeader)) {
				System.out.println("Passed: "+elementTypeAdminHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+elementTypeAdminHeader+" page");
			}
			
			menuBarLinks.goToElementTypeGroups(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(elementTypeGroupsHeader)) {
				System.out.println("Passed: "+elementTypeGroupsHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+elementTypeGroupsHeader+" page");
			}
*/			
			menuBarLinks.goToNotionalAmountETpyeAdmin(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(notionalAmountETpyeAdminHeader)) {
				System.out.println("Passed: "+notionalAmountETpyeAdminHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+notionalAmountETpyeAdminHeader+" page");
			}
			
			menuBarLinks.goToUserQueueAdmin(driver);
			if(driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText().contentEquals(userQueueAdminHeader)) {
				System.out.println("Passed: "+userQueueAdminHeader+" page is available");
			}else {
				String header=driver.findElement(By.xpath(menuBarLinks.pageHeader)).getText();
				System.out.println("Failed: navigated to "+header+" page when tried for "+userQueueAdminHeader+" page");
			}
			
			
			
			
		
		
		}catch(Exception e) {
			throw e;
		}
		
		
		}
}

/*	 	String payrollName=testInputGPMS.payrollName;
	 	String filePath=System.getProperty("user.dir")+"\\reportsDownloaded\\";
	 	String fileName="AUTO.Employee_Data_Upload_Template_Employee_Data_Upload_Template.(20190516.14-47-38).xlsx";
	 	
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
 */ 	 	
 /*  	 	String filePath=System.getProperty("user.dir")+"\\reportsDownloaded\\";
   	 	String reportName="AUTO.Employee_Data_Upload_Template_Employee_Data_Upload_Template.(20190517.";
   	 	File dir=new File(filePath);
		File[] allDownloads=dir.listFiles();
		
		for(int i = allDownloads.length-1; i >=0 ; i--) {
			System.out.println(allDownloads[i].getName());
			if(allDownloads[i].getName().contains(reportName)) {
				reportName=allDownloads[i].getName();
				break;
			}
		}
		if(reportName.startsWith("~$")) { reportName=reportName.replace("~$", "");}
		
		System.out.println(reportName);
*/		
/*	//Download employee upload template and capture file name, 				
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
*/		
		
		
/*		
		int count=employeeUploadTemplate.getNumberOfSheets();
		sheetName=new String[count];
		for(int x=0; x<count; x++) {	
			sheetName[x]=employeeUploadTemplate.getSheetAt(x).getSheetName();
			sheets.add(employeeUploadTemplate.getSheetAt(x));
		}
		
		Row payrollValidationHeader=payrollValidation.getRow(1);
		Cell periodName=payrollValidationHeader.getCell(2);
		String currentPeriod=periodName.getStringCellValue();
		System.out.println(currentPeriod);
		
		for(int x=0; x<count; x++) {
			if(sheetName[x].contentEquals("Perm Entitlements")) {
				
				int rowCount=permEntitlement.getLastRowNum();
				Row lastEmployeerow=permEntitlement.getRow(rowCount);
				Row headerRow=permEntitlement.getRow(0);
				Row secondRow=permEntitlement.getRow(1);
				String[] headerCells=null;
				
				for(int y=3; y<lastEmployeerow.getLastCellNum(); y++) {
					headerCells[y]=headerRow.getCell(y).getStringCellValue();
				}
				
				
				for(int z=1; z<=noOfEmployeesToAdd; z++) {
					Row newEmployeeRow=permEntitlement.createRow(rowCount+z);
					
					for(int y=0; y<lastEmployeerow.getLastCellNum(); y++) {
						if(headerRow.getCell(y).getStringCellValue().contains("Employee Number")) {
							Cell newCell=newEmployeeRow.createCell(y);
							newCell.setCellValue(newEmpDetails.get(y).get(z-1));
				         }else if(headerRow.getCell(y).getStringCellValue().contains("Surname")) {
				        	Cell newCell=newEmployeeRow.createCell(y);
							newCell.setCellValue(newEmpDetails.get(y).get(z-1));
				         }else if(headerRow.getCell(y).getStringCellValue().contains("Forename")) {
					        	Cell newCell=newEmployeeRow.createCell(y);
								newCell.setCellValue(newEmpDetails.get(y).get(z-1));
				         }else {
				        	 String entitlement1=entitlementsPerm.get(ThreadLocalRandom.current().nextInt(2, 50));
				        	 String entitlement2=entitlementsPerm.get(ThreadLocalRandom.current().nextInt(2, 50));
				        	 Object[] entitlement1Details= {entitlement1, "", ThreadLocalRandom.current().nextInt(100, 300)};
				        	 Object[] entitlement2Details= {entitlement2, currentPeriod, ThreadLocalRandom.current().nextInt(100, 300)};
				        	 int entitlement1Present=0;
				        	 int entitlement2Present=0;
				        	 
				        	 for(int c=0; c<lastEmployeerow.getLastCellNum(); c++) {
				        		 String headerEntitle=headerRow.getCell(c).getStringCellValue();
				        		 if(headerEntitle.contains(entitlement1))	{entitlement1Present=c; break;	}
				        	 }
				        	 
				        	 for(int c=0; c<lastEmployeerow.getLastCellNum(); c++) {
				        		 String headerEntitle=headerRow.getCell(c).getStringCellValue();
				        		 if(headerEntitle.contains(entitlement1))	{entitlement2Present=c; break;	}
				        	 }
				        	 
				        	 if(entitlement1Present==0) {
				        		 for(int g=0; g<3; g++) {
				        			 Cell addElement=headerRow.createCell(headerRow.getLastCellNum()+1);
				        			 addElement.setCellValue(entitlement1+" 0");
				        		 }
				        		 Cell addElementSecond1=secondRow.createCell(secondRow.getLastCellNum()+1);
				        		 addElementSecond1.setCellValue("ENP!Element Type");
			        			 
			        			 Cell addElementSecond2=secondRow.createCell(secondRow.getLastCellNum()+1);
			        			 addElementSecond2.setCellValue("ENP!Effective To");
			        			 
			        			 Cell addElementSecond3=secondRow.createCell(secondRow.getLastCellNum()+1);
			        			 addElementSecond3.setCellValue("ENP!Amount");
			        			 
			        			 Cell addElementEmpEntitlement1=newEmployeeRow.createCell(y);
			        			 //secondRow.createCell(secondRow.getLastCellNum()+1);
			        			 addElementSecond3.setCellValue("ENP!Amount");
				        	 }
				        	 
				        	 int noOfEntitlementPermsToAdd=ThreadLocalRandom.current().nextInt(1, 3);
				        	 for (int w=0; w<noOfEntitlementPermsToAdd; w++) {
				        		 String entitlement=entitlementsPerm.get(ThreadLocalRandom.current().nextInt(2, 50));
				        		 int cell=3;
				        		 boolean yes=false;
				        		 for(String x1:headerCells) {
				        			 if(x1.contains(entitlement)) {	yes=true; break; }
				        			 cell++;
				        			 
				        		 }
				        		 if(yes=true) {
				        			 if(y==)
				        			 Cell newCell=newEmployeeRow.createCell(y);
				        			 newCell.setCellValue(entitlement);
				        		 }
				        	 }
				         }
						}
					}
				
			}
		}
		
*/		
		
/*		String filePath="C://Users//sribur19//Desktop//";
		String fileName="Automation Test Cases.xlsx";
		String sheetName="GPMS Reggression";
		
		File file =    new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook excelFile = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet=excelFile.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		System.out.println(rowCount);
		for(int i=3;i<=6; i++) {
			Row row=sheet.getRow(i);
			int rowLenght=row.getLastCellNum();
			for(int j=0; j<=rowLenght; j++) {
				System.out.println(row.getCell(j).getStringCellValue()+"|| ");
			}
			
		}System.out.println("--");
		
	*/	

	

		
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
		
		
		
	
	



