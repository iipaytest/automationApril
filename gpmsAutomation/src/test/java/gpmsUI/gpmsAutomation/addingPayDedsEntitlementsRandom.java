package gpmsUI.gpmsAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsGPMS.employeeDetailsEPAPageObjects;
import pageObjectsGPMS.employeeDetailsPageObjects;
import pageObjectsGPMS.employeeSearchPageObjects;
import pageObjectsGPMS.menuBarLinks;
import pageObjectsGPMS.menuPageObjects;
import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class addingPayDedsEntitlementsRandom extends basicDetails {

	
	@Test
	public void addPaymentsRandom() throws IOException, AWTException, InterruptedException {
		
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
			
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Payments_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Payments, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
			
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payments)).click();				
				int noOfPaymentsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many payments randomly
				System.out.println("Totals Payments given are: "+noOfPaymentsToAdd);
				for(int i=1; i<=noOfPaymentsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.payDedsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(500);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveFrom+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, "indefinite");
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To: Indefinite	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						String effectiveTo=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsRecentSavedEffectiveTo+"//option[text()='"+effectiveFrom+"']/following-sibling::option[2]")).getText();
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveTo+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Payments Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}	
	}	

	@Test
	public void addDeductionsRandom() throws IOException, AWTException, InterruptedException {
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
		employeeSearchPageObjects.goToRequiredEmployeePage(driver, testInputGPMS.employeeNo);
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Deductions_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Deductions, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.deductions)).click();				
				int noOfDeductionsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many deductions randomly
				System.out.println("Totals Deductions given are: "+noOfDeductionsToAdd);
				for(int i=1; i<=noOfDeductionsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.payDedsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveFrom+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, "indefinite");
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To: Indefinite	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.payDedsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						String effectiveTo=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.payDedsRecentSavedEffectiveTo+"//option[text()='"+effectiveFrom+"']/following-sibling::option[2]")).getText();
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveTo+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Deductions Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
		}
	}
	
	@Test
	public void addUnitPayRandom() throws IOException, AWTException, InterruptedException {
		
		if(employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo)==false) {
			System.out.println("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Unit Pay_Emp No dont exists");
			Assert.fail("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
		
		}else{
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Unit Pay_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPay)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayAllTypes)).click(); Thread.sleep(1000);
				int noOfUnitPaysToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Unit Pays randomly
				System.out.println("Totals Unit Pays given are: "+noOfUnitPaysToAdd);
				for(int i=1; i<=noOfUnitPaysToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.unitPayDropDown);
					if(i==1) {
						String unitPayUnits=String.valueOf(ThreadLocalRandom.current().nextInt(2, 6));
						String unitPayRateOverride=String.valueOf(ThreadLocalRandom.current().nextInt(10, 15));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayUnits)).sendKeys(unitPayUnits);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayRateOverride)).sendKeys(unitPayRateOverride);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Unit Pay Units:"+unitPayUnits+" Unit Pay Rate Override:"+unitPayRateOverride);
						commonMethods.successErrorMesssage(driver);
					}
					else {
						String unitPayUnits=String.valueOf(ThreadLocalRandom.current().nextInt(2, 6));
						String unitPayFinalAmountOverride=String.valueOf(ThreadLocalRandom.current().nextInt(5, 10));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayUnits)).sendKeys(unitPayUnits);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.unitPayFinalAmountOverride)).sendKeys(unitPayFinalAmountOverride);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Unit Pay Units:"+unitPayUnits+" Unit Pay Final Amount Override:"+unitPayFinalAmountOverride);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Unit Pays Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}
	
	
	@Test
	public void addEntitlementsTempRandom() throws IOException, AWTException, InterruptedException {
		
		if(employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo)==false) {
			System.out.println("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Temp_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
		
		}else{
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Temp_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTemp)).click();
				int noOfEntitlementsTempsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Temps randomly
				System.out.println("Totals Entitlement Temps given are: "+noOfEntitlementsTempsToAdd);
				for(int i=1; i<=noOfEntitlementsTempsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(200, 300));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTempAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else {
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(200, 300));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsTempAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Entitlements Temp Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}
		
	}
	
	
	@Test
	public void addEntitlementsPermRandom() throws IOException, AWTException, InterruptedException {
		
		if(employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo)==false) {
			System.out.println("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Perm_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
		
		}else{
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Perm_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPerm)).click();
				int noOfEntitlementsPermsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Perms randomly
				System.out.println("Totals Entitlement Perms given are: "+noOfEntitlementsPermsToAdd);
				for(int i=1; i<=noOfEntitlementsPermsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveFrom+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveFrom+"/option")).getText();
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, "indefinite");
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To: Indefinite	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}else {
						String effectiveFrom=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveFrom+"/option")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.entitlementsEffectiveTo, effectiveFrom);
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 2000));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsEffectiveTo)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						String effectiveTo=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsPermRecentSavedEffectiveTo+"//option[text()='"+effectiveFrom+"']/following-sibling::option[2]")).getText();
						System.out.println("	"+itemSelected+" > Effective From:"+effectiveFrom+"	Effective To:"+effectiveTo+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Entitlements Perm Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
			}
		}	
	}
	
	@Test
	public void addEntitlementsUnitPayRandom() throws IOException, AWTException, InterruptedException {
		
		if(employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo)==false) {
			System.out.println("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
			commonMethods.takeScreenShot(driver, "Failed adding Entitlement Unit Pay_Emp No dont exists");
			Assert.fail("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't exists");
		
		}else{
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Entitlement Unit Pay_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Entitlement Unit Pay, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	

				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPay)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayAllTypes)).click(); Thread.sleep(1000);
				int noOfEntitlementsUnitPaysToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Unit Pays randomly
				System.out.println("Totals Entitlement Unit Pays given are: "+noOfEntitlementsUnitPaysToAdd);
				for(int i=1; i<=noOfEntitlementsUnitPaysToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.entitlementsDropDown);
					if(i==1) {
							String entitlementsUnitPayUnits=String.valueOf(ThreadLocalRandom.current().nextInt(2, 6));
							String entitlementsUnitPayRateOverride=String.valueOf(ThreadLocalRandom.current().nextInt(10, 15));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayUnits)).sendKeys(entitlementsUnitPayUnits);
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayRateOverride)).sendKeys(entitlementsUnitPayRateOverride);
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
							System.out.println("	"+itemSelected+" > Entitlement Unit Pay Units:"+entitlementsUnitPayUnits+" Entitlement Unit Pay Rate Override:"+entitlementsUnitPayRateOverride);
							commonMethods.successErrorMesssage(driver);
						}
						else {
							String entitlementsUnitPayUnits=String.valueOf(ThreadLocalRandom.current().nextInt(2, 6));
							String entitlementsUnitPayFinalAmountOverride=String.valueOf(ThreadLocalRandom.current().nextInt(10, 15));
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayUnits)).sendKeys(entitlementsUnitPayUnits);
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.entitlementsUnitPayFinalAmountOverride)).sendKeys(entitlementsUnitPayFinalAmountOverride);
							driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
							System.out.println("	"+itemSelected+" > Entitlement Unit Pay Units:"+entitlementsUnitPayUnits+" Entitlement Unit Pay Final Amount Override:"+entitlementsUnitPayFinalAmountOverride);
							commonMethods.successErrorMesssage(driver);
						}
						
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
						commonMethods.takeScreenShot(driver, "Entitlements Unit Pay Added");
					}
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
				}
			}	
		}

	
	@Test
	public void addNotionalAmountsPermRandom() throws IOException, AWTException, InterruptedException {
		
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Notional Amounts Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Notional Amounts Perm_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Notional Amounts Perm, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmounts)).click();
				int noOfNotionalAmountsPermsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Perms randomly
				System.out.println("Totals Notional Amounts Perms given are: "+noOfNotionalAmountsPermsToAdd);
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountPermButton)).click(); Thread.sleep(500);
				for(int i=1; i<=noOfNotionalAmountsPermsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.notionalAmountDropDown);
					if(i==1) {
						String startPeriod=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountStartPeriod+"/option[2]")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountStartPeriod, startPeriod);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountEndPeriod, startPeriod);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDetailsButton)).click();
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Start Period:"+startPeriod+"	Effective To:"+startPeriod+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else if (i==2){
						String startPeriod=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountStartPeriod+"/option[2]")).getText();
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountStartPeriod, startPeriod);
						commonMethods.selectFromListPartialText(driver, employeeDetailsEPAPageObjects.notionalAmountEndPeriod, "indefinite");
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDetailsButton)).click();
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Effective From:"+startPeriod+"	Effective To: Indefinite	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}else {
						String startPeriod=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountStartPeriod+"/option[2]")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountStartPeriod, startPeriod);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountEndPeriod, startPeriod);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountEndPeriod)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountEndPeriod)).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDetailsButton)).click();
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						String effectiveTo=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountRecentSavedEffectiveTo+"//option[text()='"+startPeriod+"']/following-sibling::option[2]")).getText();
						System.out.println("	"+itemSelected+" > Effective From:"+startPeriod+"	Effective To:"+effectiveTo+"	Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Notional Amounts Perm Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
		}
	}	

	
	@Test
	public void addNotionalAmountsTempRandom() throws IOException, AWTException, InterruptedException {
		
		employeeSearchPageObjects.isEmployeeExists(driver, testInputGPMS.employeeNo);
			driver.findElement(By.xpath(employeeSearchPageObjects.employeeToBeSelected(testInputGPMS.employeeNo))).click();
			if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()==0) {
				System.out.println("Failed: Cant add Notional Amounts Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
				commonMethods.takeScreenShot(driver, "Failed adding Notional Amounts Temp_Emp No dont have any Current Actice Payroll Assignments");
				Assert.fail("Failed: Cant add Notional Amounts Temp, as  Employee Number'"+ testInputGPMS.employeeNo +"' don't have any Current Actice Payroll Assignments");
			}else {	
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.actionButton)).click();
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmounts)).click();
				int noOfNotionalAmountsTempsToAdd=ThreadLocalRandom.current().nextInt(1, 3);	//Randomly picks either 1, 2 or 3 and will add that many Entitlement Perms randomly
				System.out.println("Totals Notional Amounts Temps given are: "+noOfNotionalAmountsTempsToAdd);
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountTempButton)).click(); Thread.sleep(500);
				for(int i=1; i<=noOfNotionalAmountsTempsToAdd; i++) {
					String itemSelected=commonMethods.selectRandomFromList(driver, employeeDetailsEPAPageObjects.notionalAmountDropDown);
					if(i==1) {
						String startPeriod=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountStartPeriod+"/option[2]")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountStartPeriod, startPeriod);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDetailsButton)).click();
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Start Period:"+startPeriod+" Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					}
					else {
						String startPeriod=driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountStartPeriod+"/option[3]")).getText();Thread.sleep(1000);
						commonMethods.selectFromListExactText(driver, employeeDetailsEPAPageObjects.notionalAmountStartPeriod, startPeriod);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountDetailsButton)).click();
						String amount=String.valueOf(ThreadLocalRandom.current().nextInt(100, 200));
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.notionalAmountAmount)).sendKeys(amount);
						driver.findElement(By.xpath(employeeDetailsEPAPageObjects.save)).click();Thread.sleep(1000);
						System.out.println("	"+itemSelected+" > Start Period:"+startPeriod+" Amount:"+amount);
						commonMethods.successErrorMesssage(driver);
					
					driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
					commonMethods.takeScreenShot(driver, "Notional Amounts Temp Added");
				}
				driver.findElement(By.xpath(employeeDetailsEPAPageObjects.backToButton)).click();
		}
			}	
	}
	
}
