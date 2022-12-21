package com.booking.page;

import com.booking.service.ConfProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends AbstractPage{
    Actions actions;
    JavascriptExecutor jsExecutor;

    public SearchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        jsExecutor=(JavascriptExecutor)driver;
        actions = new Actions(driver);
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

    @FindBy(css="div [data-testid=availability-rate-information] span:nth-child(2) span:nth-child(2)")
    private WebElement price;

    @FindBy(css = "span[data-date='2022-12-01']")
    private WebElement checkInDate;

    @FindBy(css = "span[data-testid='searchbox-checkbox-question']")
    private WebElement searchCheckboxWithHoverText;

    @FindBy(css = "button[type='submit']")
    private WebElement buttonSearch;

    @FindBy(css="div[data-testid='filters-group-toggle-style']")
    private WebElement setYourBudget;

    @FindBy(css="input[type=range]~div:nth-of-type(1) span")
    private WebElement range;

    public int priceForNight(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String totalPrice = priceForHotel.getText();
        totalPrice = totalPrice.replaceAll("[^\\d.]", "");
        Integer pricePerNight = Integer.valueOf(totalPrice);
        pricePerNight = pricePerNight/7;
        return pricePerNight;
    }

    public int priceForHotel(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String totalPrice = price.getText();
        totalPrice = totalPrice.replaceAll("[^\\d.]", "");
        Integer pricePerNight = Integer.valueOf(totalPrice);
        return pricePerNight;
    }

    public void chooseBudgetFrom50to100(){
        budget50t0100.click();
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addBreakfast));
    }

    public String getTextBreakfast(){
        return breakfostLabel.getText();
    }

    public String getTextCity(){
        return cityAddress.getText();
    }

    public void clickOnAddBreakfast(){
        actions.moveToElement(addBreakfast);
        addBreakfast.click();
    }

    public void clickOnWishlist(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(wishlist)).click();
    }

    public boolean appearPopUpWishList(){
        boolean wiahlist;
        return  wiahlist = wishlistPopOver.isDisplayed();
    }

    public void setCheckInDate (){
        checkInDate.click();
  }

  public void hoverText(){
      boolean result = false;
      new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchCheckboxWithHoverText));
      actions.moveToElement(searchCheckboxWithHoverText).build().perform();
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

  }

  public void clickOnSearch(){
        buttonSearch.click();
  }

  public void clickOnBudgetTrue(){
        setYourBudget.click();
        jsExecutor.executeScript("window.scrollBy(0,150)");
  }

  public void setBudgetFrom60(){
      actions.moveToElement(range).clickAndHold()
              .moveToElement(range, 50, 0).click().build().perform();
  }

  public SearchPage openPage(){
        driver.navigate().to(ConfProperties.getProperty("page"));
        return this;
  }

}
