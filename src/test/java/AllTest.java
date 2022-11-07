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
    public  void setDriver(){
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
    public void searchForHotelWithoutLogIN () {
        homePage.inputSearch("Cordoba, Spain");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.clickOnSearch();

        Assert.assertTrue(driver.getTitle().contains("Córdoba: "));
    }

    @Test
    public void successLogin(){
        homePage.clickOnSignInButton();
        loginPage.setEmail();
        loginPage.clickOnGoButton();
        loginPage.setPassword();
        loginPage.clickOnSubmitButton();
        Assert.assertTrue(loginPage.getName().contains("Ольга Головина"));
    }

    @Test
    public void userCanSaveHotelToWishlist(){
        homePage.inputSearch("Cordoba, Spain");
        homePage.setCheckInDate();
        homePage.setCheckOutDate();
        homePage.clickOnSearch();
        homePage.getTextfromTitle();
        searchPage.clickOnWishlist();
        Assert.assertTrue(searchPage.appearPopUpWishList(), "true");
    }


    @AfterClass
        public void tearDown() {
            driver.quit();
    }

}
