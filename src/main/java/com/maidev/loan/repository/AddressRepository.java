package com.maidev.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maidev.loan.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
