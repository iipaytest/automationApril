package pageObjectsGPMS;



public class reportsPageObjects {
	
	public static final String availableReports="//select[@id='ddReports_DropDown']";
	public static final String payroll="//select[contains(@id,'payroll_DropDown')]";
	public static final String period="//select[contains(@id,'period_DropDown')]";
	public static final String layoutl="//select[contains(@id,'layout_DropDown')]";
	public static final String omitBlankSheets="//*[contains(text(),'Omit Blank Sheets')]/preceding-sibling::input[1]";
	
	
	
	public static final String submit="//*[@id='btnSubmit']";
	
	public static final String status="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[5]";
	public static final String typeReportName="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[3]";
	public static final String refresh="//*[@id='btnRefresh']";
	public static final String requestDetailsButton="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[4]/img";
	//public static final String requestDetailsDetails="//*[@id='QueueList_requestCtl_0_tblMain']//tr[2]//table[1]";
	public static final String requestDetailsTable="//*[@id='QueueList_requestCtl_0_tblMain']//*[@class='requestParameters']";
	public static final String runtimeDetailsToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download runtime summary details to local file system')]";
	public static final String runtimeDetailsSummary="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]/td[6]/img";
	public static final String outPutReportToLocalFile="//*[@id='QueueList_requestCtl_0_tblMain']//tr[1]//*[contains(@title, 'Download file to local file system')]";
	
	
	public static final String summaryWindowClose="//*[@id='processingMessages_close']";
	public static final String summaryWindowFrame="//*[@id='processingMessages_close']";
}
