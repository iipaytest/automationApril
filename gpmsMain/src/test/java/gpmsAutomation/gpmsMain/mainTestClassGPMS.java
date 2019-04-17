package gpmsAutomation.gpmsMain;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;

import gpmsAutomation.pageObjectsGPMS.addNewPayrollPageObjects;
import gpmsAutomation.pageObjectsGPMS.elementTypeSelectionPageObjects;
import gpmsAutomation.pageObjectsGPMS.menuBarLinks;
import gpmsAutomation.pageObjectsGPMS.userDefinedOrgUnitAdminPageObjects;
import gpmsAutomation.reusableMethods.commonMethods;
import gpmsAutomation.testInputs.testInputGPMS;

public class mainTestClassGPMS {
	
	static WebDriver driver;
	
	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\cofigFiles\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//commonMethods.takeScreenShot(driver, "GPMS Version Verified");
		//String url=testInputGPMS.urlTST3redirector;
		
		
		driver.get(testInputGPMS.urlTST3redirector);
		driver.findElement(By.cssSelector("input#ClientId")).sendKeys(testInputGPMS.clientID);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		
		
		driver.findElement(By.cssSelector("input#Username")).sendKeys(testInputGPMS.userName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(testInputGPMS.password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
        
        menuBarLinks.goToElementTypeAdmin(driver);
        elementTypeSelectionPageObjects.isElementTypePresent(driver, "month");
		
        
	}
}
	
			
		 
		
		
		
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
		
		
		
	
	



