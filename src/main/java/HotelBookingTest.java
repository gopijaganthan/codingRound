import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import library.Driver;
import pages.ClearTrip;
import pages.Hotel;

public class HotelBookingTest {
    WebDriver driver;
    ClearTrip clearTrip = new ClearTrip();
    Hotel hotel = new Hotel();
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
    
    @Test
    public void shouldBeAbleToSearchForHotels() {
        clearTrip.navigateTo("Hotels");
        hotel.enterLocality("Indiranagar, Bangalore");
        hotel.travellerSelection("1 room, 1 adult");
        hotel.clickSearch();
    }

    @AfterClass
    public void afterAllTest() {
    		Driver.quitAllDrivers();
    }
    
}
