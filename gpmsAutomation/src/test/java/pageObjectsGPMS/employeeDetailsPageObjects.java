package pageObjectsGPMS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import reusableMethods.commonMethods;
import testInputs.testInputGPMS;

public class employeeDetailsPageObjects {
	
	public static final String backToSearchResults="//input[@id='btnBack_image']";
	public static final String pageHeader="//*[@id='iipHeader_title']";
	
	public static final String basicDetails="//td[contains(text(), 'Basic Details')]";
		public static final String title="//input[@id='txtTitle']";
		public static final String forename="//input[@id='txtForenames']";
		public static final String surname="//input[@id='txtSurname']";
		public static final String employeeNo="//input[@id='txtEmpNo']";
		public static final String startDate="//input[@id='calStartDate_txtDate']";
		public static final String dateOfBirth="//input[@id='calDOB_txtDate']";
		public static final String gender="//select[@id='ddGender_DropDown']";
		
		public static final String email="//input[@id='txtEmail']";
		public static final String endDate="//input[@id='calEndDate_txtDate']";
		public static final String knownAs="//input[@id='txtKnownAs']";
		public static final String telephone="//input[@id='txtTelNo']";
		public static final String extension="//input[@id='txtExt']";
		public static final String fax="//input[@id='txtFax']";
		public static final String securityGroup="//select[@id='ddSecurityGroup_DropDown']";
		public static final String deceased="//input[@id='chkDeceased']";
	
	
	public static final String addresses="//td[contains(text(), 'Addresses')]";
		
		public static final String hideObsolete="//input[@id='chkHideObsoleteAddresses']";
		public static final String newAddress="//a[@id='btnNewAddress']";
	
		public static final String new_currentAddress_1="//div[@class='empPanel'][1]//tr[1]/td[1]/img[2]";
			public static final String addressRef_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String addressLine1_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String addressLine2_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String addressLine3_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String postalTown_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String county_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String postCode_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			public static final String country_1="//div[@class='empPanel'][1]//tr[1]/td[@class='addressText']";
			
		public static final String oldAddress_2="//div[@class='empPanel'][2]//tr[1]/td[1]/img[2]";
			public static final String addressRef_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String addressLine1_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String addressLine2_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String addressLine3_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String postalTown_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String county_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String postCode_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
			public static final String country_2="//div[@class='empPanel'][2]//tr[1]/td[@class='addressText']";
		
		public static final String oldAddress_3="//div[@class='empPanel'][3]//tr[1]/td[1]/img[2]";
			public static final String addressRef_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String addressLine1_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String addressLine2_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String addressLine3_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String postalTown_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String county_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String postCode_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
			public static final String country_3="//div[@class='empPanel'][3]//tr[1]/td[@class='addressText']";
		
	public static final String directLoginSelfService="//td[contains(text(), 'Direct Login Self Service ')]";
		public static final String confirmEmailAddress="//input[@id='txtSSEmail']";
		public static final String registerEmployee="//input[@id='btnSelfService']";
	
	public static final String save="//input[@id='btnSave']";
	public static final String reset="//input[@id='btnReset']";
	public static final String delete="//input[@id='btnDelete']";
	public static final String viewPayHistory="//input[@id='btnPayHistory']";
	public static final String createPayrollAssignment="//input[@id='btnNewEPA']";
	
	public static final String currentPayrollAssginment_1="//div[@id='pnlCurrentPayrolls']//tr[2]//a";
	public static final String currentPayrollAssginment_2="//div[@id='pnlCurrentPayrolls']//tr[3]//a";
	public static final String currentPayrollAssginment_3="//div[@id='pnlCurrentPayrolls']//tr[4]//a";
	
	public static final String previousPayrollAssginment_1="//div[@id='pnlPreviousPayrolls']//tr[2]//a";
	public static final String previousPayrollAssginment_2="//div[@id='pnlPreviousPayrolls']//tr[3]//a";
	public static final String previousPayrollAssginment_3="//div[@id='pnlPreviousPayrolls']//tr[4]//a";
	
	public static Boolean isCurrentAssignmentExists(WebDriver driver) throws InterruptedException, AWTException, IOException {		
		Boolean passed=false;
		
		if(driver.findElements(By.xpath(employeeDetailsPageObjects.currentPayrollAssginment_1)).size()!=0) {
			passed=true;
		}else {
			System.out.println("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
			commonMethods.takeScreenShot(driver, "Failed verifying Reset Function from Employee level_Emp No dont have any Current Actice Payroll Assignments");
			Assert.fail("Failed: Cant verify Reset Function from Employee level, as  Employee Number'"+ testInputGPMS.emplyeeNo +"' don't have any Current Actice Payroll Assignments");
		}
		
		return passed;	
	}
	
	
}
