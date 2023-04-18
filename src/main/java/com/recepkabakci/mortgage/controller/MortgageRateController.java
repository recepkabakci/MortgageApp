package com.recepkabakci.mortgage.controller;

import com.recepkabakci.mortgage.dto.request.MortgageCheckRequest;
import com.recepkabakci.mortgage.dto.response.MortgageCheckResponse;
import com.recepkabakci.mortgage.model.MortgageRate;
import com.recepkabakci.mortgage.service.MortgageRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class MortgageRateController {
    private final MortgageRateService mortgageRateService;

    /*
     *to get all objects of MortgageRate class.
     */
    @GetMapping("/interest-rates")
    public List<MortgageRate> findAll() {
        return mortgageRateService.findAll();
    }

    /*
     *to check feasibly and to get monthly cost of mortgage.
     */
    @PostMapping("/mortgage-check")
    public ResponseEntity<MortgageCheckResponse> checkMortgage(@RequestBody MortgageCheckRequest request) {
        return ResponseEntity.ok(mortgageRateService.checkMortgage(request));
    }


}
