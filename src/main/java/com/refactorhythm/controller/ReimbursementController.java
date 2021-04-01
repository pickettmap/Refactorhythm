package com.refactorhythm.controller;

import com.refactorhythm.service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ReimbursementController extends AbstractController {

    ReimbursementService reimbursementService = new ReimbursementService();

    @Override
    public void get(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("user-id");
        try{
            if(id == null){     //fetchAllReimbursements()
                res.getWriter().println(gson.toJson(reimbursementService.fetchAllReimbursements()));
            } else{             // getReimbursementsByUserId(int id)
                res.getWriter().println(gson.toJson(reimbursementService.getReimbursementsByUserID(Integer.parseInt(id))));
            }
        }catch(IOException e){
            e.printStackTrace();
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
        } catch(IOException e){
            e.printStackTrace();
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
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        try{
            if(id == null){     //fetchAllReimbursements()
                res.setStatus(400);
                res.getWriter().println("Id not found");
            } else{             // getReimbursementsByUserId(int id)
                reimbursementService.deleteReimbursementById(Integer.parseInt(id));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
