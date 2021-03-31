package com.refactorhythm.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class ReimbursementTest {
    private Reimbursement r_withConstructor;
    private Reimbursement r_withSetters;

    private Instant now = Instant.now();
    private Instant in2Hours = now.plus(2, ChronoUnit.HOURS);

    private Timestamp submitted = Timestamp.from(now);
    private Timestamp resolved = Timestamp.from(in2Hours);

    private User author = mock(User.class);
    private User resolver = mock(User.class);


    @Test
    @Order(1)
    public void noArgConstructorTest(){
        r_withSetters = new Reimbursement();
        assertNotNull(r_withSetters);
    }

    @Test
    @Order(2)
    public void argConstructorTest(){
        when(author.getUser_id()).thenReturn(1);
        when(resolver.getUser_id()).thenReturn(2);
        r_withConstructor = new Reimbursement(
                1,
                100f,
                submitted,
                resolved,
                "description of the reimbursement",
                author.getUser_id(),
                resolver.getUser_id(),
                1,
                1
        );
        assertNotNull(r_withConstructor);
    }

    @Test
    @Order(3)
    public void settersTest(){
        when(author.getUser_id()).thenReturn(1);
        when(resolver.getUser_id()).thenReturn(2);
        r_withSetters.setId(1);
        r_withSetters.setAmount(100f);
        r_withSetters.setSubmitted(submitted);
        r_withSetters.setResolved(resolved);
        r_withSetters.setDescription("description of the reimbursement");
        r_withSetters.setAuthor(author.getUser_id());
        r_withSetters.setResolver(resolver.getUser_id());
        r_withSetters.setStatus_id(1);
        r_withSetters.setType_id(1);
        assertNotNull(r_withSetters);
    }

    @Test
    @Order(4)
    public void gettersTest(){
        assertEquals(r_withConstructor.getId(), r_withSetters.getId());
        assertEquals(r_withConstructor.getAmount(), r_withSetters.getAmount());
        assertEquals(r_withConstructor.getSubmitted(), r_withSetters.getSubmitted());
        assertEquals(r_withConstructor.getResolved(), r_withSetters.getResolved());
        assertEquals(r_withConstructor.getDescription(), r_withSetters.getDescription());
        assertEquals(r_withConstructor.getAuthor(), r_withSetters.getAuthor());
        assertEquals(r_withConstructor.getResolver(), r_withSetters.getResolver());
        assertEquals(r_withConstructor.getStatus_id(), r_withSetters.getStatus_id());
        assertEquals(r_withConstructor.getType_id(), r_withSetters.getType_id());
    }

    @Test
    @Order(5)
    public void hashCodeTest(){
        assertEquals(r_withConstructor.hashCode(), r_withSetters.hashCode());
    }

    @Test
    @Order(6)
    public void isEqualsTest(){
        assertEquals(r_withSetters, r_withConstructor);
    }

    @Test
    @Order(7)
    public void toStringTest(){
        assertEquals(r_withConstructor.toString(), r_withSetters.toString());
    }
}
