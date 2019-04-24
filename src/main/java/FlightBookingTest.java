
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
import org.testng.annotations.Test;

import com.sun.jna.Platform;

import java.util.List;

public class FlightBookingTest {

    WebDriver driver = new ChromeDriver();
    Integer maxWaitTimeoutInSeconds = 15;
    String baseUrl = "https://www.cleartrip.com/";


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        setDriverPath();
        driver.get(baseUrl);
        waitForPageLoaded();
        
        waitForElement(By.id("OneWay"));
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin
        waitForElement(By.id("ui-id-1"));
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination
        waitForElement(By.id("ui-id-2"));
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td[contains(@class,'selected')]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        
        //verify that result appears for the provided journey search
        waitForElement(By.className("searchSummary"));
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }
    
    //This function will wait for the page to load till the time specified 
    public void waitForPageLoaded(Integer timeoutInSeconds) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    
    //This function will wait for the page to load till default time
    public void waitForPageLoaded() {
    		waitForPageLoaded(maxWaitTimeoutInSeconds);
    }
    
	public boolean waitForElement(By by, Integer timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		//to nullify exceptions
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch(Exception e){
		}
		return isElementPresent(by);
	}
	
	//[overloading] - to make 'timeoutInSeconds' as optional parameter
	public boolean waitForElement(By by)
	{
		return waitForElement(by, maxWaitTimeoutInSeconds);
	}

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (Platform.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (Platform.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (Platform.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
