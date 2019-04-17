package gpmsAutomation.pageObjectsGPMS;



public class reportsPageObjects {
	
	public static final String availableReports="//select[@id='ddReports_DropDown']";
	public static final String payroll="//select[contains(@id,'payroll_DropDown')]";
	public static final String period="//select[contains(@id,'period_DropDown')]";
	public static final String layoutl="//select[contains(@id,'layout_DropDown')]";
	public static final String omitBlankSheets="//*[contains(text(),'Omit Blank Sheets')]/preceding-sibling::input[1]";
	
	
	
	public static final String submit="//*[@id='btnSubmit']";
	
	public static final String completed="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[5]";
	public static final String refresh="//*[@id='btnRefresh']";
	public static final String requestDetailsButton="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[4]/img";
	public static final String requestDetailsDetails="//*[@id='QueueList_requestCtl_0_tblMain']//tr[2]//table[1]";
	public static final String downloadReportToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'local file')]";
}
