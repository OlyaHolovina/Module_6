import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
        email.sendKeys(ConfProperties.getProperty("login"));
    }

    public void clickOnGoButton(){
        goButton.click();
    }

    public void setPassword(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                    until(ExpectedConditions.visibilityOf(password))
                        .sendKeys(ConfProperties.getProperty("password"));
    }

    public void clickOnSubmitButton(){
        submit.click();
    }

    public String getName() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(name));
        return name.getText();
    }

}
