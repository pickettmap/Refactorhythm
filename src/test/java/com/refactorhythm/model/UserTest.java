package com.refactorhythm.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UserTest {

    private User userWithConstructor = new User();
    private User userWithSetters = new User();


    @Test
    @Order(1)
    public void noArgConstructorTest() {
        userWithSetters = new User();

        Assertions.assertNotNull(userWithSetters);

        Assertions.assertEquals(userWithSetters.getUser_id(), 0);
        Assertions.assertEquals(userWithSetters.getRole_id(),0);
    }

    @Test
    @Order(2)
    public void allArgConstructorTest() {
        userWithConstructor = new User(
                1,
                "username",
                "password",
                "first",
                "last",
                "refactorhythm@gmail.com",
                1
        );

        assertNotNull(userWithConstructor);
    }

    @Test
    @Order(3)
    public void settersTest() {
        userWithSetters.setUser_id(1);
        userWithSetters.setUsername("username");
        userWithSetters.setPassword("password");
        userWithSetters.setFirstname("first");
        userWithSetters.setLastname("last");
        userWithSetters.setEmail("refactorhythm@gmail.com");
        userWithSetters.setRole_id(1);

        assertNotNull(userWithSetters);
    }

    @Test
    @Order(4)
    public void gettersTest() {
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
    public void hashCodeTest() {
        assertEquals(userWithConstructor.hashCode(), userWithSetters.hashCode());
    }

    @Test
    @Order(6)
    public void isEqualsTest() {
        assertEquals(userWithConstructor, userWithSetters);
    }

    @Test
    @Order(7)
    public void toStringTest(){
        assertEquals(userWithConstructor.toString(), userWithSetters.toString());
    }

}
