package pageObjectsGPMS;



public class employeeDetailsEPAPageObjects {
	public static final String actionButton="//*[@id='pnlCurrentPayrolls']//input[contains(@onclick, 'return loadEpaActions')]";
	public static final String actionButtonClose="//*[@id='actionsDialog_close']";
	
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
		public static final String detailsButton="//*[contains(@id, 'imgDetails')]";
		public static final String jobDetailsRowsCount="//*[contains(@class, 'JdJobDetailsPanel')]/div";
		public static final String payrollDetailConstraints="//*[text()='Payroll Detail Constraints:']/following-sibling::td[1]";
		public static final String payrollDetailType="//*[text()='Payroll Detail Type:']/following-sibling::td[1]";
		
	
	public static final String leaveAbsence="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Leave / Absence')]";
		public static final String dropDownLeaveAbsence="//div[@id='ddNavigateToSet_Panel']/select";	
	
	public static final String payments="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Payments')]";
	public static final String deductions="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Deductions')]";
		
		public static final String payDedsDropDown="//div[@id='ddNavigateToSet_Panel']/select";	
		public static final String payDedsEffectiveFrom="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl01_ctl00_Panel']/select";
		public static final String payDedsEffectiveTo="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl02_ctl00_Panel']/select";
		public static final String payDedsAmount="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl03_param']";
		public static final String payDedsRecentSavedEffectiveTo="//table[contains(@id, 'paymentSet_current')]//td[3]";
	
	
	public static final String unitPay="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Unit Pay')]";
		public static final String unitPayDropDown="//div[@id='ddNavigateToSet_Panel']/select";
		public static final String unitPayUnitTypes="//*[contains(text(), 'Unit Types Only')]/preceding-sibling::input";
		public static final String unitPayAllStandardTypes="//*[contains(text(), 'All Standard Types')]/preceding-sibling::input";
		public static final String unitPayAllTypes="//*[contains(text(), 'All Types Including')]/preceding-sibling::input";
		
		public static final String unitPayEffectiveFrom="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl01_ctl00_Panel']/select";
		public static final String unitPayUnits="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl02_param']";
		public static final String unitPayDateWorked="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl03_param_txtDate']";
		public static final String unitPayRateOverride="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl04_param']";
		public static final String unitPayFinalAmountOverride="//*[@id='paymentSet_new0']//*[@id='paymentSet_new0_ctl05_param']";
		
	
	public static final String entitlementsTemp="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Temp)')]";
	public static final String entitlementsPerm="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Perm)')]";
	public static final String entitlementsUnitPay="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Entitlements (Unit Pay)')]";
		
		public static final String entitlementsDropDown="//div[@id='ddNavigateToSet_Panel']/select";
		public static final String entitlementsEffectiveFrom="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl01_ctl00_Panel']/select";
		public static final String entitlementsEffectiveTo="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl02_ctl00_Panel']/select";
		
		public static final String entitlementsTempAmount="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl02_param']";
		public static final String entitlementsPermAmount="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl03_param']";
			
		public static final String entitlementsUnitPayUnitTypes="//*[contains(text(), 'Unit Types Only')]/preceding-sibling::input";
		public static final String entitlementsUnitPayAllStandardTypes="//*[contains(text(), 'All Standard Types')]/preceding-sibling::input";
		public static final String entitlementsUnitPayAllTypes="//*[contains(text(), 'All Types Including')]/preceding-sibling::input";
		
		public static final String entitlementsUnitPayEffectiveFrom="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl01_ctl00_Panel']/select";
		public static final String entitlementsUnitPayUnits="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl02_param']";
		public static final String entitlementsUnitPayDateWorked="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl03_param_txtDate']";
		public static final String entitlementsUnitPayRateOverride="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl04_param']";
		public static final String entitlementsUnitPayFinalAmountOverride="//*[@id='entitlementSet_new0']//*[@id='entitlementSet_new0_ctl05_param']";
		
		public static final String entitlementsPermRecentSavedEffectiveTo="//table[contains(@id, 'entitlementSet_current')]//td[3]";
		
	public static final String notionalAmounts="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Notional Amounts')]";
		public static final String notionalAmountDropDown="//div[@id='ddNavigateToSet_Panel']/select";
		public static final String notionalAmountTempButton="//*[@value='Temporary']";
		public static final String notionalAmountPermButton="//*[@value='Permanent']";
		public static final String notionalAmountStartPeriod="//*[@id='notionalSet_new0']//*[@id='notionalSet_new0_ctl01_ctl00_Panel']/select";
		public static final String notionalAmountEndPeriod="//*[@id='notionalSet_new0']//*[@id='notionalSet_new0_ctl02_ctl00_Panel']/select";
		public static final String notionalAmountDetailsButton="//*[@id='notionalSet_new0']//*[@id='notionalSet_new0_imgDetails']";
		public static final String notionalAmountAmount="//*[text()='Amount:']/following-sibling::td[1]/input";
		
		public static final String notionalAmountRecentSavedEffectiveTo="//table[contains(@id, 'notionalSet_current')]//td[3]";
	
	public static final String incumbentDetails="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Incumbent Details')]";
		public static final String incumbentDetailsDropDown="//div[@id='ddNavigateToSet_Panel']/select";
	
	public static final String tools="//*[@id='actionsDialog_contentTable']//a[contains(text(), 'Tools')]";
		public static final String dropDownTools="//div[@id='ddAvailableGofors_Panel']/select";
	
	
	public static final String backToButton="//*[@id='btnBack_button']";
	
	
	public static final String success="//span[@id='lblSuccess']";
	public static final String error="//span[@id='lblError']";
	public static final String errorsummary="//div[@id='errorSummary']";
	public static final String warning="//div[@id='warning']";
	
	public static String visibleText(String visibletext) {
		String text="//*[@id='actionsDialog_contentTable']//a[contains(text(), '"+visibletext+"')]";
		return text;
	}
	
}
