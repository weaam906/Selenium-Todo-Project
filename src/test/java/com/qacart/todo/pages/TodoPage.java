package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoints;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage {
    public TodoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css="[data-testid=\"welcome\"]")
    private WebElement welcomeMessage;
    @FindBy(css="[data-testid=\"add\"]")
    private WebElement addButton;
    @FindBy(css = "[data-testid=\"todo-item\"]")
    private WebElement todoItem;
    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement deleteIcon;
    @FindBy(css="[data-testid=\"no-todos\"]")
    private WebElement noToDoText;
    @Step
    public TodoPage load(){
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoints.TODO_PAGE_ENDPOINT);
        return this;
    }
    @Step
    public boolean isWelcomeMessageDisplayed(){return welcomeMessage.isDisplayed();}
    @Step
    public NewTodoPage clickOnAddPlusButton(){
        addButton.click();
        return new NewTodoPage(driver); //On clicking, the screen navigates to the new to-do page
    }
    @Step
    public String getToDoText(){
        return todoItem.getText();
    }
    @Step
    public boolean isNoToDoTextDisplayed(){
        return noToDoText.isDisplayed();
    }
    @Step
    public TodoPage clickOnDeleteIcon(){ //return to-do page to stay in the same page and assert on it
        deleteIcon.click();
        return this;
    }

    //UI Assertion
    public boolean isDeleteIconDisplayed(){return deleteIcon.isDisplayed();}
    public boolean isAddPlusIconDisplayed(){return addButton.isDisplayed();}
    public boolean isToDoItemDisplayed(){
        return todoItem.isDisplayed();
    }

}
