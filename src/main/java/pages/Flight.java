package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import library.BasePage;
import library.Driver;

public class Flight extends BasePage{

	public Flight() {
		PageFactory.initElements(getCurrentDriver(), this);
	}
	
	@FindBy(id = "OneWay")
    private WebElement oneWayRadio;

    @FindBy(id = "FromTag")
    private WebElement fromTextBox;
    
    @FindBy(id = "ToTag")
    private WebElement toTextBox;
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td[contains(@class,'selected')]/a")
    private WebElement departDate;
    
    @FindBy(id = "SearchBtn")
    private WebElement searchButton;
    
    @FindBy(className = "searchSummary")
    private WebElement searchSummary;
    
    public void clickOneWay(){
    		oneWayRadio.click();
    }
    
    public void enterFrom(String from){
	    	fromTextBox.clear();
	    	fromTextBox.sendKeys(from);
	    	// select the first option
	    	getCurrentDriver().findElement(By.id("ui-id-1")).findElements(By.tagName("li")).get(0).click();
    }
    
    public void enterTo(String to){
    		toTextBox.clear();
    		toTextBox.sendKeys(to);
    		// select the first option
    		getCurrentDriver().findElement(By.id("ui-id-2")).findElements(By.tagName("li")).get(0).click();
    }
    
    
    public void setDefaultDate(){
    		departDate.click();
    }
    
    public void clickSearch(){
    		searchButton.click();
    }
    
    public boolean isSearchSummaryPresent() {
    		return searchSummary.isDisplayed();
    }

}