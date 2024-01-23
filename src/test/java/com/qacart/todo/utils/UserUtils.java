/* ------------------------------------------------------ -
 - Date: 28rd.Dec.23                                      -
 - Author: We'am Othman                                   -
 - Desc: This file is to create User using Faker library  -
 - ------------------------------------------------------ - */
package com.qacart.todo.utils;

import com.github.javafaker.Faker;
import com.qacart.todo.objects.User;

public class UserUtils {
    public static User generateRandomUser(){

        String email = Faker.instance().internet().emailAddress();
        String password = "12345678@";
        String firstName = Faker.instance().name().firstName();
        String lastName = Faker.instance().name().lastName();

        User user = new User(email,password,firstName,lastName);
        return user;
    }
}
