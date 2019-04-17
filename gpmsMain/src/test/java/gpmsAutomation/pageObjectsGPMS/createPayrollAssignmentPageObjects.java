package gpmsAutomation.pageObjectsGPMS;


public class createPayrollAssignmentPageObjects {

	public static final String backToEmployeeDetails="//a[@id='btnBack_button']";
	public static final String payroll="//select[@id='ddPayrolls_DropDown']";
	//public static final String payrollOptions="//select[@id='ddPayrolls_DropDown']/option";
	public static final String payrollStartDate="//input[@id='calStartDate_txtDate']";
	public static final String payrollEndDate="//input[@id='calEndDate_txtDate']";
	public static final String actualStartDate="//input[@id='calJoinDate_txtDate']";
	public static final String actualEndDate="//input[@id='calLeaveDate_txtDate']";
	
	public static final String historicLeaver="//input[@id='cbLeaverBeforeTakeOn']";
	
	
	public static final String employeeAddress="//span[contains(text(), 'Employee Address')]/parent::div/following-sibling::div//select";
	//public static final String employeeAddressOptions="//span[contains(text(), 'Employee Address')]/parent::div/following-sibling::div//select/option";
	public static final String paymentType="//span[contains(text(), 'Payment Type')]/parent::div/following-sibling::div//select";
	//public static final String paymentTypeOptions="//span[contains(text(), 'Payment Type')]/parent::div/following-sibling::div//select/option";
	public static final String splitType="//span[contains(text(), 'Split Type')]/parent::div/following-sibling::div//select";
	//public static final String splitTypeOptions="//span[contains(text(), 'Split Type')]/parent::div/following-sibling::div//select/option";
	
	public static final String save="//input[@id='btnSave']";
	public static final String delete="//input[@id='btnDelete']";
	
	public static final String errorMessage1="//*[@id='lblError']";
	public static final String errorMessage2="//*[@id='errorSummary']";
	public static final String successMessage="//span[@id='lblSuccess']";
	public static final String employeePayrollDetails="//input[@id='btnPayrollDetails']";
	
	
	
}
