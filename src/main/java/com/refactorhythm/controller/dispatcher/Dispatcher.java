package com.refactorhythm.controller.dispatcher;

import com.refactorhythm.controller.ReimbursementController;
import com.refactorhythm.servlet.enumeration.Endpoint;
import com.refactorhythm.servlet.enumeration.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher {
    // declare the controller objects
    ReimbursementController reimbursementController;
    public Dispatcher(){
        // initialize the controllers
        reimbursementController = new ReimbursementController();
    }

    public void dispatch(Endpoint endpoint, RequestMethod method, HttpServletRequest req, HttpServletResponse res){
        switch(endpoint){
            case REIMBURSEMENT: switch(method){

                case GET: reimbursementController.get(req, res);
                    break;
                case POST: reimbursementController.post(req, res);
                    break;
                case PUT: reimbursementController.put(req, res);
                    break;
                case DELETE: reimbursementController.delete(req, res);
                    break;
            }
                break;
            case USER:
                break;
        }
    }
}
