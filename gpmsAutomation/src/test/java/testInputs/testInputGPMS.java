package testInputs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testInputGPMS {	
	
	//Variable for local use: Note-don't change these details
		static Date date = new Date(); 											// Current System Date and time is assigned to objDate
		static String strDateFormat = "MMMdd"; 									//Date format is Specified
		static SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 	//Date format string is passed as an argument to the Date format object
	
	//Locations to external files
		public static final String gpmsFileLocation 	="C:\\Users\\sribur19\\Box\\SriTestFolder\\Automation\\TestApril15\\";	//location to save all GPMS Test outputs
		public static final String gpmsTestInputsLocation 	=System.getProperty("user.dir")+"\\testInputs\\";
		public static final String gpmsTestOutputsLocation 	=System.getProperty("user.dir")+"\\testOutputs\\";
	
	//TST3, Client ID, Login credentials
		public static final String urlTST3redirector 	="https://tst3-redirector.iipaysp.com";
		public static final String clientID				="AUTO";
		public static final String userName				="srisainaveen";
		public static final String password				="Test@2019";
		public static final String versionNo			="04.08.20";	//v020200-tc2_#16 / 04.08.00
		public static final String versionTC			="tc3_#20";		//v020200-tc2_#16 / 04.08.00
	
	
	//Create new Employee 
		public static final String title 		="Mr";
		public static final String forename		="Fore1";
		public static final String surname		="Sur1";
		public static final String employeeNo	="TestMay16";			//+objSDF.format(date);
		public static final String startDate 	="04/04/2019";		//will be given for Payroll Start Date, Actual Start Date
		public static final String dOB			="03/03/1990";
		public static final String gender		="Male";
		public static final String addressRef	="Payroll";
		public static final String addressLine1	="Add Line 1";
		public static final String postalTown	="Cheltenham";
		public static final String country		="UK";
		public static final String endDate 		="04/04/2019";		//will be given for Payroll End Date, Actual End Date
		public static final String paymentType	="International";	//Payment Information (Configurable) info for creating payroll assignment
		public static final String splitType	="Remainder";		//Payment Information (Configurable) info for creating payroll assignment
	
	//Create new Payroll
		public static final String rulesetCountry	="Germany";
		public static final String rulesetCurrency	="EUR";
		public static final String payrollName		="Test Payroll May16"; 	//+objSDF.format(date);	//payroll name will be given with todays date, 'Test Payroll Apr04'
		public static final String companyName		=null;					//leave this null if you want to add newCompanyName created above to this payroll
		public static final String payFrequency		="Monthly";				//full name of pay frequency
		//Payroll periods to generate
			public static final String payrollYearPeriod	="2019";
			public static final Boolean payDaybutton		=true;		//to select payOffset, given payDay=false
			public static final String payDayToSelect		="Monday";
			public static final String payOffsetToGive		="2";
			public static final Boolean payDayFirst			=false;		//to select Last, give payDayFirst=false
			public static final Boolean payOffsetAfter		=true;		//to select payOffsetBefore, give payOffsetAfter=false
			public static final String bankTransferOffset	="2";
			
					
		
	//Create new Company
		public static final String newCompanyName	="Test Company";
		public static final String companyAddressLine1="Festive House";
		public static final String companyAddressLine2="";
		public static final String companyAddressLine3="";
		public static final String companyPostalTown="Cheltenham";
		public static final String companyCountyDistrict="";
		public static final String companyPostCode="";
		public static final String companyCountry="UK";
	
	//Create new Org Unit
		public static final String newOrgUnit		="TestOrg";
		public static final String[][] newNamesReferences		= {{"Org1", "Ref1"}, {"Org2", "Ref2"}};
	
	//Create new Element Type
		public static final String newElementTypeName			="TestEType";
		public static final String newElementTypeDescription	=null;
		public static final String newElementTypeFromParty		="Employee";	//Employee, Employer, Other
		public static final String newElementTypeToParty		="Employer";	//Employee, Employer, Other
		public static final String newElementTypeConsolidation	=null;
		public static final Boolean newElementTypeNegate		=false;
		public static final Boolean newElementTypeObsolete		=false;
		
		
	
			
	//Required Pay period in a payroll to details of specific previous or future pay periods
			public static final String requiredPayrollName		=null;	//"Germany";		//leave it null for active, else give specific payroll Name>	"Test Payroll Apr10";
			public static final String requiredPayPeriodTaxYear	=null;	//"2017";			//leave it null for active, else give specific Tax year>		"2019";
			public static final String requiredPayPeriodNo		=null;	//"6";				//leave it null for active, else give specific Period No>		"2";	(1-12 numbers)
			
	
	//EPA details to add
		public static final String[] payDedsToAdd= {"Payments", "Deductions"};
		
		public static final String[][] paymentsTypesToAdd= {{"Salary (Net)", "P4 2018", "P4 2018", "10"},{"13th salary", "P4 2018", "P4 2018", "20"}};			//{Payment EType, Effective From, Effective To, Amount}
		public static final String[][] deductionsTypesToAdd= {{"Benefit in Kind", "P4 2018", "P4 2018", "10"},{"Care of child", "P4 2018", "P4 2018", "20"}};	//{Payment EType, Effective From, Effective To, Amount}
		
		
		public static final String[][] unitPaysTypesToAdd= {};
		
		//public static final String[] deductionsTypesToAdd= {"Benefit in Kind", "Care of child"};
			public static final int[] deductionsAmountsToAdd= {2020, 1010};
		
		public static final String[] unitPaysToAdd= {};
		public static final String[] entitlementsTempToAdd= {};
		public static final String[] entitlementsPermToAdd= {};
		public static final String[] entitlementsUnitPayToAdd= {};
	
	//EPA Details to Add
		public static final String elementTypeEPA	="Salary (ICP)";
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
