package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoints;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTodoPage extends BasePage {
    public NewTodoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css ="[data-testid=\"header\"]")
    private WebElement createNewTodoHeader;
    @FindBy(css ="[data-testid=\"sub-header\"]")
    private WebElement newTodoSubTitle;
    @FindBy(css ="[data-testid=\"new-todo\"]")
    private WebElement newTodoItem;
    @FindBy(css ="[data-testid=\"submit-newTask\"]")
    private WebElement submitButton;
    @FindBy(css ="[data-testid=\"back\"]")
    private WebElement backToYourTodoHyperText;
    @Step
    public boolean isNewTodoItemDisplayed(){
        return newTodoItem.isDisplayed();
    }
    @Step
    public TodoPage addNewTodo(String newTodoText){
        newTodoItem.sendKeys(newTodoText);
        submitButton.click();
        return new TodoPage(driver);
    }

    //UI Assertion
    public boolean isTitleDisplayed(){
        return createNewTodoHeader.isDisplayed();
    }
    public boolean isSubtitleDisplayed(){
        return newTodoSubTitle.isDisplayed();
    }
    public void returnBackToYourTodo(){
        backToYourTodoHyperText.click();
    }
    public NewTodoPage load(){
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoints.NEW_TODO_ENDPOINT);
        return this;
    }

}
