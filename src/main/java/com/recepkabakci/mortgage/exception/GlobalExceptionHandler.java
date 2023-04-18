package com.recepkabakci.mortgage.exception;

import com.recepkabakci.mortgage.exception.custom.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.recepkabakci.mortgage.exception.ExceptionType.BAD_REQUEST_ERROR;
import static com.recepkabakci.mortgage.exception.ExceptionType.UNEXPECTED_ERROR;

/*
 * exception handler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * to check maturity period if it exists or not.
     */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
        log.warn("Invalid maturity period! {}", exception.getMessage());
        return createExceptionInfoResponse(BAD_REQUEST_ERROR, exception);
    }

    /*
     * general exception handler for unknown error.
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception) {
        log.error("Unhandled error occurred!", exception);
        return createExceptionInfoResponse(UNEXPECTED_ERROR, exception);
    }

    /*
     * method for create exception response
     */
    private ResponseEntity<ExceptionResponse> createExceptionInfoResponse(ExceptionType exceptionType, Exception exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .exceptionCode(exceptionType.getCode())
                .customMessage(exceptionType.getMessage())
                .exceptionMessage(exception.getMessage())
                .httpStatus(exceptionType.getHttpStatus().value())
                .build(), exceptionType.getHttpStatus());
    }
}
