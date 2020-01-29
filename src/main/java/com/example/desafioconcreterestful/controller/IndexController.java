package com.example.desafioconcreterestful.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Desafio Concrete - Restful API";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
