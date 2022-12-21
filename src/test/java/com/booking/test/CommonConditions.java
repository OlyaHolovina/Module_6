package com.booking.test;

import com.booking.driver.DriverSingletone;
import com.booking.page.HomePage;
import com.booking.page.LoginPage;
import com.booking.page.SearchPage;
import com.booking.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;

    @BeforeMethod()
    public void setUp(){
        driver = DriverSingletone.getDriver();

        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.searchPage = new SearchPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){DriverSingletone.closeDriver();}
}
