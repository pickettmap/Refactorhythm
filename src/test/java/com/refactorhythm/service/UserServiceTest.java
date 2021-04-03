package com.refactorhythm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.refactorhythm.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.lang.reflect.Method;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private UserService userService = new UserService();
    private User testUser = new User(
            1,
            "username",
            "password",
            "first",
            "last",
            "refactorhythm@gmail.com",
            1
    );
    private Gson gson = new Gson();
    private String json = gson.toJson(testUser);

    @Test
    @Order(1)
    public void userServiceConstructor() {
        UserService testUserService = new UserService();

        Assert.assertNotNull(testUserService);
    }

    @Test
    @Order(2)
    public void createUserTest() {
        userService.createUser(json);
    }

    @Test
    @Order(3)
    public void updateUserTest() {
        userService.updateUser(json);
    }

    @Test
    @Order(4)
    public void fetchAllUsersTest() {
        userService.fetchAllUsers();
    }

    @Test
    @Order(5)
    public void getUserByIdTest() {
        userService.getUserById(1);
    }

    @Test
    @Order(6)
    public void getUserByUsernameTest () {
        userService.getUserByUsername("username");
    }

    @Test
    @Order(7)
    public void deleteUserTest() {
        userService.deleteUser(1);
    }

    @Test
    @Order(8)
    public void getUserByLoginTest() {
        userService.getUserByLogin("username","password");
    };
}
