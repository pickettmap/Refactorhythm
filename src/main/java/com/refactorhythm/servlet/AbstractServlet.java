package com.refactorhythm.servlet;

import com.refactorhythm.controller.dispatcher.FrontController;

import javax.servlet.http.HttpServlet;

public class AbstractServlet extends HttpServlet {
    FrontController frontController = new FrontController();
}
