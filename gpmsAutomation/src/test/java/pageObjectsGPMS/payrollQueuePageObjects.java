package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class payrollQueuePageObjects {
	
	
	public static final String status="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[5]";
	public static final String typeName="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[3]";
	public static final String refresh="//*[@id='btnRefresh']";
	public static final String requestDetailsButton="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[4]/img";
	public static final String requestDetailsTable="//*[@id='QueueList_requestCtl_0_tblMain']//*[@class='requestParameters']";
	public static final String runtimeDetailsToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download runtime summary details to local file system')]";
	public static final String runtimeDetailsSummary="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[6]/img";
	public static final String outPutReportToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download file to local file system')]";
	
	public static final String summaryWindowClose="//*[@id='processingMessages_close']";
	public static final String summaryWindowFrame="//*[@id='processingMessages_iframe']";
	public static final String summaryWindowFrameMessages="//body//*[contains(@class,'messageItem ')]";
	public static final String summaryWindowFrameMessagesLabel="//body//*[contains(@class, 'messageItemLabel ')]";
	
	public static final String backToPayroll="//*[@id='btnBack_button']";
	
	public static String refreshUntillComplete(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		int i=20; //no of seconds to wait before breaking while loop for clicking refresh button
		while(!(driver.findElement(By.xpath(payrollQueuePageObjects.status)).getAttribute("innerText").contains("Completed")) && i>=0) {Thread.sleep(1000);	driver.findElement(By.xpath(payrollQueuePageObjects.refresh)).click();	i--; }
		String status=driver.findElement(By.xpath(payrollQueuePageObjects.status)).getAttribute("innerText");
		//System.out.println(reportName+"'s Status: "+status);
		
		return status;
	}
	
	public static void getReportStatusMessages(WebDriver driver) throws InterruptedException, AWTException, IOException {	
		
		driver.findElement(By.xpath(payrollQueuePageObjects.runtimeDetailsSummary)).click();
		String label=" ";
		String message=" ";
		driver.switchTo().frame("processingMessages_iframe");
		int i=driver.findElements(By.xpath(payrollQueuePageObjects.summaryWindowFrameMessages)).size();
		for(int p=1; p<=i; p++) {	
			message=message+driver.findElement(By.xpath(payrollQueuePageObjects.summaryWindowFrameMessages+"["+p+"]")).getText()+"	";	
		}
		for(int p=1; p<=i; p++) {	
			label=label+driver.findElement(By.xpath(payrollQueuePageObjects.summaryWindowFrameMessagesLabel+"["+p+"]")).getText()+"	";	
		}
		
		if(label.contains("[WARNING]")==true) {label="Warning";}
		if(label.contains("[ERROR]")==true) {label="Error";}
		message="Completed with "+label+": "+message;
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath(payrollQueuePageObjects.summaryWindowClose)).click();
		System.out.println(message);
	}
	
	

}
