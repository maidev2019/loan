package com.maidev.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maidev.loan.dto.LoanRequest;
import com.maidev.loan.dto.LoanResponse;
import com.maidev.loan.model.Loan;
import com.maidev.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class LoanService {
    private final LoanRepository loanRepository; 
    public void createLoanRequest(LoanRequest loanRequest){
        Loan loan = Loan.builder()
            .desiredAmount(loanRequest.getDesiredAmount())
            .anualIncome(loanRequest.getAnualIncome())
            .usedForType(loanRequest.getUsedForType())
            .build();
        loanRepository.save(loan);
        log.info("Loan {} is saved!", loan.getId());
    }
    public List<LoanResponse> getAllLoanResponses() {
        return loanRepository.findAll().stream().map(this::mapTopLoanResponse).toList();
    }
    private LoanResponse mapTopLoanResponse(Loan loan) {
        return LoanResponse.builder()
        .anualIncome(loan.getAnualIncome())
        .desiredAmount(loan.getDesiredAmount())
        .usedForType(loan.getUsedForType())
        .id(loan.getId()).build();
    }
}
