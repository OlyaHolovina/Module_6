import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

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

    @FindBy(css="div[data-bui-ref='calendar-next']")
    private WebElement nextMontCalend;

    @FindBy(xpath = "//td[@data-date='2023-03-08']")
    private WebElement checkOutDate90;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSearch;

    @FindBy(css =".fe_banner__message")
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


     public void inputSearch (String destination){
         popUpWindow.click();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
         searchDestination.sendKeys(destination);
     }

    public void add2Adults1ChildrenOf3YearsOldAddSecondRoom (){
        guestsFrame.click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addAdult));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addRoom));

        addRoom.click();
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

    public String getTextfromErrorMesage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".fe_banner.fe_banner__accessible.fe_banner__red"))));

return errorMessage.getAttribute("textContent");
    }

     public String getTextfromTitle() {
         return title.getText();
     }

     public void clickOnSignInButton(){
        signInButton.click();
     }

     public void clickOnDateAfter90D(){
         nextMontCalend.click();
         nextMontCalend.click();
         nextMontCalend.click();
         checkOutDate90.click();
     }

     public String add90Days() throws ParseException {
         String date = "2022-12-01";
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(sdf.parse(date));
         calendar.add(Calendar.DATE, 90);
         date = sdf.format(calendar.getTime());
         return date;
     }

    public void validateReservationMessage() {

        String message = errorMessage.getText();
        message = message.replaceAll("\\s+", "");

    }
}

