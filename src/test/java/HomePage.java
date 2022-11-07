import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy (css = "#onetrust-accept-btn-handler")
    private WebElement popUpWindow;

    @FindBy(css = "input[type ='search']")
    private WebElement searchDestination;

    @FindBy(xpath = "//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']")
    private WebElement openCalend ;

    @FindBy(xpath = "//td[@data-date='2022-12-01']")
    private WebElement checkInDate;

    @FindBy(xpath = "//td[@data-date='2022-12-08']")
    private WebElement checkOutDate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//h1[contains(text(),'Córdoba:')]")
    private WebElement title;

    @FindBy(xpath = "//a[@class='bui-button bui-button--secondary js-header-login-link']")
    private WebElement signInButton;

     public void inputSearch (String destination){
         popUpWindow.click();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         searchDestination.sendKeys(destination);
     }

     public void setCheckInDate (){
         new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(openCalend)).click();
         checkInDate.click();
     }

     public void setCheckOutDate(){
         new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
     }

     public void clickOnSearch(){
         buttonSearch.click();
     }

     public String getTextfromTitle() {
         return title.getText();
     }

     public void clickOnSignInButton(){
        signInButton.click();
     }

    }

