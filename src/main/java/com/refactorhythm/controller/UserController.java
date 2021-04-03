package com.refactorhythm.controller;

import com.google.gson.Gson;
import com.refactorhythm.model.User;
import com.refactorhythm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UserController extends AbstractController{

    private UserService userService = new UserService();


    //TODO: Set statuses and create responses
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("user_id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            if(password!= null && username != null) {
                res.getWriter().println(userService.getUserByLogin(username, password));
                res.setStatus(200);
            } else if (username != null) {
                res.getWriter().println(userService.getUserByUsername(username));
                res.setStatus(200);
            } else if(id != null) {
                Integer intId = Integer.parseInt(id);
                String js = gson.toJson(userService.getUserById(intId));
                res.getWriter().println(js);
                res.setStatus(200);
            } else {
                res.getWriter().println(userService.fetchAllUsers());
                res.setStatus(200);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse res) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            userService.createUser(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(HttpServletRequest req, HttpServletResponse res) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            userService.updateUser(sb.toString());
            res.setStatus(200);
            res.getWriter().println("User information has been updated");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {

        String id = req.getParameter("user_id");

        try {
            if (id == null) {
                res.setStatus(400);
                res.getWriter().println("User with id: " + id + " not found");
            } else {
                userService.deleteUser(Integer.parseInt(id));
                res.getWriter().println("User with id: " + id + "has been successfully deleted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
