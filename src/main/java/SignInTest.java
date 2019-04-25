import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jna.Platform;

import library.Driver;
import pages.ClearTrip;
import pages.SignIn;;

public class SignInTest {
	WebDriver driver;
    ClearTrip clearTrip = new ClearTrip();
    SignIn signIn = new SignIn();
    String baseUrl = "https://www.cleartrip.com/";
    
    @BeforeClass
    public void oneTimeSetUp() {
    		driver = Driver.getDriver();
    		
    }
    
    @BeforeTest
    public void eachTimeSetUp() {
    		Driver.navigateTo(baseUrl);
        Driver.waitForPageToLoad();
    }
    
    @AfterClass
    public void afterAllTest() {
    		Driver.quitAllDrivers();
    }
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    		//Perform action
    		signIn.clickYourTrips();
    		signIn.clickSignIn();
    		signIn.loginIntoApplication("", "");
    		
    		//Error to check
    		Assert.assertTrue(signIn.getErrors().contains("There were errors in your submission"));
    }
}
