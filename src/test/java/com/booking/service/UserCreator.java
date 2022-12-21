package com.booking.service;

import com.booking.model.User;

public class UserCreator {

    public static final String TESTDATA_EMAIL = "testdata.user.email";
    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredential(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME )
        ,TestDataReader.getTestData(TESTDATA_USER_PASSWORD)
                ,TestDataReader.getTestData(TESTDATA_EMAIL));
    }
}
