package com.example.patientmanagement.service;

import com.example.patientmanagement.model.Visit;
import com.example.patientmanagement.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisitsByPatientId(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }
    public void updateVisit(Visit updatedVisit) {

        // Assuming you have a method to find the existing visit by its ID
        Visit existingVisit = visitRepository.findById(updatedVisit.getId())
                .orElseThrow(() -> new RuntimeException("Visit not found with ID: " + updatedVisit.getId()));

        // Update the fields of the existing visit with the values from the updated visit
        existingVisit.setDateAndTime(updatedVisit.getDateAndTime());
        existingVisit.setVisitType(updatedVisit.getVisitType());
        existingVisit.setReason(updatedVisit.getReason());
        existingVisit.setFamilyHistory(updatedVisit.getFamilyHistory());

        // Save the updated visit back to the database
        visitRepository.save(existingVisit);
    }
    public void createVisit(Visit visit) {
        // Ensure that the patient ID is set before saving
        visitRepository.save(visit);
    }
    public List<String> getAvailableDateTimesForPatient(Long patientId) {
        List<LocalDateTime> dateTimes = visitRepository.findAvailableDateTimesByPatientId(patientId);

        // Convert LocalDateTime to String (you may adjust the format as needed)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTimes.stream()
                .map(formatter::format)
                .collect(Collectors.toList());
    }

}
