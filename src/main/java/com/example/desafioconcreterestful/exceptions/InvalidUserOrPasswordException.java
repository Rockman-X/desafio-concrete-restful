package com.example.desafioconcreterestful.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserOrPasswordException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "Usuário e/ou senha inválidos";

    public InvalidUserOrPasswordException(HttpStatus httpStatus) {

        super(MSG);
    }
}
