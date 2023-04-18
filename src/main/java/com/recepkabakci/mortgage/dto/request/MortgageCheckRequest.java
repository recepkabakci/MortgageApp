package com.recepkabakci.mortgage.dto.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MortgageCheckRequest {
    @NotNull(message = "income can not be null")
    private double income;
    @NotNull(message = "maturityPeriod can not be null")
    private Integer maturityPeriod;
    @NotNull(message = "loanValue can not be null")
    private double loanValue;
    @NotNull(message = "homeValue can not be null")
    private double homeValue;
}


