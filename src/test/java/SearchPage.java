import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    public WebDriver driver;
    Actions actions;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@data-testid='wishlist-icon']")
    private WebElement wishlist;

    @FindBy(css = "div[data-testid='wishlist-popover-content']")
    private WebElement wishlistPopOver;

    @FindBy(css = ".efeda70352")
    private WebElement budget50t0100;

    @FindBy(css = "div[data-filters-item='popular:mealplan=1']")
    private WebElement addBreakfast;

    @FindBy(css = "button[aria-label='Dismiss sign-in info.']")
    private WebElement popUpDismissSignIn;

    @FindBy(css = "div[data-testid='gallery-ribbon']")
    private WebElement breakfostLabel;

    @FindBy(css="span[data-testid='address']")
    private WebElement cityAddress;

    @FindBy(css="div[data-testid='price-and-discounted-price']>span:nth-child(2)")
    private WebElement priceForHotel;

    public int priceForNight(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String totalPrice = priceForHotel.getText();
        totalPrice = totalPrice.replaceAll("[^\\d.]", "");
        Integer pricePerNight = Integer.valueOf(totalPrice);
        pricePerNight = pricePerNight/7;
        return pricePerNight;
    }

    public void chooseBudgetFrom50to100(){
        budget50t0100.click();
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addBreakfast));
    }

    public String getTextBreakf(){
        return breakfostLabel.getText();
    }

    public String getTextCity(){
        return cityAddress.getText();
    }

    public void clickOnAddBreakfast(){
        actions = new Actions(driver);
        actions.moveToElement(addBreakfast);
        addBreakfast.click();
    }

    public void clickOnWishlist(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(wishlist)).click();
    }

    public boolean appearPopUpWishList(){
       // new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(wishlistPopOver));
        boolean wiahlist;
        return  wiahlist = wishlistPopOver.isDisplayed();
    }

}
