package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import reusableMethods.commonMethods;

public class menuBarLinks extends  menuPageObjects {
	
	//Search Options
		public static void goToForEmployee(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(search));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(forEmployee);
			Thread.sleep(500);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToForPayroll(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(search));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(forPayroll);
			Thread.sleep(500);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
	
	//Admin Options	
		public static void goToAddEmployee(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(addEmployee);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToAddPayroll(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(addPayroll);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToUserAdmin(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(userAdmin);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToUserSecurityConfig(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(userSecurityConfig);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToElementTypeAdmin(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(elementTypeAdmin);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToElementTypeGroups(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(elementTypeGroups);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToNotionalAmountETpyeAdmin(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(notionalAmountETpyeAdmin);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToUserQueueAdmin(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(admin));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(userQueueAdmin);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
	
		//Client Admin Options	
				public static void goToCompanyAdmin(WebDriver driver) throws AWTException, InterruptedException, IOException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(menuPageObjects.clientAdmin)));
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					commonMethods.waitForPageLoad(driver, driver.findElement(By.xpath(menuPageObjects.companyAdmin)));
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.companyAdmin));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToSupportDetailsAdmin(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.supportDetailsAdmin));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToClientLogoAdmin(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.clientLogoAdmin));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToBoxSettings(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.boxSettings));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToTermsOfUseText(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.termsOfUseText));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToGLReportConfigurations(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.glReportConfigurations));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToPaymentTypes(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.paymentTypes));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToEmpNoAutoGeneration(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.empNoAutoGeneration));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToElementTypeTranslations(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.elementTypeTranslations));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToElementTypeMappings(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.elementTypeMappings));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToElementBreakdownConfigurations(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.elementBreakdownConfigurations));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToDocumentStorage(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.documentStorage));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToWorkdayPECIPayrollMappings(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.workdayPECIPayrollMappings));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToUploadReformatters(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.uploadReformatters));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
				public static void goToPercentageCheckers(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.percentageCheckers));
					action.moveToElement(submenu2).build().perform();
					submenu2.click();	
				}
				
			//Lookup Data
				public static void goToUserDefinedPayrollDetails(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.lookupData));
					action.moveToElement(submenu2).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu3=driver.findElement(By.xpath(menuPageObjects.userDefinedPayrollDetails));
					action.moveToElement(submenu3).build().perform();
					submenu3.click();	
				}
				
				public static void goToUserDefinedLists(WebDriver driver) throws AWTException, InterruptedException {
					
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.lookupData));
					action.moveToElement(submenu2).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu3=driver.findElement(By.xpath(menuPageObjects.userDefinedLists));
					action.moveToElement(submenu3).build().perform();
					submenu3.click();	
				}

				public static void goToUserDefinedOrgUnits(WebDriver driver) throws AWTException, InterruptedException {
	
					Actions action = new Actions(driver);
					WebElement mainMenu = driver.findElement(By.xpath(menuPageObjects.admin));
					action.moveToElement(mainMenu).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu1=driver.findElement(By.xpath(menuPageObjects.clientAdmin));
					action.moveToElement(submenu1).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu2=driver.findElement(By.xpath(menuPageObjects.lookupData));
					action.moveToElement(submenu2).build().perform();
					Thread.sleep(1000);
					
					WebElement submenu3=driver.findElement(By.xpath(menuPageObjects.userDefinedOrgUnits));
					action.moveToElement(submenu3).build().perform();
					submenu3.click();	
				}
		
	//Reports options	
		public static void goToRequestReport(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(reports));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(requestReport);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToReportsInbox(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(reports));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(reportsInbox);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToAuditLog(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(reports));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(auditLog);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
	
	//Support Options	
		public static void goToSupportContactDetails(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(support));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(supportContactDetails);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToReleaseNotes(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(support));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(releaseNotes);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
	
	//iiPay Hub Links	
		public static void goToInsightDashboards(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(iiPayHubLinks));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(insightDashboards);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
		public static void goToReturnToHub(WebDriver driver) throws AWTException, InterruptedException {
			
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(iiPayHubLinks));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(returnToHub);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
	
		//Lookup Data
		public static void goToJumpToEmployeeNumber(WebDriver driver) throws AWTException, InterruptedException {
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath(jumpTo));
			action.moveToElement(mainMenu).build().perform();
			By element=By.xpath(jumpToEmployeeNumber);
			Thread.sleep(1000);
			WebElement submenu=driver.findElement(element);
			submenu.click();	
		}
		
	
}
