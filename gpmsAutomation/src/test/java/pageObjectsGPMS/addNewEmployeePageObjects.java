package pageObjectsGPMS;



public class addNewEmployeePageObjects {

	
	public static final String title="//input[@id='txtTitle']";
	public static final String forename="//input[@id='txtFirstName']";
	public static final String surname="//input[@id='txtLastName']";
	public static final String employeeNo="//input[@id='txtEmployeeNumber']";
	public static final String startDate="//input[@id='calStartDate_txtDate']";
	public static final String dOB="//input[@id='calDOB_txtDate']";
	public static final String gender="//select[@id='lstGender_DropDown']";
	
	public static final String addressRef="//input[@id='empAddControl_txtReference']";
	public static final String addressLine1="//input[@id='empAddControl_txtAddress1']";
	public static final String postalTown="//input[@id='empAddControl_txtTown']";
	public static final String country="//input[@id='empAddControl_txtCountry']";
	
	public static final String save="//input[@id='btnSave']";
	public static final String cancel="//input[@id='btnCancel']";
	
	public static final String errorMessage="//*[@id='lblError']";
	public static final String errorBasicDetails="//*[contains(text(), 'Basic Details')]/following-sibling::*//span[contains(@class, 'red')]";
	public static final String errorAddress="//*[contains(text(), 'Address')]/following-sibling::*//span[contains(@class, 'red')]/parent::td/preceding-sibling::td[2]";
	
	
}
