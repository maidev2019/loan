package com.maidev.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maidev.loan.model.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
	
}
