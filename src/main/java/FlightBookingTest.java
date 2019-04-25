
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.Flight;
import library.Driver;

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
        //performs search Actions
        flightPage.clickOneWay();
        flightPage.enterFrom("Bangalore");
        flightPage.enterTo("Delhi");
        flightPage.setDefaultDate();
        flightPage.clickSearch();
        
        //Verifying search summary page
        Assert.assertTrue(flightPage.isSearchSummaryPresent());
    }
    
    @AfterClass
    public void afterAllTest() {
    		Driver.quitAllDrivers();
    }
}
