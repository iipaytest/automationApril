package gpmsAutomation.reusableMethods;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import gpmsAutomation.pageObjectsGPMS.employeeDetailsEPAPageObjects;
import gpmsAutomation.pageObjectsGPMS.menuBarLinks;
import gpmsAutomation.pageObjectsGPMS.menuPageObjects;
import gpmsAutomation.testInputs.testInputGPMS;

public class ePAMethods {

	public static void addPayemnts(WebDriver driver, String employeeNo, String[] payments) throws AWTException, InterruptedException {
		
		driver.findElement(By.xpath(menuPageObjects.jumpToTextBox)).sendKeys(employeeNo);
		menuBarLinks.goToJumpToEmployeeNumber(driver);
		
		if(driver.findElements(By.xpath(menuPageObjects.warning)).size()==1) {
			System.out.println("Employee Number'"+ employeeNo +"' do not exists, hence can't Add Payments to Employee step");
			Assert.assertEquals(0, driver.findElements(By.xpath(menuPageObjects.warning)).size());
		}else {
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
			driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payments)).click();
			
			if(payments==null) { 
				System.out.println("No payment information was given to add");
			}else	{
				for(int i=0; i<=payments.length; i++) {
					List <WebElement> paymentslist=driver.findElements(By.xpath("//div[@id='ddNavigateToSet_Panel']/select/option"));
					for(WebElement option :paymentslist) {
						if (option.getAttribute("innerText").contains("2_IT - Cometa ER")) { System.out.println(payments[i]);
				        option.click();
				        break;
						}
						List <WebElement> effectiveFromList=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.paymentEffectiveFrom+"/option"));
						for(WebElement option1 :effectiveFromList) {	if (option1.getText().contains(testInputGPMS.paymentsType1[1])) {     option.click();      break;	}	}
						Thread.sleep(1000);
						List <WebElement> effectiveToList=driver.findElements(By.xpath(employeeDetailsEPAPageObjects.paymentEffectiveTo+"/option"));
						for(WebElement option2 :effectiveToList) {	if (option2.getText().contains(testInputGPMS.paymentsType1[2])) {     option.click();      break;	}	}
						Thread.sleep(1000);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.paymentAmount)).sendKeys(testInputGPMS.paymentsType1[3]);
						Thread.sleep(1000);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();
					}
				}
			}
		}
	}
}
