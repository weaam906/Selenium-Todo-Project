package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Auth Login")
public class LoginTest extends BaseTest {
    @Story("Login with email and password")
    @Test (description = "Should login using email and password")
    public void shouldBeAbleToLoginWithEmailAndPassword(){
    //1-Create instance of the login page
        LoginPage loginPage = new LoginPage(getDriver()); //Create object from login page

    //2- initialize the driver, login and assert the welcome message using builder design pattern
        boolean isWelcomeMessageDisplayed = loginPage
                .load()
                .login(ConfigUtils.getInstance().getEmail(), ConfigUtils.getInstance().getPassword())
                .isWelcomeMessageDisplayed();

    //3-Assert the validation of the login
        Assert.assertTrue(isWelcomeMessageDisplayed);
    }

    @Story("Login UI Assertion")
    @Test (description = "login page should be displayed as expected UI",
            enabled = false)
    public void loginPageShouldBeDisplayedAsExpected(){
        //1-Create instance of the login page
        LoginPage loginPage = new LoginPage(getDriver()); //Create object from login page
        //2- initialize the driver, login and assert the welcome message using builder design pattern
        boolean isWelcomeMessageDisplayed = loginPage
                .load()
                .login(ConfigUtils.getInstance().getEmail(), ConfigUtils.getInstance().getPassword())
                .isWelcomeMessageDisplayed();

        //3-Assert the validation of the login
        Assert.assertTrue(isWelcomeMessageDisplayed);
    }
}
