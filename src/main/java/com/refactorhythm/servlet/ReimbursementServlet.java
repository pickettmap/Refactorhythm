package com.refactorhythm.servlet;

import com.refactorhythm.servlet.enumeration.Endpoint;
import com.refactorhythm.servlet.enumeration.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handles receiving requests to the Reimbursement endpoint.
 * @author Brandon Pinkerton
 * @version 1.0
 */
@WebServlet(urlPatterns = "/reimbursement")
public class ReimbursementServlet extends AbstractServlet {

    /**
     * This handles all get requests for Reimbursements.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.GET, req, res);
    }

    /**
     * This handles all post requests for Reimbursements.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.POST, req, res);
    }

    /**
     * This handles all put requests for Reimbursements.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.PUT, req, res);
    }

    /**
     * This handles all delete requests for Reimbursements.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.REIMBURSEMENT, RequestMethod.DELETE, req, res);
    }
}
