/* -----------------------------------------------------------------------------
  - Date: 20th.Dec.23                                                          -
  - Auth: We'am Othman                                                         -
  - Desc: Base test is for driver setup and quit before and after test methods -
  -       this class must be extended by all the test classes in order         -
  ------------------------------------------------------------------------------ */

package com.qacart.todo.base;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookiesUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class BaseTest {
    //ThreadLocal is used to provide each method a unique driver instance
    //in order to be able to run more than one method in the same class in parallel
    //as they share the same class, so they share the same driver instance so, they'll override each other
    protected ThreadLocal<WebDriver> driver= new ThreadLocal<>(); //Protected in order to be seen by all classes within the same package
    public WebDriver getDriver() {return this.driver.get();}
    public void setDriver(WebDriver driver) {this.driver.set(driver);}

    @BeforeMethod
    public void driverSetup(){
        WebDriver driver =new DriverFactory().initializeDriver();
        setDriver(driver);
    }
    @AfterMethod
    public void driverTeardown(ITestResult iTestResult){
        String testCaseName = iTestResult.getMethod().getMethodName();
        File destinationFile = new File("screenshots"+File.separator+testCaseName+".png");
        takeScreenShots(destinationFile);
        getDriver().quit();}
    @Step
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookies){
        List<org.openqa.selenium.Cookie> seleniumCookies = CookiesUtils.convertRestAssuredToSeleniumCookie(restAssuredCookies);
        for(org.openqa.selenium.Cookie cookie:seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    public void takeScreenShots(File destinationFile){
        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,destinationFile);
            InputStream inputStream = new FileInputStream(destinationFile);
            Allure.addAttachment("screenshots",inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
