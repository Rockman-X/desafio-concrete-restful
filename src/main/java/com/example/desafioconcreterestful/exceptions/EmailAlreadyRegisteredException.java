package com.example.desafioconcreterestful.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "E-mail já existente";

    public EmailAlreadyRegisteredException(HttpStatus httpStatus) {

        super(MSG);
    }
}
