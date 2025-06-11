package ru.kors.springstudents.repository.university;

import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kors.springstudents.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {


}
