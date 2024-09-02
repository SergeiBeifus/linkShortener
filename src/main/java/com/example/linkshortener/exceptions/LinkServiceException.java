package com.example.linkshortener.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LinkServiceException extends RuntimeException {
    private final String message;

    public LinkServiceException(LinkErrors linkErrors) {
        this.message = linkErrors.getMessage();
    }
}
