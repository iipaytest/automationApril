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
import org.testng.annotations.Test;

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
	
	public static boolean isClickable(WebDriver driver, WebElement name)      {
		try
		{
		   WebDriverWait wait = new WebDriverWait(driver, 5);
		   wait.until(ExpectedConditions.elementToBeClickable(name));
		   return true;
		}
		catch (Exception e)
		{
		  return false;
		}
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

	
	

	
	
	public void verifyStatusOfPayrollPeriod(WebDriver driver, String payrollName, String periodName) throws AWTException, InterruptedException, IOException {
		//This is to verify Status of payroll period to get count of employees in Awaiting Process, Locked, Processed, Confirmed
			
			driver.findElement(By.xpath(payrollSearchPageObjects.payrollToBeSelected(payrollName))).click();
			//System.out.println("Payroll Details are: "+driver.findElement(By.xpath(payrollPageObjects.payrollDetails)).getText());
			//System.out.println("Payroll Details are: "+driver.findElement(By.xpath(payrollPageObjects.payrollPeriodDetails)).getText());
			
			List<WebElement> columns=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr"));
			List<WebElement> rows=driver.findElements(By.xpath(payrollPageObjects.payrollPeriodDetails+"/tbody/tr[1]/td"));
			//System.out.println(columns.size());	//Number of periods displayed
			
			String[][] table = payrollPageObjects.captureAllPayrollDetailsIntoTable(driver);
			
			
			
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
						//commonMethods.reportsInboxRefreshUntillComplete(driver);
						//commonMethods.reportsInboxReportDownload(driver);
						
					break;
				}
			}
	}

			

		
		
	public void verifyStarterLeaverInPayrollPeriod(WebDriver driver, String payrollName, String periodName) throws AWTException, InterruptedException, IOException {
		//This is to get count of employees in Staters, Leavers, After Leavers, Historic Leavers
			
			
	}


}
