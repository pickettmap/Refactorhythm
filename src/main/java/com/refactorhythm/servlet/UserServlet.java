package com.refactorhythm.servlet;

import com.refactorhythm.servlet.enumeration.Endpoint;
import com.refactorhythm.servlet.enumeration.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends AbstractServlet {

    /**
     * This handles all get requests for User.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.GET, req, res);
    }

    /**
     * This handles all post requests for User.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.POST, req, res);
    }

    /**
     * This handles all put requests for User.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.PUT, req, res);
    }

    /**
     * This handles all delete requests for User.
     * @param req the user request.
     * @param res the response to be sent to the user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.DELETE, req, res);
    }
}
