package com.refactorhythm.dao;

import com.refactorhythm.model.Reimbursement;
import com.refactorhythm.model.User;
import com.refactorhythm.util.SessionUtility;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {
    User testUser = new User(
                1,
                "username",
                "password",
                "first",
                "last",
                "refactorhythm@gmail.com",
                1
    );

    UserDao ud = new UserDao();

    @Test
    @Order(1)
    public void insertTest(){
        ud.insert(testUser);
    }

    @Test
    @Order(2)
    public void getById(){
        ud.getById(testUser.getUser_id());
    }

    @Test
    @Order(3)
    public void getByUsername(){
        ud.getByUsername(testUser.getUsername());
    }

    @Test
    @Order(4)
    public void getListTest(){
        ud.getList();
    }

    @Test
    @Order(5)
    public void deleteTest(){
        ud.delete(testUser);
    }
}
