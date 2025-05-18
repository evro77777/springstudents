package ru.kors.springstudents.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.repository.StudentRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Primary
public class StudentService implements ru.kors.springstudents.service.StudentService {
    private final StudentRepository repository;

    @Override
    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        //todo#1
        Optional<Student> studentOptional = Optional.ofNullable(repository.findByEmail(student.getEmail()));
        Optional<Student> studentOptionalById = repository.findById(student.getId());
        System.out.println("test ...."+ studentOptional + "_____"+ studentOptionalById);
        if (studentOptional.isPresent() || studentOptionalById.isPresent()) {
            throw new IllegalStateException("A student  already exists");
        }
        return repository.save(student);
    }

    @Override
    public Student findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> optionalStudent = repository.findById(student.getId());
        if (optionalStudent.isEmpty()) {
            throw new IllegalStateException(String.format("Student with id = %d not found", student.getId()));
        }
        return repository.save(student);
    }

    @Override
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);

    }
}
