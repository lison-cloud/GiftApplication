package by.bsuir.system.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import by.bsuir.system.web.controller.exception.Error;
import by.bsuir.system.web.controller.exception.GiftCertificateNotFoundException;
import by.bsuir.system.web.controller.exception.TagNotFound;

@RestControllerAdvice
public class AppWideExceptionHandler {
    

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {TagNotFound.class, GiftCertificateNotFoundException.class})
    public Error errorHandler(Exception exception) {
        return new Error(404, "Entity not found");
    }

}
