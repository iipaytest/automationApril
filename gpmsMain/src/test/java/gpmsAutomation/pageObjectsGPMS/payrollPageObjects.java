package gpmsAutomation.pageObjectsGPMS;

import org.openqa.selenium.WebDriver;

public class payrollPageObjects {
	
	
	
	public static final String payrollPageHeader="//*[@id='iipHeader_title']";
	
	public static final String payrollActions="//a[@id='btnActions_button']";
	public static final String payrollActionsList="//table[@id='payrollActionsDialog_contentTable']//a";
		public static final String deletePayroll="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Delete')]";
		public static final String editProperties="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Edit Properties')]";
		public static final String editPeriods="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Edit Periods')]";
		public static final String permissions="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Permissions')]";
		public static final String selfServicePayslip="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Self-Service Payslip Visibility')]";
		
		public static final String processingQueue="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Processing Queue')]";
		public static final String changeModeToInactive="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Change Mode to Inactive')]";
		public static final String changeModeToNonStandard="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Change Mode to Non-Standard')]";
		public static final String changeModeToObsolete="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Change Mode to Obsolete')]";
		
		public static final String exchangeRates="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Exchange Rates')]";
		public static final String reportPackages="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Report Packages')]";
		public static final String ersContributions="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Ers Contributions')]";
		public static final String companyBankAccount="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Company Bank Account')]";
		public static final String payrollReportLogo="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Payroll Report Logo')]";
		public static final String configurableChangesDataExport="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Configurable Changes Data Export')]";
		public static final String paySummaryNarrativeTranslations="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Pay Summary Narrative Translations')]";
		public static final String payslipOptions="//table[@id='payrollActionsDialog_contentTable']//*[contains(text(),'Payslip Options')]";
		
		public static final String payrollActionsClose="//*[contains(@id,'payrollActionsDialog_close')]";
		
	public static final String payrollName="//*[@id='lblPayrollNameValue']";
	public static final String ruleset="//*[@id='lblRulesetValue']";
	public static final String currency="//*[@id='iipHeader_pageDataText3']";
	public static final String companyName="//*[@id='lblCompanyNameValue']";
	public static final String payFrequency="//*[@id='lblPayFrequencyValue']";
	public static final String payrollMode="//*[@id='lblPayrollModeValue']";
	
	public static final String periodActions(WebDriver driver, String periodName) {
		String periodActions="//*[@id='dgPeriodData']//*[contains(text(), '"+periodName+"')]/preceding-sibling::td[1]";
		return periodActions;
	}
	
	public static final String reportsAccrualAIP="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Accrual (AIP)')]";
	public static final String reportsAccrualESPP="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Accrual (ESPP)')]";
	public static final String reportsChangesExport="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Changes Export Report')]";
	public static final String reportsConfigChangeDataExport="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Config Change Data Export Report')]";
	public static final String reportsConfigEBMultiPeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Config EB Multi Period')]";
	public static final String reportsConfigEBSinglePeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Config EB Single Period')]";
	public static final String reportsConfigurablePaymentSinglePeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Configurable Payment Report (single period)')]";
	public static final String reportsElementBreakdownMultiPeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Element Breakdown Report (multi-period)')]";
	public static final String reportsElementBreakdownSinglePeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Element Breakdown Report (single-period)')]";
	public static final String reportsEmployeeDataUploadTemplate="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Employee Data Upload Template')]";
	public static final String reportsETypePercentageChangeMultiPeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'EType Percentage Change Report (multi-period)')]";
	public static final String reportsGLCodingMultiPeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'GL Coding Report (multi-period)')]";
	public static final String reportsGLCodingSinglePeriod="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'GL Coding Report (single-period)')]";
	public static final String reportsGTNChanges="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'GTN Changes Report')]";
	public static final String reportsPayStatement="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Pay Statement Report')]";
	public static final String reportsProcessChecking="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Process Checking Report')]";
	public static final String reportsSingleEmployeePayStatement="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Single Employee Pay Statement Report')]";
	public static final String reportsUnitSummary="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'Unit Summary Report')]";
	public static final String reportsYTDBreakdown="//*[@id='periodActionsDialog_ctl00_pnlReports']//*[contains(text(),'YTD Breakdown Report')]";
	
	public static final String uploadsEmployeeUpload="//*[@id='periodActionsDialog_ctl00_pnlUploads']//*[contains(text(),'Employee Upload')]";
	public static final String uploadsEEUploadWithReformatter="//*[@id='periodActionsDialog_ctl00_pnlUploads']//*[contains(text(),'EE Upload with Reformatter')]";
		
}
