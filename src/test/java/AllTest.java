import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AllTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;

    @BeforeClass
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(ConfProperties.getProperty("page"));

        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.searchPage = new SearchPage(driver);
    }

    @Test
    public void searchForHotelWithoutLogIN() {
        homePage.inputSearch("Cordoba, Spain");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.clickOnSearch();

        Assert.assertTrue(driver.getTitle().contains("Córdoba: "));
    }

    @Test
    public void successLogin() {
        homePage.clickOnSignInButton();
        loginPage.setEmail();
        loginPage.clickOnGoButton();
        loginPage.setPassword();
        loginPage.clickOnSubmitButton();
        Assert.assertTrue(loginPage.getName().contains("Ольга Головина"));
    }

    @Test
    public void userCanSaveHotelToWishlist() {
        homePage.inputSearch("Cordoba, Spain");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.clickOnSearch();
        homePage.getTextFromTitle();
        searchPage.clickOnWishlist();
        Assert.assertTrue(searchPage.appearPopUpWishList(), "true");
    }

    @Test
    public void searchHotelFor2AdultsAnd1Child2Bedrooms() {
        homePage.inputSearch("Zakopane");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.add2Adults1ChildrenOf3YearsOldAddSecondRoom();
        homePage.clickOnSearch();

        searchPage.clickOnAddBreakfast();
        Assert.assertEquals(searchPage.getTextBreakfast(), "Breakfast included");

        searchPage.chooseBudgetFrom50to100();
        Assert.assertTrue(searchPage.priceForNight() <= 50);

    }

    @Test
    public void checkSearchLocation() {
        homePage.inputSearch("Zakopane, Poland");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.add2Adults1ChildrenOf3YearsOldAddSecondRoom();
        homePage.clickOnSearch();
        Assert.assertEquals(searchPage.getTextCity(), "Zakopane");
    }

    @Test
    public void checkErrorMessageAfterReservation90Days() {
        homePage.inputSearch("Cordoba, Spain");
        homePage.setCheckInDate();
        homePage.clickOnDateAfter90D();
        homePage.clickOnSearch();

        String textFromErrorMessage = homePage.getTextFromErrorMessage();
        Assert.assertEquals(textFromErrorMessage, "Sorry, reservations for more than 30 nights aren't possible.");
    }

    @Test
    public void messageHoverTravellingForWork() {
        homePage.inputTextLocationWithJS();
        searchPage.setCheckInDate();
        searchPage.hoverText();

    }

    @Test
    public void setBudgetAndCheckHotel() {
        homePage.inputTextLocationWithJS();
        searchPage.setCheckInDate();
        searchPage.clickOnSearch();
        searchPage.clickOnBudgetTrue();
        searchPage.setBudgetFrom60();

        Assert.assertTrue(searchPage.priceForHotel() >= 60);

    }

    @AfterClass
        public void tearDown() {
            driver.quit();
    }
}

