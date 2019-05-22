package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import pageObjectsGPMS.*;
import reusableMethods.*;
import testInputs.testInputGPMS;

public class addingEPADetails extends basicDetails{

	@Test
	public void addEPARandom() throws Exception {
		
		String startDate=null;
		String endDate=null;

		//Navigates to employee details page > edit payroll details page
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.employeePayrollDetails)).click();
		
		int noOfEPAsToAdd=ThreadLocalRandom.current().nextInt(2, 4);	//this picks a random from 2, 3, 4
		System.out.println("Total EPAs given are: "+noOfEPAsToAdd);
		
		for(int x=1; x<=noOfEPAsToAdd; x++) {
			
			//selects random element type from edit payroll details drop down
			commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.dropDownEmployeePayrollDetails);
			String EPASelected=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payrollDetailType)).getText();
			System.out.println("	EPA Selected: "+EPASelected);
			//this while loop will select different element type if the current selected one is "Payment Information (Configurable)"
			while(EPASelected.contentEquals("Payment Information (Configurable)")){
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.dropDownEmployeePayrollDetails);
			}
			
			//checks if we are on element type page, or there is a step between for selecting element type (exp: Entitlement (Unit Pay Perm)), navigate to required page by selecting random element type
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.save)).size()==0) {
				commonMethods.selectRandomFromList(driver, "//*[contains(@class, 'crimsonBorder')]");
				Thread.sleep(1000);
			}
			
			//Captures the element type's payroll Constraints (concurrent/continuous) details
			String payrollConstraints=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payrollDetailConstraints)).getText();
			
			//Checks for details button, if exists will click on it
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).size()!=0) {
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).click();
			}
			
			//Below 4 if loops get the Start and End dates based on payrollConstraints based on the new or existing element type 
			if(payrollConstraints.contentEquals("Non-Concurrent, Continuous")) {
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
					//Select startDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[2]//select")));
					
					Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
					endDatePrevious.selectByIndex(2);
					
					Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDateCurrent1.selectByVisibleText(endDatePrevious.getFirstSelectedOption().getText());
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
					
					startDate=startDateCurrent1.getFirstSelectedOption().getText();
					endDate="indefinite";
				}else {
					Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDate1.selectByIndex(1);
					
					startDate=startDate1.getFirstSelectedOption().getText();
					endDate="indefinite";
				}	
			}
			if(payrollConstraints.contentEquals("Concurrent, Non-Continuous")) {
					Select date=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					date.selectByIndex(1);
					startDate=date.getFirstSelectedOption().getText();
					date.selectByIndex(2);
					endDate=date.getFirstSelectedOption().getText();
				}
			
			if(payrollConstraints.contentEquals("Non-Concurrent, Non-Continuous")) {
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
					Select startDatePreviouos=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					String startDate1=startDatePreviouos.getFirstSelectedOption().getText();
					
					
					Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
					String endDate1=endDatePrevious.getFirstSelectedOption().getText();
					
					if(endDate1.contentEquals("indefinite")) {
						endDatePrevious.deselectByVisibleText(startDate1);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")).sendKeys(Keys.ARROW_DOWN);	
						endDate1=endDatePrevious.getFirstSelectedOption().getText();
					}
					
					Select date=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					date.selectByVisibleText(endDate1);
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
					startDate=date.getFirstSelectedOption().getText();
					endDate=date.getFirstSelectedOption().getText();
					}else {
						Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
						startDate1.selectByIndex(1);	
						startDate=startDate1.getFirstSelectedOption().getText();
						endDate=startDate1.getFirstSelectedOption().getText();
					}
				}
			if(payrollConstraints.contentEquals("Concurrent, Continuous, Auto End Date")) {
					Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDate1.selectByIndex(1);
					
					startDate=startDate1.getFirstSelectedOption().getText();
					
				}
			
			//Captures list of mandatory details for that element type
			List<WebElement> mandatoryFields=driver.findElements(By.xpath("//*[contains(@id, 'new')]//*[contains(@class, 'crimsonBorder')]"));
			int i=mandatoryFields.size();
			
			//Salary (ICP) is only element type which is both Concurrent and Continuous, and has Auto end date
			//So below if loop is for other than Salary (ICP), due to its specific behaviour
			if(!(payrollConstraints.contentEquals("Concurrent, Continuous, Auto End Date"))) {
				int j=0;
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(startDate);
				
				//this if loop decides how many mandatory field to be skipped in for loop after that, (1 or 2 mandatory fields as Start and end dates)
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[3]//select")).size()!=0) {
					Select endDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[3]//select")));
					endDateCurrent1.selectByVisibleText(endDate);
				}else {
					j++;
					i++;
				}
				//this for skips first 1 or 2 mandatory fields as Start and end dates are those first 1 or 2 and those are already captured in above 4 if loops group
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
					
					j++; 
					if(j>=i) {break;}
					}
			}else {//this else loop is for element type which is both Concurrent and Continuous (Salary (ICP)) 
				int j=0;
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(startDate);
				//this for loop will skip first mandatory field (start date, which is selected in above step), and fills other mandatory fields like Effective date: "11/03/2000", Salary: "2"
				for(WebElement we:mandatoryFields) {
					if(j<1) {}
					else {	if(we.getTagName().contains("input")) {	
							if(we.getAttribute("id").contains("txtDate"))	{
								we.clear();
								String todaysDate="11/03/2000";
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
					
					j++; 
					if(j>=i) {break;}
					}
			}
			
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
			System.out.println(commonMethods.successErrorMesssage(driver));
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();	
			
		}
	}


	@Test
	public void addEPARequired() throws Exception {
		
		String elementType=testInputGPMS.elementTypeEPA;
		String startDate=null;
		String endDate=null;

		//Navigates to employee details page > edit payroll details page
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.employeePayrollDetails)).click();
		
			//selects required element type from edit payroll details drop down
			commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.dropDownEmployeePayrollDetails, elementType);
			String EPASelected=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payrollDetailType)).getText();
			System.out.println("	EPA Selected: "+EPASelected);
			
			//checks if we are on element type page, or there is a step between for selecting element type (exp: Entitlement (Unit Pay Perm)), navigate to required page by selecting random element type
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.save)).size()==0) {
				commonMethods.selectRandomFromList(driver, "//*[contains(@class, 'crimsonBorder')]");
				Thread.sleep(1000);
			}
			
			//Captures the element type's payroll Constraints (concurrent/continuous) details
			String payrollConstraints=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payrollDetailConstraints)).getText();

			
			//Checks for details button, if exists will click on it
			if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).size()!=0) {
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.detailsButton)).click();
			}
			
			//Below 4 if loops get the Start and End dates based on payrollConstraints based on the new or existing element type 
			if(payrollConstraints.contentEquals("Non-Concurrent, Continuous")) {
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
					//Select startDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[2]//select")));
					
					Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
					endDatePrevious.selectByIndex(2);
					
					Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDateCurrent1.selectByVisibleText(endDatePrevious.getFirstSelectedOption().getText());
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
					
					startDate=startDateCurrent1.getFirstSelectedOption().getText();
					endDate="indefinite";
				}else {
					Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDate1.selectByIndex(1);
					
					startDate=startDate1.getFirstSelectedOption().getText();
					endDate="indefinite";
				}	
			}
			if(payrollConstraints.contentEquals("Concurrent, Non-Continuous")) {
					Select date=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					date.selectByIndex(1);
					startDate=date.getFirstSelectedOption().getText();
					date.selectByIndex(2);
					endDate=date.getFirstSelectedOption().getText();
				}
			
			if(payrollConstraints.contentEquals("Non-Concurrent, Non-Continuous")) {
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount)).size()>4) {
					Select endDatePrevious=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[5]//td[3]//select")));
					String endDate1=endDatePrevious.getFirstSelectedOption().getText();
					
					Select date=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					date.selectByVisibleText(endDate1);
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")).sendKeys(Keys.ARROW_DOWN);
					startDate=date.getFirstSelectedOption().getText();
					endDate=date.getFirstSelectedOption().getText();
					}else {
						Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
						startDate1.selectByIndex(1);	
						startDate=startDate1.getFirstSelectedOption().getText();
						endDate=startDate1.getFirstSelectedOption().getText();
					}
				}
			if(payrollConstraints.contentEquals("Concurrent, Continuous, Auto End Date")) {
					Select startDate1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
					startDate1.selectByIndex(1);
					
					startDate=startDate1.getFirstSelectedOption().getText();
					
				}
			
			//Captures list of mandatory details for that element type
			List<WebElement> mandatoryFields=driver.findElements(By.xpath("//*[contains(@id, 'new')]//*[contains(@class, 'crimsonBorder')]"));
			int i=mandatoryFields.size();
			
			//Salary (ICP) is only element type which is both Concurrent and Continuous, and has Auto end date
			//So below if loop is for other than Salary (ICP), due to its specific behaviour
			if(!(payrollConstraints.contentEquals("Concurrent, Continuous, Auto End Date"))) {
				int j=0;
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(startDate);
				
				//this if loop decides how many mandatory field to be skipped in for loop after that, (1 or 2 mandatory fields as Start and end dates)
				if(driver.findElements(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[3]//select")).size()!=0) {
					Select endDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[3]//select")));
					endDateCurrent1.selectByVisibleText(endDate);
				}else {
					j++;
					i++;
				}
				//this for skips first 1 or 2 mandatory fields as Start and end dates are those first 1 or 2 and those are already captured in above 4 if loops group
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
					
					j++; 
					if(j>=i) {break;}
					}
			}else {//this else loop is for element type which is both Concurrent and Continuous (Salary (ICP)) 
				int j=0;
				Select startDateCurrent1=new Select(driver.findElement(By.xpath(employeeDetailsEPAPageObjects.jobDetailsRowsCount+"[4]//td[2]//select")));
				startDateCurrent1.selectByVisibleText(startDate);
				//this for loop will skip first mandatory field (start date, which is selected in above step), and fills other mandatory fields like Effective date: "11/03/2010", Salary: "2"
				for(WebElement we:mandatoryFields) {
					if(j<1) {}
					else {	if(we.getTagName().contains("input")) {	
							if(we.getAttribute("id").contains("txtDate"))	{
								we.clear();
								String todaysDate="11/03/2010";
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
					
					j++; 
					if(j>=i) {break;}
					}
			}
			
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
			System.out.println(commonMethods.successErrorMesssage(driver));
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();	
			
		
		
		
		
	}




}
