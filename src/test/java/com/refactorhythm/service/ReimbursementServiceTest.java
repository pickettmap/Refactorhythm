package com.refactorhythm.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ReimbursementServiceTest {
    ReimbursementService rs = new ReimbursementService();
    String json = "{\n" +
            "    \"id\": 0,\n" +
            "    \"amount\": 100.0,\n" +
            "    \"submitted\": \"Apr 2, 2021 9:28:30 PM\",\n" +
            "    \"resolved\": \"Apr 2, 2021 11:28:30 PM\",\n" +
            "    \"description\": \"description of the reimbursement\",\n" +
            "    \"author\": 0,\n" +
            "    \"resolver\": 0,\n" +
            "    \"status_id\": 1,\n" +
            "    \"type_id\": 1\n" +
            "  }");
    @Test
    @Order(1)
    public void createReimbursementTest() throws Exception {
        rs.createReimbursement(json);
    }

    @Test
    @Order(2)
    public void fetchAllReimbursementsTest(){
        rs.fetchAllReimbursements();
    }

    @Test
    @Order(3)
    public void getReimbursementsByUserIdTest(){
        rs.getReimbursementsByUserID(0);
    }

    @Test
    @Order(4)
    public void updateReimbursementTest() throws Exception {
        rs.updateReimbursement(json);
    }

    @Test
    @Order(5)
    public void deleteReimbursementByIdTest() throws Exception {
        rs.deleteReimbursementById(0);
    }
}
