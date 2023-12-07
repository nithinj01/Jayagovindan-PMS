package com.example.patientmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
    public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String socialSecurityNumber;


    // Constructors, getters, and setters

    public Patient(){

    }
        public Patient(Long id, String name, String surname, LocalDate dateOfBirth, String socialSecurityNumber) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.dateOfBirth = dateOfBirth;
            this.socialSecurityNumber = socialSecurityNumber;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getSocialSecurityNumber() {
            return socialSecurityNumber;
        }

        public void setSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
        }


}

