package com.ame.desafio.starwars.controller.exceptions;

import com.ame.desafio.starwars.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {
    /**
     * @param e   - Exception that must be handled
     * @param req - HttpServletRequest
     * @return Default json error to handle {@link ObjectNotFoundException}
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    public StandardError objectNotFoundException(ObjectNotFoundException e, HttpServletRequest req) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return err;
    }

    /**
     * @param e   - Exception that must be handled
     * @param req - HttpServletRequest
     * @return Default json error to handle {@link MethodArgumentNotValidException}
     */
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação.", System.currentTimeMillis());

        for (FieldError fe : e.getBindingResult().getFieldErrors()) {
            err.addError(fe.getField(), fe.getDefaultMessage());
        }

        return err;
    }*/
    /**
     * @param e   - Exception that must be handled
     * @param req - HttpServletRequest
     * @return Default json error to handle {@link DatabaseException}
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DatabaseException.class)
    @ResponseBody
    public StandardError dataBaseException(DatabaseException e, HttpServletRequest req) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return err;
    }
    /**
     * @param e - Exception that must be handled
     * @param req - HttpServletRequest
     * @return Default json error to handle {@link ConstraintViolationException}
     * */
    /*@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError constraintViolationException(ConstraintViolationException e, HttpServletRequest req){
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação.", System.currentTimeMillis());

        for(ConstraintViolation ce : e.getConstraintViolations()){
            String field = ce.getPropertyPath().toString();
            int initPosition = field.lastIndexOf(".");
            field = field.substring(++initPosition);
            err.addError(field, ce.getMessage());
        }

        return err;
    }*/
    /**
     * @param e   - Exception that must be handled
     * @param req - HttpServletRequest
     * @return Default json error to handle {@link ServiceIntegrationException}
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceIntegrationException.class)
    @ResponseBody
    public StandardError serviceIntegrationException(ServiceIntegrationException e, HttpServletRequest req) {
        StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
        return err;
    }
}
