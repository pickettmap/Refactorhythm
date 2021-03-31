package com.refactorhythm.dao;

import com.refactorhythm.model.Reimbursement;
import com.refactorhythm.model.User;
import com.refactorhythm.util.SessionUtility;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.mock;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReimbursementDaoTest {
    private Instant now = Instant.now();
    private Instant in2Hours = now.plus(2, ChronoUnit.HOURS);
    private Timestamp submitted = Timestamp.from(now);
    private Timestamp resolved = Timestamp.from(in2Hours);
    private User author = mock(User.class);
    private User resolver = mock(User.class);

    Reimbursement reimbursement = new Reimbursement(
            0,
            100f,
            submitted,
            resolved,
            "description of the reimbursement",
            author.getUser_id(),
            resolver.getUser_id(),
            1,
            1
    );
    ReimbursementDao rd = new ReimbursementDao();

    @Test
    @Order(1)
    public void testCreate(){
        rd.insert(reimbursement);
    }
    @Test
    @Order(2)
    public void testGetById(){
        rd.getById(reimbursement.getId());
    }
    @Test
    @Order(3)
    public void testGetByAll(){
        rd.getList();
    }
    @Test
    @Order(4)
    public void testDelete(){
        rd.delete(reimbursement);
    }
}
