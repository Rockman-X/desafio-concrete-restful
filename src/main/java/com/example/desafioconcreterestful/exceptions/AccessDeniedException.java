package com.example.desafioconcreterestful.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String MSG = "Acesso não autorizado.";

    public AccessDeniedException(HttpStatus httpStatus) {

        super(MSG);
    }


}
