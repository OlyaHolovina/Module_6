package com.booking.test;

import org.testng.Assert;
import org.testng.annotations.*;

public class AllTest extends CommonConditions {

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
        homePage.signIn();
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
        homePage.inputTextLocationWithJS("Zakopane");
        searchPage.setCheckInDate();
        searchPage.hoverText();

    }

    @Test
    public void setBudgetAndCheckHotel() {
        homePage.inputTextLocationWithJS("Zakopane");
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

