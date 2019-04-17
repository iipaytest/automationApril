package gpmsAutomation.pageObjectsGPMS;



public class payrollSearchPageObjects {
	public static final String payrollName="//input[@id='txtPayrollName']";
	public static final String resultsPerPage="//select[@id='lstResultsPerPage_DropDown']";
	public static final String search="//input[@id='btnSearch']";
	
	
	public static final String payrollClick(String payrollName) {	
		String payrollClick="//*[@id='dgResults_table']//div[contains(text(), '"+payrollName+"')]";
		return payrollClick;
	}
	
	public static final String employeeNo="//input[@id='txtEmployeeNumber']";
	public static final String startDate="//input[@id='calStartDate_txtDate']";
	public static final String dOB="//input[@id='calDOB_txtDate']";
	public static final String gender="//select[@id='lstGender_DropDown']";
}
