package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import library.BasePage;
import library.Driver;

public class Hotel extends BasePage{
	
	public Hotel() {
		PageFactory.initElements(getCurrentDriver(), this);
	}
	
 	@FindBy(xpath = "//nav[@class='hasProductIcons']//li[contains(@class,'hotelApp')]//a[@href='/hotels']")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    public void navigateToHotelspage() {
    		hotelLink.click();
    }
    
    public void clickSearch() {
	    	searchButton.click();
    }
    
    
    public void enterLocality(String where) {
    		localityTextBox.sendKeys(where);
    }
    
    public void travellerSelection(String reqRoomDetails) {
    		new Select(travellerSelection).selectByVisibleText(reqRoomDetails);
    }
}
