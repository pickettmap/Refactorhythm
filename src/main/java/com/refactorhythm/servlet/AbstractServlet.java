package com.refactorhythm.servlet;

import com.refactorhythm.controller.dispatcher.FrontController;

import javax.servlet.http.HttpServlet;

/**
 * This is a set of abstractions for the servlets to ensure they all have a frontController instance.
 */
public class AbstractServlet extends HttpServlet {
    FrontController frontController = new FrontController();
}
