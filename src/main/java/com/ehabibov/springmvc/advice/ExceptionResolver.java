package com.ehabibov.springmvc.advice;

import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ExceptionInfo;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionInfo resourceNotFoundHandler(HttpServletRequest request, ResourceNotFoundException ex) {
        ExceptionInfo info = new ExceptionInfo();
        info.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setUrl(request.getRequestURL().toString());
        info.setMessage(String.format("Resource not found: %s", ex.getMessage()));
        return info;
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionInfo entityAlreadyExistHandler(HttpServletRequest request, EntityAlreadyExistException ex){
        ExceptionInfo info = new ExceptionInfo();
        info.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setUrl(request.getRequestURL().toString());
        info.setMessage(String.format("Entity exists: %s", ex.getMessage()));
        return info;
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionInfo fileNotFoundHandler(HttpServletRequest request, FileNotFoundException ex){
        ExceptionInfo info = new ExceptionInfo();
        info.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setUrl(request.getRequestURL().toString());
        info.setMessage(String.format("File not found: %s", ex.getMessage()));
        return info;
    }

}
