package com.booking.page;

import com.booking.service.ConfProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class HomePage extends AbstractPage {
    JavascriptExecutor jsExecutor;
    Actions action;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }


    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement popUpWindow;

    @FindBy(css = "input[placeholder='Where are you going?']")
    private WebElement searchDestination;

    @FindBy(css = "ul[data-testid=autocomplete-results]")
    private WebElement resultsDestination;

    @FindBy(css = "ul[data-testid=autocomplete-results] li:nth-of-type(1) div")
    private WebElement resultDestination;

    @FindBy(css = "div[data-testid=searchbox-datepicker] div")
    private WebElement openCalendar;

    @FindBy(xpath = "//span[@data-date='2023-12-01']")
    private WebElement checkInDate;

    @FindBy(xpath = "//span[@data-date='2022-12-08']")
    private WebElement checkOutDate;

    @FindBy(css = "div[data-testid='searchbox-datepicker'] button")
    private WebElement nextMontCalendar;

    @FindBy(xpath = "//td[@data-date='2023-03-08']")
    private WebElement checkOutDate90;

    @FindBy(css = "button.sb-searchbox__button")
    private WebElement buttonSearch;

    @FindBy(css = ".fe_banner__message")
    private WebElement errorMessage;

    @FindBy(xpath = "//h1[contains(text(),'Córdoba:')]")
    private WebElement title;

    @FindBy(xpath = "//a[@class='bui-button bui-button--secondary js-header-login-link']")
    private WebElement signInButton;

    @FindBy(css = ".xp__guests__count")
    private WebElement guestsFrame;

    @FindBy(css = "button[aria-label='Increase number of Adults']")
    private WebElement addAdult;

    @FindBy(css = ".bui-button bui-button--secondary bui-stepper__add-button")
    private WebElement addChildren;

    @FindBy(css = "option[value='3']")
    private WebElement yearsOld3;

    @FindBy(name = "age")
    private WebElement dropDownChildAge;

    @FindBy(css = "button[aria-label='Increase number of Rooms']")
    private WebElement addRoom;

    @FindBy(css = ".sb-group__children__field clearfix")
    private WebElement childField;


    public void inputSearch(String destination) {
        popUpWindow.click();
        searchDestination.sendKeys(destination);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        jsExecutor.executeScript("window.scrollBy(0,150)");
        action.moveToElement(resultDestination).click().build().perform();

    }

    public void add2Adults1ChildrenOf3YearsOldAddSecondRoom() {
        guestsFrame.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addAdult));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addRoom));

        addRoom.click();
    }

    public void setCheckInDate() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOf(openCalendar));
        checkInDate.click();

    }

    public void setCheckOutDate() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
    }

    public void clickOnSearch() {
        buttonSearch.click();
    }

    public String getTextFromErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".fe_banner.fe_banner__accessible.fe_banner__red"))));

        return errorMessage.getAttribute("textContent");
    }

    public String getTextFromTitle() {
        return title.getText();
    }

    public void signIn() {
        signInButton.click();
    }

    public void clickOnDateAfter90D() {
        nextMontCalendar.click();
        nextMontCalendar.click();
        nextMontCalendar.click();
        checkOutDate90.click();
    }

    public void inputTextLocationWithJS(String input) {
        popUpWindow.click();
        jsExecutor.executeScript("window.scrollBy(0,150)");
        searchDestination.click();
        searchDestination.sendKeys(input);

        action.sendKeys(Keys.ENTER);
        action.perform();
    }

    public HomePage openPage() {
        driver.navigate().to(properties.getProperty("page"));
        return this;
    }

    public void acceptCookiesPopup() {
        popUpWindow.click();
    }
}

