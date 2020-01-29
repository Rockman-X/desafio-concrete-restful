package com.example.desafioconcreterestful.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidSessionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "Sessão Inválida.";

    public InvalidSessionException(HttpStatus httpStatus) {

        super(MSG);

    }
}
