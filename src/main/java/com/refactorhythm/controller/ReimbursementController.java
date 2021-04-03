package com.refactorhythm.controller;

import com.refactorhythm.service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

public class ReimbursementController extends AbstractController {

    ReimbursementService reimbursementService = new ReimbursementService();
    String methodNotFoundError = "{\"error\":\"Method not found to handle your request. Check your parameters.\"}";
    String idNotFoundError = "{\"error\":\"Id not found.\"}";
    String success = "{\"message\":\"success.\"}";

    @Override
    public void get(HttpServletRequest req, HttpServletResponse res) {

        Enumeration<String> params = req.getParameterNames();
        res.setContentType("application/json");
        try{
            if(params.hasMoreElements()){ // they are sending parameters
                String id = req.getParameter("user-id");
                if(id != null && !id.equals("")){
                    res.setStatus(200);
                    res.getWriter().println(gson.toJson(reimbursementService.getReimbursementsByUserID(Integer.parseInt(id))));
                } else{
                    res.setStatus(400);
                    res.getWriter().println(methodNotFoundError);
                }
            } else { // they are sending a fetchAll
                res.setStatus(200);
                res.getWriter().println(gson.toJson(reimbursementService.fetchAllReimbursements()));
            }
        } catch (IOException|NumberFormatException e) {
            res.setStatus(400);
            try {
                res.getWriter().println(methodNotFoundError);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse res) {
        //createReimbursement(String json)

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()){
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reimbursementService.createReimbursement(sb.toString());
            //TODO: set different status code if it errors out
            res.setStatus(201);
            res.getWriter().println(success);
        } catch(Exception e){
            res.setStatus(400);
            try {
                res.getWriter().println(methodNotFoundError);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    @Override
    public void put(HttpServletRequest req, HttpServletResponse res) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()){
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reimbursementService.updateReimbursement(sb.toString());
            //TODO: set different status code if it errors out
            res.setStatus(201);
            res.getWriter().println(success);
        } catch(Exception e){
            res.setStatus(400);
            try {
                res.getWriter().println(methodNotFoundError);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        try{
            if(id == null){     //fetchAllReimbursements()
                res.setStatus(400);
                res.getWriter().println(methodNotFoundError);
            } else{             // getReimbursementsByUserId(int id)
                reimbursementService.deleteReimbursementById(Integer.parseInt(id));
                res.setStatus(200);
                res.getWriter().println(success);
            }
        }catch(Exception e){
            res.setStatus(400);
            try {
                res.getWriter().println(idNotFoundError);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
