package com.recepkabakci.mortgage.service;

import com.recepkabakci.mortgage.dto.request.MortgageCheckRequest;
import com.recepkabakci.mortgage.dto.response.MortgageCheckResponse;
import com.recepkabakci.mortgage.exception.custom.BadRequestException;
import com.recepkabakci.mortgage.model.MortgageRate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MortgageRateService {

    private final Map<Integer, MortgageRate> mortgageRateList = new HashMap<>();

    MortgageRateService() {
        mortgageRateList.put(60, new MortgageRate(60, 3.02, new Date()));
        mortgageRateList.put(120, new MortgageRate(120, 3.81, new Date()));
        mortgageRateList.put(180, new MortgageRate(180, 4.61, new Date()));
        mortgageRateList.put(360, new MortgageRate(360, 5.51, new Date()));
    }

    /*
     * Find all interest rates
     * @return list of mortgage rates
     */
    public List<MortgageRate> findAll() {
        return new ArrayList<>(mortgageRateList.values());
    }

    /*
    * Check mortgage
    * @param request mortgage check request
    * @return  result of mortgage check

    * @formula =>> M = P * r * (1 + r)^n / ((1 + r)^n - 1)
      M = is the total monthly payment (mortgageValue)
      P = is the total amount of your loan (loanValue)
      r = is the monthly interest rate (i / 12)
      i = is the annual interest rate (mortgageRate)
      n = is the number of monthly payments (maturity period * 12)

    *Income is treated as annual income.
    *totalMortgageValue is treated as an interest payment of the loanValue.
    */
    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {

        MortgageRate mortgageRate = mortgageRateList.get(request.getMaturityPeriod());
        if (mortgageRate == null) {
            throw new BadRequestException("Invalid maturity period");
        }
        double interestRate = mortgageRate.getInterestRate() / 100 / 12; // monthly interest rate
        int numPayments = request.getMaturityPeriod() ; // number of monthly payments
        double principal = request.getLoanValue(); // principal amount

        double mortgageMonthlyValue = principal * interestRate * Math.pow(1 + interestRate, numPayments) / (Math.pow(1 + interestRate, numPayments) - 1);

        double totalMortgageValue = mortgageMonthlyValue * numPayments;

        MortgageCheckResponse response = new MortgageCheckResponse();
        response.setMonthlyCost(mortgageMonthlyValue);
        response.setFeasible(true);

        if (totalMortgageValue > (request.getIncome() * 4) || totalMortgageValue > (request.getHomeValue())) {
            response.setFeasible(false);
        }


        return response;
    }

}
