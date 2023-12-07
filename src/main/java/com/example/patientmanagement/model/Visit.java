package com.example.patientmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
    public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime dateAndTime;
    private String visitType;
    private String reason;
    private String familyHistory;

        // Constructors, getters, and setters

        public Visit(Long id, Patient patient, LocalDateTime dateAndTime, String visitType, String reason, String familyHistory) {
            this.id = id;
            this.patient = patient;
            this.dateAndTime = dateAndTime;
            this.visitType = visitType;
            this.reason = reason;
            this.familyHistory = familyHistory;
        }

    public Visit() {

    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public LocalDateTime getDateAndTime() {
            return dateAndTime;
        }

        public void setDateAndTime(LocalDateTime dateAndTime) {
            this.dateAndTime = dateAndTime;
        }

        public String getVisitType() {
            return visitType;
        }

        public void setVisitType(String visitType) {
            this.visitType = visitType;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getFamilyHistory() {
            return familyHistory;
        }

        public void setFamilyHistory(String familyHistory) {
            this.familyHistory = familyHistory;
        }
    }


