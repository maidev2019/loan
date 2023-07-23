package com.maidev.loan.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@Entity
@Table(name="loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Double desiredAmount;

    @Column
    private Double anualIncome;

    @Enumerated(EnumType.STRING)	
    @Column
    private LoanType usedForType;

    

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "loan_applicant", 
      joinColumns = { @JoinColumn(name = "loan_id", referencedColumnName = "id") },
      inverseJoinColumns = { @JoinColumn(name = "applicant_id", referencedColumnName = "id") })
    private Applicant applicant;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "loan_address", 
      joinColumns = { @JoinColumn(name = "loan_id", referencedColumnName = "id") },
      inverseJoinColumns = { @JoinColumn(name = "address_id", referencedColumnName = "id") })
    private Address address;


}
