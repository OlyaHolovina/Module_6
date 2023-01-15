package com.booking.page;

import com.booking.service.ConfProperties;
import com.booking.service.LoggingConfProperties;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected ConfProperties properties;

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
        this.properties = new LoggingConfProperties(new ConfProperties());
    }
}
