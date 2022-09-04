package io.github.moaresoliveira.orangenotesapi.controller;

import io.github.moaresoliveira.orangenotesapi.exception.CollectionNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({CollectionNotFoundException.class})
    public String handleCollectionNotFoundException(CollectionNotFoundException e) {
        return e.getMessage();
    }

}
