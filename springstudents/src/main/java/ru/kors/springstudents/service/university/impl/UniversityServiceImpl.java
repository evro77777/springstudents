package ru.kors.springstudents.service.university.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.University;
import ru.kors.springstudents.model.UniversityDetails;
import ru.kors.springstudents.repository.university.UniversityRepository;
import ru.kors.springstudents.repository.university_details.UniversityDetailsRepository;
import ru.kors.springstudents.service.university.UniversityService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private UniversityRepository universityRepository;
    private UniversityDetailsRepository universityDetailsRepository;

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    public Optional<UniversityDetails> findById(Long id) {
        return universityDetailsRepository.findById(id);
    }

}
