package com.booking.StepsDefinition;

import com.booking.page.HomePage;
import com.booking.page.SearchPage;
import com.booking.test.CommonConditions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Steps extends CommonConditions {

    public Steps() {
        this.setUp();
    }

    //HomePage homePage;


    @Given("User open booking")
    public void user_navigates_to() {
        homePage.openPage();
    }

    @When("User enter destination {string}")
    public void user_enter_destination(String city) {
        homePage.inputSearch(city);
    }

    @When("User choose check in date")
    public void user_choose_check_in_date() {
        homePage.setCheckInDate();
    }

    @When("User choose checkout date")
    public void user_choose_checkout_date() {
        homePage.setCheckOutDate();
    }

    @When("User choose guests")
    public void user_choose_guests() {
        homePage.add2Adults1ChildrenOf3YearsOldAddSecondRoom();
    }

    @When("User Search for results")
    public void user_search_for_results() {
        homePage.clickOnSearch();
    }

    @When("User add breakfast")
    public void user_add_breakfast() {
        homePage.clickOnSearch();
    }

    @Then("Breakfast is included")
    public void searching_hotel_is_displayed() {
        searchPage.clickOnAddBreakfast();
    }

    @When ("User login with credentials {string} and {string}")
    public void userLogIn(String email, String password){
        loginPage.login(email, password);
    }

    @Then("User Log in with {string}")
    public void userLogInWithName(String name){
        assertThat(loginPage.getLoggedInUserName(), is(equalTo(name)));
    }
}
