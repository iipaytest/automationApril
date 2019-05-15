package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import reusableMethods.commonMethods;

public class reportsPageObjects {
	
	public static final String availableReports="//select[@id='ddReports_DropDown']";
	public static final String payroll="//select[contains(@id,'payroll_DropDown')]";
	public static final String period="//select[contains(@id,'period_DropDown')]";
	public static final String layoutl="//select[contains(@id,'layout_DropDown')]";
	public static final String omitBlankSheets="//*[contains(text(),'Omit Blank Sheets')]/preceding-sibling::input[1]";
	
	
	
	public static final String submit="//*[@id='btnSubmit']";
	
	public static final String status="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[5]";
	public static final String typeReportName="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[3]";
	public static final String refresh="//*[@id='btnRefresh']";
	public static final String requestDetailsButton="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[4]/img";
	//public static final String requestDetailsDetails="//*[@id='QueueList_requestCtl_0_tblMain']//tr[2]//table[1]";
	public static final String requestDetailsTable="//*[@id='QueueList_requestCtl_0_tblMain']//*[@class='requestParameters']";
	public static final String layoutType=requestDetailsTable+"//*[text()='Layout:']/following-sibling::td[1]";
	public static final String requestTime=requestDetailsTable+"//*[text()='Request Time:']/following-sibling::td[1]";
	public static final String runtimeDetailsToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download runtime summary details to local file system')]";
	public static final String runtimeDetailsSummary="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[6]/img";
	public static final String outPutReportToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download file to local file system')]";
	
	
	public static final String summaryWindowClose="//*[@id='processingMessages_close']";
	public static final String summaryWindowFrame="//*[@id='processingMessages_iframe']";
	
	
	public static void reportsInboxRefreshUntillComplete(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		int i=20; //no of seconds to wait before breaking while loop for clicking refresh button
		while(driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText").contains("Processing") && i>=0) {Thread.sleep(1000);	driver.findElement(By.xpath(reportsPageObjects.refresh)).click();	i--; }
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		String reportName=driver.findElement(By.xpath(reportsPageObjects.typeReportName)).getAttribute("innerText");
		System.out.println(reportName+"'s Status: "+status);
	}

	public static Boolean isReportStatusComplete(WebDriver driver) throws InterruptedException, AWTException, IOException {	
		Thread.sleep(1000);
		Boolean isReportStatusComplete=false;
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		
		if(!(status.contains("Complete"))) {	isReportStatusComplete=false;	}
		else	isReportStatusComplete=true;
		
		return isReportStatusComplete;
	}
	
	public static void reportsInboxReportDownload(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		String reportNameType=driver.findElement(By.xpath(reportsPageObjects.typeReportName)).getAttribute("innerText");
		 if(reportsPageObjects.isReportStatusComplete(driver)==false) {
			 System.out.println("Failed: "+reportNameType+" not generated, Status message: "+status);
				if(driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsToLocalFile)).isDisplayed()) {
					driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsToLocalFile)).click();	
				}
				driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
				commonMethods.takeScreenShotOfElement(driver, reportNameType+" generation failed_Processing Summary", driver.findElement(By.xpath(reportsPageObjects.summaryWindowFrame)));
				driver.findElement(By.xpath(reportsPageObjects.summaryWindowClose)).click();
				driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
				commonMethods.takeScreenShotOfElement(driver, reportNameType+" generation failed_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
				driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
				Assert.fail("Failed: "+reportNameType+" not generated, Status message: "+status);
		 }else {
			 	driver.findElement(By.xpath(reportsPageObjects.outPutReportToLocalFile)).click();
				System.out.println("Passed: "+reportNameType+" generated successfully, Status message: "+status);
				driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
				commonMethods.takeScreenShotOfElement(driver, reportNameType+" generation successfully_Processing Summary", driver.findElement(By.xpath(reportsPageObjects.summaryWindowFrame)));
				driver.findElement(By.xpath(reportsPageObjects.summaryWindowClose)).click();
				driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
				commonMethods.takeScreenShotOfElement(driver, reportNameType+" generated successfully_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
				driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
		 }
		
	}
	
	public static String downloadedReportName(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		String reportNameType=driver.findElement(By.xpath(reportsPageObjects.typeReportName)).getAttribute("innerText");
		String reportLayout="";
		String reportRequestTime="";
		String reportName="";
		
		if(!(status.contains("Complete"))) {	
			System.out.println("Failed: "+reportNameType+" not generated, Status message: "+status);
			if(driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsToLocalFile)).isDisplayed()) {
				driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();	
			}
			driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
			commonMethods.takeScreenShotOfElement(driver, reportNameType+" generation failed_Processing Summary", driver.findElement(By.xpath(reportsPageObjects.summaryWindowFrame)));
			driver.findElement(By.xpath(reportsPageObjects.summaryWindowClose)).click();
			driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
			commonMethods.takeScreenShotOfElement(driver, reportNameType+" generation failed_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
			Assert.fail("Failed: "+reportNameType+" not generated, Status message: "+status);
		}else {
			driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
			commonMethods.takeScreenShotOfElement(driver, reportNameType+" generated successfully_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
			reportLayout=driver.findElement(By.xpath(reportsPageObjects.layoutType)).getText();
			reportRequestTime=driver.findElement(By.xpath(reportsPageObjects.requestTime)).getText();
		}
		Date date = new Date(); 											// Current System Date and time is assigned to objDate
		String strDateFormat = "yyyyMMdd"; 									//Date format is Specified
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		String date1=objSDF.format(date);
		reportName=reportNameType.replace(" ", "_")+"_"+reportLayout.replace(" ", "_")+".("+date1+"."+reportRequestTime.replace(":", "-")+").xlsx";
		return reportName;
	}
	
}
