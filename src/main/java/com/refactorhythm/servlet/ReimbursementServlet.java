package com.refactorhythm.servlet;

import com.refactorhythm.servlet.enumeration.Endpoint;
import com.refactorhythm.servlet.enumeration.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reimbursement")
public class ReimbursementServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.GET, req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.POST, req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.PUT, req, res);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.DELETE, req, res);
    }
}
