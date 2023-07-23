package com.maidev.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maidev.loan.dto.AddressDTO;
import com.maidev.loan.dto.ApplicantDTO;
import com.maidev.loan.dto.LoanRequest;
import com.maidev.loan.dto.LoanResponse;
import com.maidev.loan.model.Address;
import com.maidev.loan.model.Applicant;
import com.maidev.loan.model.Loan;
import com.maidev.loan.repository.AddressRepository;
import com.maidev.loan.repository.ApplicantRepository;
import com.maidev.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class LoanService {
    private final LoanRepository loanRepository; 
    private final AddressRepository addressRepository; 
    private final ApplicantRepository applicantRepository; 

    public void createLoanRequest(LoanRequest loanRequest){
        AddressDTO addressTemp = loanRequest.getAddress();
        Address address = new Address();
        address.setStreet(addressTemp.getStreet());
        address.setStreetAdditionalLine(addressTemp.getStreetAdditionalLine());
        address.setCity(addressTemp.getCity());
        address.setPostalcode(addressTemp.getPostalcode());
        address.setState(addressTemp.getState());
        Address savedAddress = addressRepository.save(address);

        ApplicantDTO applicantTemp = loanRequest.getApplicant();
        Applicant applicant = new Applicant();
        applicant.setFirstname(applicantTemp.getFirstname());
        applicant.setLastname(applicantTemp.getLastname());
        applicant.setEmail(applicantTemp.getEmail());
        
        Applicant savedApplicant = applicantRepository.save(applicant);

        Loan loan = Loan.builder()            
            .desiredAmount(loanRequest.getDesiredAmount())
            .anualIncome(loanRequest.getAnualIncome())
            .usedForType(loanRequest.getUsedForType())
            .address(savedAddress)
            .applicant(savedApplicant)
            .build();
        loanRepository.save(loan);
        log.info("Loan {} is saved!", loan.getId());
    }
    public List<LoanResponse> getAllLoanResponses() {
        return loanRepository.findAll().stream().map(this::mapTopLoanResponse).toList();
    }
    private LoanResponse mapTopLoanResponse(Loan loan) {
        ApplicantDTO applicantDTO = getApplicantDTO(loan.getApplicant());
        AddressDTO addressDTO = getAddressDTO(loan.getAddress());

        return LoanResponse.builder()
        .id(loan.getId())
        .anualIncome(loan.getAnualIncome())
        .desiredAmount(loan.getDesiredAmount())
        .usedForType(loan.getUsedForType())
        .applicant(applicantDTO)
        .address(addressDTO)
        .build();
    }
    private AddressDTO getAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setStreetAdditionalLine(address.getStreetAdditionalLine());
        addressDTO.setCity(address.getCity());
        addressDTO.setPostalcode(address.getPostalcode());
        addressDTO.setState(address.getState());
        return addressDTO;
    }
    private ApplicantDTO getApplicantDTO(Applicant applicant) {
        
        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setFirstname(applicant.getFirstname());
        applicantDTO.setLastname(applicant.getLastname());
        applicantDTO.setEmail(applicant.getEmail());
        return applicantDTO;
    }
}
