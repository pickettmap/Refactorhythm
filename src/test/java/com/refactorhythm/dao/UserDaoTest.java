package com.refactorhythm.dao;

import com.refactorhythm.model.Reimbursement;
import com.refactorhythm.model.User;
import com.refactorhythm.util.SessionUtility;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
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

        assertEquals("first", ud.getById(1).getFirstname());
    }

    @Test
    @Order(2)
    public void getById(){
        ud.getById(testUser.getUser_id());

        User comparison = ud.getById(testUser.getUser_id());
        assertEquals("first", comparison.getFirstname());
    }

    @Test
    @Order(3)
    public void getByUsername(){
        User u = ud.getByUsername(testUser.getUsername());

        User comparison = ud.getById(testUser.getUser_id());
        assertEquals(comparison.getUsername(), u.getUsername());
    }

    @Test
    @Order(4)
    public void getListTest(){
        assertNotEquals(0,ud.getList().size());
    }

    @Test
    @Order(5)
    public void deleteTest(){
        ud.delete(testUser);
        assertNull(ud.getById(1));
    }
}
