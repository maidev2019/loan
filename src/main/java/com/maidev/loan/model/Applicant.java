package com.maidev.loan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @Column 
    private String title;

    @Column
    private String lastname;
    
    @Column
    private String firstname;

    @Column
    private String email;

    @OneToOne    
    private Address address;
}