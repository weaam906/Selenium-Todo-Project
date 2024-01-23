package com.qacart.todo.api;

import com.qacart.todo.config.EndPoints;
import com.qacart.todo.objects.Task;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TaskAPI {
    public void addTask(String token){
        Task task = new Task("Learn Selenium",false);
        Response response =
                given()
                        .baseUri(ConfigUtils.getInstance().getBaseUrl())
                        .header("Content-Type","application/json")
                        .body(task)
                        .auth().oauth2(token) //take the accessToken to authorize log in
                .when()
                        .post(EndPoints.API_TASK_ENDPOINT) //path parameter
                .then()
                        .log().all().extract().response();
        if(response.statusCode()!=201){
            throw new RuntimeException("Something went wrong in adding new task");
        }
    }
}
