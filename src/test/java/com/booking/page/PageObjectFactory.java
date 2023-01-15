package com.booking.page;

import com.booking.driver.DriverSingletone;
import org.openqa.selenium.WebDriver;

public class PageObjectFactory {
    private WebDriver driver;

    public PageObjectFactory(WebDriver driver) {

        this.driver = driver;
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public LoginPage getLoginPage(){
        return new LoginPage(driver);
    }

    public SearchPage getSearchPage(){
        return new SearchPage(driver);
    }
}
