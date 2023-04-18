package com.recepkabakci.mortgage.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionType {
    UNEXPECTED_ERROR(1000, "Unexpected Error! Please submit a report.", INTERNAL_SERVER_ERROR),

    BAD_REQUEST_ERROR(1001, "Invalid Parameter Error", BAD_REQUEST);


    private int code;
    private String message;
    HttpStatus httpStatus;
}
