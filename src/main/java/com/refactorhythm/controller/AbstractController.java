package com.refactorhythm.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a set of abstractions for the controllers in order to ensure they all have some base functionality.
 */
public abstract class AbstractController {
    Gson gson;
    GsonBuilder builder;

    public AbstractController(){
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public abstract void get(HttpServletRequest req, HttpServletResponse res);

    public abstract void post(HttpServletRequest req, HttpServletResponse res);

    public abstract void put(HttpServletRequest req, HttpServletResponse res);

    public abstract void delete(HttpServletRequest req, HttpServletResponse res);
}
