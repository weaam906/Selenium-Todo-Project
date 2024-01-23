package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterAPI;
import com.qacart.todo.api.TaskAPI;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Todo actions")
public class TodoTest extends BaseTest {
    @Story("Add new Todo")
    @Test (description = "Should be able to add new todo correctly")
    public void shouldBeAbleToAddToDo(){
        RegisterAPI registerAPI = new RegisterAPI(); //1-Create instance from registerAPI class
        registerAPI.Register(); //2-Call register method to log in through API call and send cookies

        TodoPage todoPage = new TodoPage(getDriver()); //3-Create instance from to-do page
        todoPage.load(); //4-Load the to-do page (Login page will be opened)

        injectCookiesToBrowser(registerAPI.getRestAssuredCookies()); //5-Add the restAssured cookies(API) to selenium cookies(browser)
        String actualResult =todoPage //6-Reload the screen again after adding cookies to open to-do page by the saved cookies
                .load()
                .clickOnAddPlusButton()
                .addNewTodo("Learn Selenium")
                .getToDoText();

        Assert.assertEquals(actualResult,"Learn Selenium");
    }

    @Story("Delete todo")
    @Test (description = "Should be able to delete todo correctly")
    public void shouldBeAbleToDelete(){
        RegisterAPI registerAPI = new RegisterAPI(); //1-Create instance from registerAPI class
        registerAPI.Register(); //2-Call register method to log in through API call and send cookies

        TaskAPI taskAPI = new TaskAPI(); //3-Create instance from TaskAPI
        taskAPI.addTask(registerAPI.getAccessToken()); //4-Add new task by passing restAssuredAccessToken(parameter)

        TodoPage todoPage = new TodoPage(getDriver()); //5-Create instance from to-do page
        todoPage.load(); //6-Load the to-do page (Login page will be loaded as cookies haven't been pass yet)
        injectCookiesToBrowser(registerAPI.getRestAssuredCookies()); //7-Add the restAssured cookies(API) to selenium cookies(browser)

        boolean isNoToDoTextDisplayed =todoPage
                .load() //8-Reload the screen again (Login will be done through the passed cookies and navigate to to-do page)
                .clickOnDeleteIcon()
                .isNoToDoTextDisplayed();

        Assert.assertTrue(isNoToDoTextDisplayed);
    }

    @Story("UI Assertion")
    @Test (description = "Check that all the components are existing and back button is working correctly",
            enabled = false)

    public void uiShouldBeDisplayedWellAndBackButtonShouldWorkCorrectly(){
        RegisterAPI registerAPI = new RegisterAPI(); //1-Create instance from registerAPI class
        registerAPI.Register(); //2-Call register method to log in through API call and send cookies

        TodoPage todoPage = new TodoPage(getDriver()); //3-Create instance from to-do page
        todoPage.load(); //4-Load the to-do page (Login page will be opened)

        NewTodoPage newTodoPage = new NewTodoPage(getDriver());

        Assert.assertTrue(todoPage.isAddPlusIconDisplayed());
        Assert.assertTrue(todoPage.isToDoItemDisplayed());
        Assert.assertTrue(newTodoPage.isNewTodoItemDisplayed());
        Assert.assertTrue(newTodoPage.isSubtitleDisplayed());
        Assert.assertTrue(newTodoPage.isTitleDisplayed());
    }
}