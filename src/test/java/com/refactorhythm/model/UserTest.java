package com.refactorhythm.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UserTest {

    private User userWithConstructor;
    private User userWithSetters;

    private int userid;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private int roleid;

    @Test
    @Order(0)
    void noArgConstructorTest() {
        User u = new User();

        Assertions.assertNotNull(u);

        Assertions.assertEquals(u.getUser_id(), 0);
        Assertions.assertEquals(u.getRole_id(),0);
    }

    @Test
    @Order(1)
    void allArgConstructorTest() {
        userWithConstructor = new User(
                1,
                "username",
                "password",
                "first",
                "last",
                "refactorhythm@gmail.com",
                1
        );

        Assertions.assertNotNull(userWithConstructor);
    }

    @Test
    @Order(3)
    void settersTest() {
        userWithSetters.setUser_id(1);
        userWithSetters.setUsername("username");
        userWithSetters.setPassword("password");
        userWithSetters.setFirstname("username");
        userWithSetters.setPassword("pickett");
        userWithSetters.setEmail("refactorhythm@gmail.com");
        userWithSetters.setRole_id(1);

        Assertions.assertNotNull(userWithSetters);
    }

    @Test
    @Order(4)
    void gettersTest() {
        assertEquals(userWithConstructor.getUser_id(), userWithSetters.getUser_id());
        assertEquals(userWithConstructor.getUsername(), userWithSetters.getUsername());
        assertEquals(userWithConstructor.getPassword(), userWithSetters.getPassword());
        assertEquals(userWithConstructor.getFirstname(), userWithSetters.getFirstname());
        assertEquals(userWithConstructor.getLastname(), userWithSetters.getLastname());
        assertEquals(userWithConstructor.getEmail(), userWithSetters.getEmail());
        assertEquals(userWithConstructor.getRole_id(), userWithSetters.getRole_id());
    }

    @Test
    @Order(5)
    void hashCodeTest() {
        assertEquals(userWithConstructor.hashCode(), userWithSetters.hashCode());
    }

    @Test
    @Order(6)
    void isEqualsTest() {
        assertEquals(userWithConstructor, userWithSetters);
    }

    @Test
    @Order(7)
    void toStringTest() {
        assertEquals(userWithConstructor.toString(), userWithSetters.toString());
    }

}
