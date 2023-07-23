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

  @ManyToOne
  @JoinColumn(name = "applicant_id")
  private Applicant applicant;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

}
