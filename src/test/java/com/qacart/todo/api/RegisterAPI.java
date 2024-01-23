/* ---------------------------------------------------------------------------- -
  - Date: 27th.Dec.23                                                           -
  - Auth: We'am Othman                                                          -
  - Desc: Register to the website using API instate of UI by sending data using -
  -       from User instance in the {body} and generate cookie to register with -                              -
  - --------------------------------------------------------------------------- - */

package com.qacart.todo.api;

import com.qacart.todo.config.EndPoints;
import com.qacart.todo.objects.User;
import com.qacart.todo.utils.ConfigUtils;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterAPI {
    private List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userID;
    private String email;
    private String firstname;
    private String lastName;
    private String password;

    public void Register(){
        User user = UserUtils.generateRandomUser();
        Response response =
                given()
                        .baseUri(ConfigUtils.getInstance().getBaseUrl())
                        .header("Content-Type","application/json")
                        .body(user)
                .when()
                        .post(EndPoints.API_REGISTER_ENDPOINT) //path parameter
                .then()
                        .extract().response();

        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userID = response.path("userID");
        email = user.getEmail();
        password = user.getPassword();
        firstname =user.getFirstName();
        lastName = user.getLastName();

    }
    public String getEmail() {
        return this.email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Cookie> getRestAssuredCookies() {return this.restAssuredCookies;}

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getUserID() {
        return userID;
    }
}
