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
    Gson gson = new Gson();

    @Override
    public void get(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("user_id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            if(password!= null && username != null) {
                res.getWriter().println(userService.getUserByLogin(username, password));
            } else if (username != null) {
                res.getWriter().println(userService.getUserByUsername(username));
            } else if(id != null) {
                Integer intId = Integer.parseInt(id);
                String js = gson.toJson(userService.getUserById(intId));
                res.getWriter().println(js);
            } else {
                res.getWriter().println(userService.fetchAllUsers());
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

    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {

    }
}
