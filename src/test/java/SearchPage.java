import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    public WebDriver driver;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@data-testid='wishlist-icon']")
    private WebElement wishlist;

    @FindBy(css = "div[data-testid='wishlist-popover-content']")
    private WebElement wishlistPopOver;


    public void clickOnWishlist(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(wishlist)).click();
    }

    public boolean appearPopUpWishList(){
       // new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(wishlistPopOver));
        boolean wiahlist;
        return  wiahlist = wishlistPopOver.isDisplayed();
    }
}
