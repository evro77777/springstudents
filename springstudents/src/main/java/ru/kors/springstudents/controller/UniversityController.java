package ru.kors.springstudents.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kors.springstudents.model.University;
import ru.kors.springstudents.model.UniversityDetails;
import ru.kors.springstudents.service.university.UniversityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/university")
@AllArgsConstructor
public class UniversityController {
    private UniversityService universityService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("all_universities")
    public List<University> findAll() {
        return universityService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<UniversityDetails> findById(@PathVariable("id") Long id) {
        return universityService.findById(id);
    }

}
