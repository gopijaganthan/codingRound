package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.jna.Platform;

public class Driver{
	private static WebDriver driver;
	private static ArrayList<WebDriver> allDrivers  = new ArrayList<WebDriver>(); 
	private Integer maxWaitTimeoutInSeconds = 15;
	
	private Driver() {
	}
	
	//This will create a driver return the driver based on the parameter'browserType'
	private static WebDriver Driver(String browserType) {
		setDriverPath();
		switch(browserType) {
		case "chrome":
			//to disable notifications on chrome driver
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver =  new ChromeDriver(options);
			
			//set implicit time out for 5 seconds
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			break;
		case "firefox":
			break;
		case "IE":
			break;
			}
		allDrivers.add(driver);
		return driver;
	}
	
	
	//This will set the driver in case of multiple drivers launched in feature
	public static WebDriver setDriver(WebDriver driver_to_set) {
		driver = driver_to_set;
		return driver;
	}
	
	//This will get the current driver
	public static WebDriver getDriver() {
		if ( allDrivers.isEmpty()) {
			driver = Driver("chrome");
        }
		return driver;
	}
	
	public static void navigateTo(String url) {
		driver.get(url);
	}
	
	//This function will wait for the page to load till the time specified 
    public static void waitForPageToLoad(Integer timeoutInSeconds) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeoutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    
    public static void waitForPageToLoad() {
    		waitForPageToLoad(15);
    }
    
    private static void setDriverPath() {
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
    
    public static void quitAllDrivers() {
    		allDrivers.forEach((driver) -> driver.quit());
    }
    
}
