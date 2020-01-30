package com.example.desafioconcreterestful.controller;

import com.example.desafioconcreterestful.exceptions.AccessDeniedException;
import com.example.desafioconcreterestful.exceptions.EmailAlreadyRegisteredException;
import com.example.desafioconcreterestful.exceptions.InvalidSessionException;
import com.example.desafioconcreterestful.exceptions.InvalidUserOrPasswordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidUserOrPasswordException.class })
    protected ResponseEntity<Object> handleInvalidUserOrPasswordException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Usuário e/ou senha inválidos.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { EmailAlreadyRegisteredException.class })
    protected ResponseEntity<Object> handleEmailAlreadyRegisteredException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "E-mail já existente.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDeniedException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Acesso não autorizado.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = { InvalidSessionException.class })
    protected ResponseEntity<Object> handleInvalidSessionException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sessão inválida.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

}
