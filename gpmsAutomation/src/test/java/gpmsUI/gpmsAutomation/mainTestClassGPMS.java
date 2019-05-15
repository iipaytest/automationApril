package gpmsUI.gpmsAutomation;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

		
 		List<String> payments=new ArrayList<String>();
		List<String> deductions=new ArrayList<String>();
		List<String> unitPay=new ArrayList<String>();
		List<String> notionalAmountsPerm=new ArrayList<String>();
		List<String> notionalAmountsTemp=new ArrayList<String>();
		List<String> entitlementsPerm=new ArrayList<String>();
		List<String> entitlementsTemp=new ArrayList<String>();
		List<String> entitlementsUnitPay=new ArrayList<String>();
 		
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
					unitPay.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
			//Capture all Notional Amounts Perm and Notional Amounts Temp into List<String>	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmounts)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountPermButton)).click(); Thread.sleep(1000);
				int noOfNotionalAmountsPerm=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option")).size();
				for(int j=1; j<=noOfNotionalAmountsPerm; j++) {
					notionalAmountsPerm.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountTempButton)).click();  Thread.sleep(1000);
				int noOfNotionalAmountsTemp=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option")).size();
				for(int j=1; j<=noOfNotionalAmountsTemp; j++) {
					notionalAmountsTemp.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDropDown+"/option["+j+"]")).getText());
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
					entitlementsUnitPay.add(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsDropDown+"/option["+j+"]")).getText());
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				
				System.out.println(payments.get(1));
				System.out.println(deductions.get(1));
				System.out.println(unitPay.get(1));
				System.out.println(notionalAmountsTemp.get(1));
				System.out.println(notionalAmountsPerm.get(1));
				System.out.println(entitlementsTemp.get(1));
				System.out.println(entitlementsPerm.get(1));
				System.out.println(entitlementsUnitPay.get(1));
				
				System.out.println(payments.size());
				System.out.println(deductions.size());
				System.out.println(unitPay.size());
				System.out.println(notionalAmountsTemp.size());
				System.out.println(notionalAmountsPerm.size());
				System.out.println(entitlementsTemp.size());
				System.out.println(entitlementsPerm.size());
				System.out.println(entitlementsUnitPay.size());

		
		//String fileName=testInputGPMS.clientID+"."+reportsPageObjects.downloadedReportName(driver);
		String fileName="AUTO.Employee_Data_Upload_Template_Employee_Data_Upload_Template.(20190513.12-57-38).xlsx";
		String filePath=System.getProperty("user.dir")+"\\reportsDownloaded\\";
		
		//Adding new employees
		int noOfEmployeesToAdd=2;
		ArrayList<ArrayList<String>> newEmpDetails=addingDetailsInEmployeeUploadTemplate.addingEmployeesDuplicateInMasterData(filePath, fileName, noOfEmployeesToAdd);
		
		addingDetailsInEmployeeUploadTemplate.addingEmployeesInOtherSheets(filePath, fileName, "Perm Entitlements", newEmpDetails, noOfEmployeesToAdd);
		
		String permEntitlementName="Perm Entitlements";
		String payrollValidationName="Payroll Validation";
		String uploadKeyValue="TestMay14";
		
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		//read employee upload template and check for required sheet existence
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		
		Sheet payrollValidaion=employeeUploadTemplate.getSheet(payrollValidationName);
		Row payrollValidationHeader=payrollValidaion.getRow(1);
		Cell periodName=payrollValidationHeader.getCell(2);
		String currentPeriod=periodName.getStringCellValue();
		Cell uploadKey=payrollValidationHeader.getCell(3);
		uploadKey.setCellValue(uploadKeyValue);
		
		Sheet permEntitlement=employeeUploadTemplate.getSheet(permEntitlementName);
		Row header=permEntitlement.getRow(0);
		Row secondRow=permEntitlement.getRow(1);
		int headerLenght=header.getLastCellNum();
		int rowCountpermEntitlement=permEntitlement.getLastRowNum();
		
		//String entitlement1="Inconvenient Hours 500";
		String entitlement1=entitlementsPerm.get(ThreadLocalRandom.current().nextInt(2, 50));
   	 	//String entitlement2=entitlementsPerm.get(ThreadLocalRandom.current().nextInt(2, 50));
   	 	Object[] entitlement1Details= {entitlement1, "", ThreadLocalRandom.current().nextInt(100, 300)};
   	 	//Object[] entitlement2Details= {entitlement2, currentPeriod, ThreadLocalRandom.current().nextInt(100, 300)};
		
		List<String> headers=new ArrayList<String>();
   	 	for(int x=0; x<headerLenght; x++) {		headers.add(header.getCell(x).getStringCellValue()); 	}

   	 	int y=0;
   	 	int entitlement1ColumnNum=0;
   	 	for(String required: headers) {
   	 		y++;
   	 		if(required.contains(entitlement1)) {
   	 		entitlement1ColumnNum=y;
   	 			break;
   	 		}
   	 	}
   	 	
   	 	if(y==0) {
	   	 	for(int x=0; x<rowCountpermEntitlement; x++) {
				Row row=permEntitlement.getRow(x);
				Cell cell=row.getCell(entitlement1ColumnNum);
				if(cell==null || cell.getCellType()==CellType.BLANK) { 	rowCountpermEntitlement=x+1; break;}
			}
	   	 	
	   	 	Row required=permEntitlement.getRow(rowCountpermEntitlement);
	   	 	Cell x1=required.createCell(entitlement1ColumnNum-1);
	   	 	x1.setCellValue(String.valueOf(entitlement1Details[0]));
	   	 	
	   	 	Cell x2=required.createCell(entitlement1ColumnNum);
	   	 	x2.setCellValue(String.valueOf(entitlement1Details[1]));
	   	 	
	   	 	Cell x3=required.createCell(entitlement1ColumnNum+1);
	   	 	x3.setCellValue((Integer) entitlement1Details[2]);
			
	   	 	
   	 	}else {
	   	 	Cell x1=header.createCell(headerLenght);
	   	 	x1.setCellValue(entitlement1+" 0");
	   	 	
	   	 	Cell x2=header.createCell(headerLenght+1);
	   	 	x2.setCellValue(entitlement1+" 0");
	   	 	
	   	 	Cell x3=header.createCell(headerLenght+2);
	   	 	x3.setCellValue(entitlement1+" 0");
	   	 	
	   	 	Cell y1=secondRow.createCell(headerLenght);
	   	 	y1.setCellValue("ENP!Element Type");
	   	 	
	   	 	Cell y2=secondRow.createCell(headerLenght+1);
	   	 	y2.setCellValue("ENP!Effective To");
	   	 	
	   	 	Cell y3=secondRow.createCell(headerLenght+2);
	   	 	y3.setCellValue("ENP!Amount");
	   	 	
	   	 	for(int x=0; x<rowCountpermEntitlement; x++) {
				Row row=permEntitlement.getRow(x);
				Cell cell=row.getCell(headerLenght);
				if(cell==null || cell.getCellType()==CellType.BLANK) { 	rowCountpermEntitlement=x+1; break;}
			}
	   	 	
	   	 	Row required=permEntitlement.getRow(rowCountpermEntitlement);
	   	 	Cell z1=required.createCell(headerLenght);
	   	 	z1.setCellValue(String.valueOf(entitlement1Details[0]));
	   	 	
	   	 	Cell z2=required.createCell(headerLenght+1);
	   	 	z2.setCellValue(String.valueOf(entitlement1Details[1]));
	   	 	
	   	 	Cell z3=required.createCell(headerLenght+2);
	   	 	z3.setCellValue((Integer) entitlement1Details[2]);
	   	 	
   	 	}
   	 	
   	 	inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		outputStream.close();	
   	 	
		
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
		}
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
		
		
		
	
	



