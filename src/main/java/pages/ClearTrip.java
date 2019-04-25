package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BasePage;

public class ClearTrip extends BasePage{
	public ClearTrip() {
		PageFactory.initElements(getCurrentDriver(), this);
	}
	
	@FindBy(xpath = "//nav[@class='hasProductIcons']//a[@href='/flights']")
    private WebElement flightsTab;
    @FindBy(xpath = "//nav[@class='hasProductIcons']//a[@href='/hotels']")
    private WebElement hotelsTab;
    @FindBy(xpath = "//nav[@class='hasProductIcons']//a[@href='/activities']")
    private WebElement activitiesTab;
    @FindBy(xpath = "//nav[@class='hasProductIcons']//a[@href='/trains']")
    private WebElement trainsTab;
    @FindBy(xpath = "//nav[@class='hasProductIcons']//a[@href='/top-deals']")
    private WebElement topDealsTab;
    
    public void navigateTo(String tabName){
		switch (tabName){
			case "Flights":
				flightsTab.click();
				break;
			case "Hotels":
				hotelsTab.click();
				break;
			case "Activities":
				activitiesTab.click();
				break;
			case "Trains":
				trainsTab.click();
				break;
			case "Flight Deals":
				topDealsTab.click();
				break;
		}
		waitForPageToLoad();
    }

	
}
