
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.Flight;
import library.Driver;

import com.sun.jna.Platform;

import java.util.List;

public class FlightBookingTest {

    WebDriver driver;
    Integer maxWaitTimeoutInSeconds = 15;
    String baseUrl = "https://www.cleartrip.com/";
    Flight flightPage = new Flight();
    @BeforeClass
    public void oneTimeSetUp() {
    		driver = Driver.getDriver();
    		
    }
    
    @BeforeTest
    public void eachTimeSetUp() {
    		Driver.navigateTo(baseUrl);
        Driver.waitForPageToLoad();
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        //Action
        flightPage.clickOneWay();
        flightPage.enterFrom("Bangalore");
        flightPage.enterTo("Delhi");
        flightPage.setDefaultDate();
        flightPage.clickSearch();
        
        //Verification
        Assert.assertTrue(flightPage.isSearchSummaryPresent());
    }
    
    @AfterClass
    public void afterAllTest() {
    		Driver.quitAllDrivers();
    }
}
