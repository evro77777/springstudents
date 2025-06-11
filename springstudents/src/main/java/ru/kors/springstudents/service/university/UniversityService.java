package ru.kors.springstudents.service.university;

import ru.kors.springstudents.model.University;
import ru.kors.springstudents.model.UniversityDetails;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    List<University> findAll();

    Optional<UniversityDetails> findById(Long id);
}
