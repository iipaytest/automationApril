package reusableMethods;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjectsGPMS.*;
import testInputs.testInputGPMS;

public class commonMethods {
	
	static File shot;
	
	public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
		
		shot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(shot,new File(testInputGPMS.gpmsTestOutputsLocation+" "+fileName+".jpg"));
		
	}
	
	public static void takeScreenShotOfElement(WebDriver driver, String fileName, WebElement webElement) throws IOException {
		
		shot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(shot);
		
		Point point = webElement.getLocation();
		int webElementWidth = webElement.getSize().getWidth();
		int webElementHeight = webElement.getSize().getHeight();
		
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), webElementWidth, webElementHeight);
			ImageIO.write(eleScreenshot, "jpg", shot);
		
		FileHandler.copy(shot,new File(testInputGPMS.gpmsTestOutputsLocation+" "+fileName+".jpg"));
		
	}
	
	
	public static void waitForPageLoad(WebDriver driver, WebElement elementName) throws IOException {
		
		try{
			WebDriverWait d=new WebDriverWait(driver,10);
			d.until(ExpectedConditions.visibilityOf(elementName));
		}catch(Exception e) {
			Assert.fail("Element looking for not found");
		}
		
		
	}
	
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
			Assert.fail("Failed: Payroll periods of given Payroll Year already exits");
			}
		else if(driver.findElements(By.xpath(editPeriodsPageObjects.generate)).size()==0){
			System.out.println("Failed: Given Payroll Year dont exits in drop down list");
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
	
	public static void successErrorMesssage(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		if(driver.findElements(By.xpath(commonPageObjects.successful)).size()==1 && driver.findElement(By.xpath(commonPageObjects.successful)).getText().contains("success")) System.out.println("	Message: "+driver.findElement(By.xpath(commonPageObjects.successful)).getAttribute("innerText"));
		if(driver.findElements(By.xpath(commonPageObjects.error)).size()==1 && driver.findElement(By.xpath(commonPageObjects.error)).getText().contains("ERROR")) System.out.println("	Error Message: "+driver.findElement(By.xpath(commonPageObjects.error)).getAttribute("innerText"));
		if(driver.findElements(By.xpath(commonPageObjects.warning)).size()==1 && driver.findElement(By.xpath(commonPageObjects.warning)).getText().contains("Warning")) System.out.println("	Warning Message: "+driver.findElement(By.xpath(commonPageObjects.warning)).getAttribute("innerText"));
		if(driver.findElements(By.xpath(commonPageObjects.errorSummary)).size()==1 && driver.findElement(By.xpath(commonPageObjects.errorSummary)).isDisplayed()) System.out.println("	Error Summary: "+driver.findElement(By.xpath(commonPageObjects.errorSummary)).getAttribute("innerText"));
		
	}
	
	public static void addPayDedsDetails(WebDriver driver, String effectiveFrom, String effectiveTo, String Amount) throws InterruptedException {
		Thread.sleep(500);
		selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveFrom, effectiveFrom);Thread.sleep(500);
		selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveTo);Thread.sleep(500);
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(Amount);
		driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
	}
	
	public static void selectFromListExactText(WebDriver driver, String xpathString, String textToMatch) throws InterruptedException {
		
		Select drpList = new Select(driver.findElement(By.xpath(xpathString)));
		try{
			drpList.selectByVisibleText(textToMatch);
		}catch (Exception e){
			System.out.println("Failed: Option '"+textToMatch+"' not found in the drop down your are looking for");
			Assert.fail("Failed: Option '"+textToMatch+"' not found in the drop down your are looking for");
		}Thread.sleep(500);
	}
	
	public static void selectFromListPartialText(WebDriver driver, String xpathString, String textToMatch) throws InterruptedException {
		
		List <WebElement> list=driver.findElements(By.xpath(xpathString+"/option"));
		for(WebElement select :list) {
			if (select.getText().contains(textToMatch)) {
				select.click();
	        break;
			}
		}Thread.sleep(500);
	}
	
	public static String selectRandomFromList(WebDriver driver, String xpathForDropDown) throws InterruptedException {		
		List <WebElement> randomOption = driver.findElements(By.xpath(xpathForDropDown+"/option"));
		int sizeOfOptions=randomOption.size();
		int randomNo=ThreadLocalRandom.current().nextInt(2, sizeOfOptions);
		String itemSelected=randomOption.get(randomNo).getText();
		System.out.println("	"+itemSelected);
		randomOption.get(randomNo).click();
		Thread.sleep(500);
		return itemSelected;
		
	}


	public static void searchPayroll(WebDriver driver, String payrollName) throws InterruptedException, AWTException {		
		menuBarLinks.goToForPayroll(driver);
		
	}
	
	public static void reportsInboxRefreshUntillComplete(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		int i=20; //no of seconds to wait before breaking while loop for clicking refresh button
		while(driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText").contains("Processing") && i>=0) {Thread.sleep(1000);	driver.findElement(By.xpath(reportsPageObjects.refresh)).click();	i--; }
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		String reportName=driver.findElement(By.xpath(reportsPageObjects.typeReportName)).getAttribute("innerText");
		System.out.println(reportName+"'s Status: "+status);
	}

	public static void reportsInboxReportDownload(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		
		Thread.sleep(1000);
		String status=driver.findElement(By.xpath(reportsPageObjects.status)).getAttribute("innerText");
		String reportName=driver.findElement(By.xpath(reportsPageObjects.typeReportName)).getAttribute("innerText");
		
		if(!(status.contains("Complete"))) {	
			System.out.println("Failed: "+reportName+" not generated, Status message: "+status);
			if(driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsToLocalFile)).isDisplayed()) {
				driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();	
			}
			driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
			commonMethods.takeScreenShotOfElement(driver, reportName+" generation failed_Processing Summary", driver.findElement(By.xpath(reportsPageObjects.summaryWindowFrame)));
			driver.findElement(By.xpath(reportsPageObjects.summaryWindowClose)).click();
			driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
			commonMethods.takeScreenShotOfElement(driver, reportName+" generation failed_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
			Assert.fail("Failed: "+reportName+" not generated, Status message: "+status);
		}else {
			driver.findElement(By.xpath(reportsPageObjects.outPutReportToLocalFile)).click();
			System.out.println("Passed: "+reportName+" generated successfully, Status message: "+status);
			driver.findElement(By.xpath(reportsPageObjects.runtimeDetailsSummary)).click();
			commonMethods.takeScreenShotOfElement(driver, reportName+" generation successfully_Processing Summary", driver.findElement(By.xpath(reportsPageObjects.summaryWindowFrame)));
			driver.findElement(By.xpath(reportsPageObjects.summaryWindowClose)).click();
			driver.findElement(By.xpath(reportsPageObjects.requestDetailsButton)).click();
			commonMethods.takeScreenShotOfElement(driver, reportName+" generated successfully_Details", driver.findElement(By.xpath(reportsPageObjects.requestDetailsTable)));
		}
	}




}
