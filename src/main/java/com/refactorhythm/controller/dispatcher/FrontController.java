package com.refactorhythm.controller.dispatcher;

import com.refactorhythm.servlet.enumeration.Endpoint;
import com.refactorhythm.servlet.enumeration.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController {

    private Dispatcher dispatcher;

    public FrontController(){
        dispatcher = new Dispatcher();
    }

    public void dispatchRequest(Endpoint endpoint, RequestMethod method, HttpServletRequest req, HttpServletResponse res){
        dispatcher.dispatch(endpoint, method, req, res);
    }
}
