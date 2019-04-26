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
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class downloadReports extends basicDetails{

	
	@Test
	//This is verify Lock Function from Employee level
	public void downloadEmployeeDataUploadTemplate() throws AWTException, InterruptedException, IOException {
		
		
		menuBarLinks.goToForPayroll(driver);
		driver.findElement(By.xpath(payrollSearchPageObjects.payrollName)).sendKeys(testInputGPMS.payrollName);
		driver.findElement(By.xpath(payrollSearchPageObjects.search)).click();
		Thread.sleep(500);
		if(driver.findElements(By.xpath(payrollSearchPageObjects.payrollToBeSelected(testInputGPMS.payrollName))).size()==0) {
			System.out.println("Failed: As  Payoll Name '"+ testInputGPMS.payrollName +"' don't exists");
			Assert.fail("Failed: As  Payoll Name '\"+ testInputGPMS.payrollName +\"' don't exists");
		}else {
			driver.findElement(By.xpath(payrollSearchPageObjects.payrollToBeSelected(testInputGPMS.payrollName))).click();
			//System.out.println("Payroll Details are: "+driver.findElement(By.xpath(payrollPageObjects.payrollDetails)).getText());
			//System.out.println("Payroll Details are: "+driver.findElement(By.xpath(payrollPageObjects.payrollPeriodDetails)).getText());
			
			List<WebElement> columns=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr"));
			List<WebElement> rows=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr[1]/td"));
			//System.out.println(columns.size());	//Number of periods displayed
			
			String[][] table = new String[columns.size()][rows.size()];
			WebElement[][] element = new WebElement[columns.size()][rows.size()];
			for (int i=1; i < columns.size(); i++) {
				for (int j=1; j < rows.size(); j++) {
					table[i-1][j-1] = driver.findElement(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr["+i+"]/td["+j+"]")).getAttribute("innerText");
					element[i-1][j-1] = driver.findElement(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr["+i+"]/td["+j+"]"));
				}
			}	
			for(int i=columns.size()-1; i >= 1; i--) {
				if(table[i][4]!=null) {
					String period=table[i][1];
						System.out.println("Current pay period is: "+period);
				/*		System.out.println("Employees in Awaiting Process stage are: "+table[i][4]);
						System.out.println("Employees in Locked stage are: "+table[i][5]);
						System.out.println("Employees in Processed stage are: "+table[i][6]);
						System.out.println("Employees in Confirmed stage are: "+table[i][7]);
						System.out.println("No of Starters: "+table[i][8]);
						System.out.println("No of Leavers: "+table[i][9]);
						System.out.println("No of After Leavers: "+table[i][10]);
						System.out.println("No of Historic Leavers: "+table[i][11]);
				*/		element[i][0].click();
						driver.findElement(By.xpath(payrollPageObjects.reportsEmployeeDataUploadTemplate)).click();
						driver.findElement(By.xpath(commonPageObjects.submitButton)).click();
						commonMethods.reportsInboxRefreshUntillComplete(driver);
						commonMethods.reportsInboxReportDownload(driver);
						
					break;
				}
			}
		}
	}
}
