package com.booking.page;

import com.booking.service.ConfProperties;
import com.booking.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    HomePage homePage;
    private Logger log = LogManager.getRootLogger();

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.homePage = new HomePage(driver);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement goButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement submit;

    @FindBy(xpath = "//span[containt(test(),'Ольга Головина')]")
    private WebElement name;


    public void setEmail(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(email)).click();
        email.sendKeys(properties.getProperty("login"));
    }

    public void clickOnGoButton(){
        goButton.click();
    }

    public void setPassword(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                    until(ExpectedConditions.visibilityOf(password))
                        .sendKeys(properties.getProperty("password"));
    }

    public void clickOnSubmitButton(){
        submit.click();
    }

    public String getName() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(name));
        return name.getText();
    }

    public LoginPage openPage() {
     driver.navigate().to(properties.getProperty("page"));
     return this;
    }

    public LoginPage login(User user){
        homePage.acceptCookiesPopup();
       homePage.signIn();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(email)).click();
        email.sendKeys(user.getEmail());
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(goButton)).click();
        clickOnGoButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(password)).click();
        password.sendKeys(user.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(submit)).click();
        clickOnSubmitButton();
//        logger.info();
        return new LoginPage(driver);
    }

    public String getLoggedInUserName(){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(name)).click();
        return name.getText();
    }

}
