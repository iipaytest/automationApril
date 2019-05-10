package gpmsReports;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class reportsCommonMethods {

	public static WebDriver driver;
	
	public static WebDriver chromeDownloadPathChange(WebDriver driver) {
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\reportsDownloaded\\");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		WebDriver driver1 = new ChromeDriver(options);
		
		driver=driver1;	
		return driver;
	}
	
	public static Boolean checkFileDownloaded(WebDriver driver, String filePath, String fileName) {
		File folder = new File(filePath);
		Boolean present=false;
		//List the files on that folder
		File[] listOfFiles = folder.listFiles();
		
		File fileRequired = null;
		     //Look for the file in the files
		     for (File listOfFile : listOfFiles) {
		         if (listOfFile.isFile()) {
		              if (listOfFile.getName().contentEquals(fileName)) {
		            	   fileRequired = new File(listOfFile.getName());
		            	   present=true;
		                }
		            }
		        }
		return present;
	}
	
	
}
