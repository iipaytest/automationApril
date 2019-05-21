package pageObjectsGPMS;

public class loginPageObjects {
	
	public static final String clientID="//*[@id='ClientId']";
	public static final String submit="//*[@type='submit']";
	
	public static final String userName="//*[@id='Username']";
	public static final String password="//*[@id='Password']";
	public static final String login="//*[@type='submit']";
	
	public static final String forgottenPassword="//*[text()='Forgotten Password?']";
	public static final String requestResetCode="//*[text()='Request Reset Code']";
	public static final String emailAddress="//*[@id='EmailAddress']";
	public static final String userID="//*[@id='UserName']";
	public static final String warning="//*[text()='Unable to verify reCaptcha']";
	public static final String warningElement="//*[@class='ibox-content']";
	
	
	public static final String version="//img[@class='loginProviderImage']/parent::p/following-sibling::p[1]";
	public static final String versionElement="//*[@class='row'][1]";
	
	
}
