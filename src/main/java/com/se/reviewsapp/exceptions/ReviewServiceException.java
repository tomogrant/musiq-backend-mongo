package com.se.reviewsapp.exceptions;

public class ReviewServiceException extends RuntimeException{
    public ReviewServiceException(String message) {
        super(message);
    }
}
