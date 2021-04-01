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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.POST, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.PUT, req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        frontController.dispatchRequest(Endpoint.USER, RequestMethod.DELETE, req, resp);
    }
}
