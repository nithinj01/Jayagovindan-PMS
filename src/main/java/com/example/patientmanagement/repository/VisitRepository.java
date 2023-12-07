package com.example.patientmanagement.repository;

import com.example.patientmanagement.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatientId(Long patientId);

    // Custom query method to get available date and time for the selected patient
    @Query("SELECT v.dateAndTime FROM Visit v WHERE v.patient.id = :patientId")
    List<LocalDateTime> findAvailableDateTimesByPatientId(@Param("patientId") Long patientId);

}

