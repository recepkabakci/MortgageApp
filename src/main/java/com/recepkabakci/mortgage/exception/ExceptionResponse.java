package com.recepkabakci.mortgage.exception;

import lombok.*;

@Builder
@Getter
@Setter
public class ExceptionResponse {
    private int exceptionCode;
    private String customMessage;
    private String exceptionMessage;
    private int httpStatus;
}
