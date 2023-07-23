package com.maidev.loan.dto;

import com.maidev.loan.model.LoanType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    private Double desiredAmount;
    private Double anualIncome;
    private LoanType usedForType;
    private ApplicantRequest applicant;
    private AddressRequest address;
}
