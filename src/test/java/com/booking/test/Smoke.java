package com.booking.test;

import com.booking.model.User;
import com.booking.page.LoginPage;
import com.booking.service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Smoke  extends CommonConditions{


    @Test
    public void successLogin() {
        User testUser = UserCreator.withCredential();
        String loggedInUser = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .getLoggedInUserName();

        assertThat(loggedInUser, is(equalTo(testUser.getUsername())));

//        Assert.assertTrue(loginPage.getName().contains("Ольга Головина"));
    }
}
