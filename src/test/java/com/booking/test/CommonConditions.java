package com.booking.test;

import com.booking.driver.DriverSingletone;
import com.booking.page.HomePage;
import com.booking.page.LoginPage;
import com.booking.page.PageObjectFactory;
import com.booking.page.SearchPage;
import com.booking.service.ConfProperties;
import com.booking.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;
    ConfProperties properties = new ConfProperties();
    private PageObjectFactory pageFactory;

    @BeforeClass()
    public void setUp(){
        driver = DriverSingletone.getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.pageFactory = new PageObjectFactory(driver);
        this.homePage = pageFactory.getHomePage();
        this.loginPage = pageFactory.getLoginPage();
        this.searchPage = pageFactory.getSearchPage();

        this.homePage.openPage();
    }


    @AfterClass(alwaysRun = true)
    public void stopBrowser(){DriverSingletone.closeDriver();}
}
