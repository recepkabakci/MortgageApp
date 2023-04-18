package com.recepkabakci.mortgage.dto.response;

import lombok.Data;

@Data
public class  MortgageCheckResponse {

    private boolean feasible;
    private double monthlyCost;
}
