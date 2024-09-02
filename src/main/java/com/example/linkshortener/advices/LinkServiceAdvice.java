package com.example.linkshortener.advices;

import com.example.linkshortener.exceptions.LinkServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class LinkServiceAdvice {

    @ExceptionHandler(LinkServiceException.class)
    public ResponseEntity<String> handleLinkServiceException(LinkServiceException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
