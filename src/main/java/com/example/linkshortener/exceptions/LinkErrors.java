package com.example.linkshortener.exceptions;

import lombok.Getter;

@Getter
public enum LinkErrors {
    ORIGINAL_LINK_INVALID("Original link is invalid"),
    LINK_NOT_FOUND("Link not found"),
    ;

    private final String message;

    LinkErrors(String message) {
        this.message = message;
    }
}

