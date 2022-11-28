import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.sound.sampled.Port;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AllTest {
    public WebDriver driver;
    String nodeURL="http://192.168.68.101:4444";
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;

    @Parameters({"Port"})
    @BeforeClass
    public void setDriver(String Port) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(Port.equalsIgnoreCase("CR"))
        {
            //nodeURL = "http://192.168.68.101:5555";
            System.out.println("Chrome Browser Initiated");
            //DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            //capabilities.setPlatform(Platform.WINDOWS);

            //driver = new RemoteWebDriver(new URL(nodeURL),capabilities);

            //driver.get("https://www.apple.com/");
            //driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        else if(Port.equalsIgnoreCase("FF"))
        {
            //nodeURL = "http://192.168.68.101:5556";
            System.out.println("Firefox Browser Initiated");
            //DesiredCapabilities capabilities1 = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            //capabilities.setPlatform(Platform.WINDOWS);

            //driver = new RemoteWebDriver(new URL(nodeURL),capabilities1);

//            driver.get(ConfProperties.getProperty("page"));
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
        driver.get(ConfProperties.getProperty("page"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

