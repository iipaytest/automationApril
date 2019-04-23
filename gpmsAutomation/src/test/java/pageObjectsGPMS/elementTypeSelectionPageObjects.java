package pageObjectsGPMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class elementTypeSelectionPageObjects {

	public static final String searchByName="//*[@id='txtSearch']";
	public static final String searchByConsolidation ="//*[@id='txtConsolidationSearch']";
	public static final String searchButton="//*[@id='btnSearch']";
	
	public static final String showObsoleteCheckBox="//*[@id='chkShowObsolete']";
	public static final String addNewElement="//*[@id='btnNew']";
	public static final String tableList="//*[@id='eTypeList_itemsHolder']/div";
	
	//New Element xpaths
		//Basic Details	
			public static final String nameElementType="//*[@id='txtETypeName']";
			public static final String descriptionElementType="//*[@id='txtDescription']";
			public static final String fromParty="//*[@id='ddFromParty_DropDown']";
			public static final String toParty="//*[@id='ddToParty_DropDown']";
			public static final String consolidationETypeName ="//*[@id='txtConsolidationETypeName']";
			public static final String negateCheckBox="//*[@id='chkNegate']";
			public static final String obsoleteCheckBox="//*[@id='chkObsolete']";
			
		//Unit Type
			public static final String multipleRateEnabledCheckBox="//*[@id='ctl11_chkMultipleRate']";
				public static final String multipleRateValue="//*[@id='ctl11_txtMultipleRateValue']";
				public static final String multipleRateUnitType="//*[@id='ctl11_ddMultipleRateUnitType_DropDown']";
			public static final String fixedRateEnabledCheckBox="//*[@id='ctl11_chkFixedRate']";
				public static final String fixedRateValue="//*[@id='ctl11_txtFixedRateValue']";
			
				public static final String backToElementList="//*[@id='btnBack_button']";
				public static final String save="//*[@id='btnSave']";
				
				public static final String sucessful="//span[@id='lblSuccess']";
				public static final String error="//span[@id='lblError']";
				public static final String errorSummary="//div[@id='errorSummary']";
				public static final String warning="//span[@id='lblWarning']";
		
	public static void isElementTypePresent(WebDriver driver, String elementTypeName) throws InterruptedException {
		int j=driver.findElements(By.xpath(tableList)).size();
		driver.findElement(By.xpath(searchByName)).sendKeys(elementTypeName);
		driver.findElement(By.xpath(searchButton)).click();
		Thread.sleep(10000);
		int y=driver.findElements(By.xpath(tableList)).size();
		int listVailable=driver.findElements(By.xpath(tableList+"//*[contains(@id, 'eTypeList_i')]/div[contains(text(), '')]")).size();
		System.out.println(j+" - "+y);
		System.out.println(listVailable);
	}
	
	
}
