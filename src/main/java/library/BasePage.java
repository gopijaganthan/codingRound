package library;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage{
    Integer maxWaitTimeoutInSeconds = 15;
	private By by;
	
	public BasePage() {
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
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeoutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    
    //This function will wait for the page to load till default time
    public void waitForPageToLoad() {
    		waitForPageLoaded(maxWaitTimeoutInSeconds);
    }
   
	public boolean waitForElement(By by, Integer timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeoutInSeconds);
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

    private WebElement findElement(By by) {
    		return Driver.getDriver().findElement(by);
    }

    private boolean isElementPresent(By by) {
        try {
        		findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    private void navigateTo(String url) {
    		Driver.getDriver().get(url);
    		waitForPageToLoad();
    }
    
    public void click(WebElement element) {
    		element.clear();
    }
    
    public void clear(WebElement element) {
    		element.clear();
    }
    
    public void sendKeys(By by, String textToEnter) {
    		findElement(by).sendKeys(textToEnter);
	}
    
    public WebDriver getCurrentDriver() {
    		return Driver.getDriver();
    }    
    
    public void select(WebElement selectElement, String valueToSelect) {
//    		new Select(this.sele).selectByVisibleText("1 room, 2 adults");
//		Driver.getDriver().findElement(this.by).select(valueToSelect);
	}
    
    
}