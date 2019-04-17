package gpmsAutomation.pageObjectsGPMS;



public class employeeDetailsEPAPageObjects {
	public static final String actionButton="//*[@id='pnlCurrentPayrolls']//input[contains(@onclick, 'return loadEpaActions')]";
	
	
	public static final String lock="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Lock')]";
	public static final String reset="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Reset')]";
	public static final String unconfirm="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Unconfirm')]";
	public static final String refresh="//*[@id='lblActionSent']/a";
	
	
	public static final String editPayrollAssignment="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Edit Payroll Assignment')]";
		public static final String payrollStartDate="//*[@id='calStartDate_txtDate']";
		public static final String actualStartDate="//*[@id='calJoinDate_txtDate']";
		public static final String payrollEndDate="//*[@id='calEndDate_txtDate']";
		public static final String actualEndDate="//*[@id='calLeaveDate_txtDate']";
		public static final String save="//*[@id='btnSave']";
		public static final String delete="//*[@id='btnDelete']";
	
	public static final String employeePayrollDetails="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Employee Payroll Details')]";
		public static final String dropDownEmployeePayrollDetails="//div[@id='ddNew_Panel']/select";
	
	public static final String leaveAbsence="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Leave / Absence')]";
		public static final String dropDownLeaveAbsence="//div[@id='ddNavigateToSet_Panel']/select";	
	
	public static final String payments="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Payments')]";
		public static final String dropDownPayments="//div[@id='ddNavigateToSet_Panel']/select";
		//public static final String dropDownPaymentsList="//div[@id='ddNavigateToSet_Panel']/select/option";
		
		public static final String paymentEffectiveFrom="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl01_ctl00_Panel']/select";
		public static final String paymentEffectiveTo="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl02_ctl00_Panel']/select";
		public static final String paymentAmount="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl03_param']";
		
	public static final String deductions="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Deductions')]";
		public static final String dropDownDeductions="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String unitPay="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Unit Pay')]";
		public static final String dropDownUnitPay="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String notionalAmounts="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Notional Amounts')]";
		public static final String dropDownNotionalAmount="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String entitlementsTemp="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Temp)')]";
		public static final String dropDownEntitlementsTemp="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String entitlementsPerm="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Perm)')]";
		public static final String dropDownEntitlementsPerm="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String entitlementsUnitPay="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Unit Pay)')]";
		public static final String dropDownEntitlementsUnitPay="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String incumbentDetails="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Incumbent Details')]";
		public static final String dropDownIncumbentDetails="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String tools="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Tools')]";
		public static final String dropDownTools="//div[@id='ddAvailableGofors_Panel']/select";
	
	
	public static final String backToEmployeeDetails="//*[@id='btnBack']//a[@id='btnBack_button']";
	
	
	
	
}
