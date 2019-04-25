package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BasePage;
import library.Driver;

public class SignIn extends BasePage {
	public SignIn() {
		PageFactory.initElements(getCurrentDriver(), this);
	}
	
	@FindBy(linkText = "Your trips")
    private WebElement yourTrips;
	
	@FindBy(id = "SignIn")
    private WebElement signIn;
	
	@FindBy(id = "signInButton")
    private WebElement signInButton;
	
	@FindBy(id="email")
    private WebElement email;
	
	@FindBy(id = "password")
    private WebElement password;
	
	@FindBy(css = "#modal_window")
    private WebElement signInFrame;
	
	@FindBy(id = "errors1")
    private WebElement error;
	
	public void switchToSignInFrame()
	{
		if(signInFrame.isDisplayed())
		{
			Driver.getDriver().switchTo().frame("modal_window");
		}
	}
	
	public void clickYourTrips() {
		yourTrips.click();
	}
	
	public void clickSignIn() {
		signIn.click();
	}
    	
	public void clickLogin() {
		signInButton.click();
	}
	
	public void enterUserName(String userName) {
		email.sendKeys(userName);	
	}
	
	public void enterPassword(String passwordToEnter) {
		password.sendKeys(passwordToEnter);	
	}
	
	public void loginIntoApplication(String username, String Password) {
		switchToSignInFrame();
		enterUserName(username);
		enterUserName(Password);
		clickLogin();
	}
	
	public String getErrors() {
		return error.getText();	
	}    
}
