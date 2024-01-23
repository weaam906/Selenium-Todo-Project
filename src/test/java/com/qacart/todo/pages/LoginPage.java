/* -----------------------------------------------------------------------------
  - Date: 20th.Dec.23                                                          -
  - Auth: We'am Othman                                                         -
  - Desc: Login page is for initializing the elements,methods within this page -
  -       every page must extend from BasePage                                 -
  ------------------------------------------------------------------------------ */

package com.qacart.todo.pages;
import com.qacart.todo.base.BasePage;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[data-testid=\"email\"]")
    private WebElement emailInput;
    @FindBy(css = "[data-testid=\"password\"]")
    private WebElement passwordInput;
    @FindBy(css = "[data-testid=\"submit\"]")
    private WebElement submitButton;
    @FindBy(css = "[data-testid=\"signup\"]")
    private WebElement createAnAccountLink;
    @Step
    public LoginPage load(){ //return this page to use builder design pattern
        driver.get(ConfigUtils.getInstance().getBaseUrl()); //Send the website URL
        return this;
    }
    @Step
    public TodoPage login(String email,String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new TodoPage(driver); //login page navigates to the to-do page.
    }

    public boolean isCreateAnAccountDisplayed(){
        return createAnAccountLink.isDisplayed();
    }
    public boolean isCreateAnAccountClickable(){
        return createAnAccountLink.isEnabled();
    }
    public void clickOnCreateAnAccount(){
        createAnAccountLink.click();
    }
}
