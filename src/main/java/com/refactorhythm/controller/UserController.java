package com.refactorhythm.controller;

import com.google.gson.Gson;
import com.refactorhythm.model.User;
import com.refactorhythm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

public class UserController extends AbstractController{

    private UserService userService = new UserService();


    /**
     * Retrieves corresponding user(s) based on request parameters
     * @param req text  Can request a specific user based on user_id, username, or username and password
     *                  No parameters will request all users
     * @param res text  User data written to response as json. Status code set to 200 for
     *                  success or 400 for failure.
     */
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res) {
        Enumeration<String > params = req.getParameterNames();
        String id = req.getParameter("user_id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            if(params.hasMoreElements()) {
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
                    res.setStatus(400);
                }
            }
             else {
                res.getWriter().println(userService.fetchAllUsers());
                res.setStatus(200);
            }

        } catch (IOException e) {
            res.setStatus(400);
            e.printStackTrace();
        }
    }

    /**
     * Adds user to database from json
     * @param req text  Json text representing new user data
     * @param res text  Sets status code to 201 if user was successfully added, 400 otherwise
     */
    @Override
    public void post(HttpServletRequest req, HttpServletResponse res) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            userService.createUser(sb.toString());
            res.setStatus(201);
        } catch (IOException e) {
            res.setStatus(400);
            e.printStackTrace();
        }
    }

    /**
     * Updates user in database from json
     * @param req text  Json text representing user data. user_id should correspond to an
     *                  existing user. User is updated based on parameter data
     * @param res text  Sets status code to 201 if user was successfully updated, 400 otherwise
     */
    @Override
    public void put(HttpServletRequest req, HttpServletResponse res) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            userService.updateUser(sb.toString());
            res.setStatus(201);
        } catch (IOException e) {
            res.setStatus(400);
            e.printStackTrace();
        }

    }

    /**
     * Deletes an existing user from database
     * @param req text  This request needs a user_id parameter to specify which user should
     *                  be deleted
     * @param res text  Sets status code to 200 if user was successfully found and deleted, 400 otherwise
     */
    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res) {

        String id = req.getParameter("user_id");

        try {
            if (id == null) {
                res.setStatus(400);
            } else {
                userService.deleteUser(Integer.parseInt(id));
                res.setStatus(200);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
