package gpmsAutomation.reusableMethods;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import gpmsAutomation.pageObjectsGPMS.menuBarLinks;
import gpmsAutomation.pageObjectsGPMS.reportsPageObjects;

public class reportsMethods {
	
public static void downloadChangesExportReport(WebDriver driver, String payrollName, String period, String layout, Boolean omit) throws IOException, AWTException, InterruptedException {
		
		menuBarLinks.goToRequestReport(driver);
		List <WebElement> reports=driver.findElements(By.xpath(reportsPageObjects.availableReports+"/option"));
		for(WebElement option :reports) {	if (option.getText().contains("Changes Export Report")) {   option.click();     break;	}	}
			
		
		
		Thread.sleep(500);
		List <WebElement> payrolls=driver.findElements(By.xpath(reportsPageObjects.payroll+"/option"));
		for(WebElement option :payrolls) { 	if (option.getText().contains(payrollName)) {     option.click();    break;	}	}
		
		Thread.sleep(500);
		List <WebElement> periods=driver.findElements(By.xpath(reportsPageObjects.period+"/option"));
		for(WebElement option :periods) {	if (option.getText().contains(period)) {	option.click();  break;		}	}
		
		Thread.sleep(500);
		List <WebElement> layouts=driver.findElements(By.xpath(reportsPageObjects.layoutl+"/option"));
		for(WebElement option :layouts) { 	if (option.getText().contains(layout)) {	option.click();  break;		}	}
		
		Thread.sleep(500);		
		if(driver.findElements(By.xpath(reportsPageObjects.omitBlankSheets)).size()==0) {	
			System.out.println("Failed: Details to Changes Export Report are not provided");
			Assert.assertEquals(1, 0);
		}
		if(omit==true)	{driver.findElement(By.xpath(reportsPageObjects.omitBlankSheets)).click();}
		
		driver.findElement(By.xpath(reportsPageObjects.submit)).click();
		Thread.sleep(1000);
		while(driver.findElement(By.xpath(reportsPageObjects.completed)).getAttribute("innerText").contains("Processing")) {Thread.sleep(1000);	driver.findElement(By.xpath(reportsPageObjects.refresh)).click();	}
		
		driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
		System.out.println("Requested details of report are as below \n"+driver.findElement(By.xpath(reportsPageObjects.requestDetailsDetails)).getAttribute("innerText"));
		
		if(driver.findElements(By.xpath(reportsPageObjects.downloadReportToLocalFile)).size()==0) {	
			System.out.println("Failed: Details to Changes Export Report not generated");
			Assert.assertEquals(1, 0);
		}
		driver.findElement(By.xpath(reportsPageObjects.downloadReportToLocalFile)).click();
	}
}
