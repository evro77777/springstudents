package ru.kors.springstudents.service.students.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.MyUser;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.repository.students.StudentRepository;
import ru.kors.springstudents.repository.myusers.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Primary
public class StudentService implements ru.kors.springstudents.service.students.StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        //todo#1
        Optional<Student> studentOptional = Optional.ofNullable(studentRepository.findByEmail(student.getEmail()));
        Optional<Student> studentOptionalById = studentRepository.findById(student.getId());
        if (studentOptional.isPresent() || studentOptionalById.isPresent()) {
            throw new IllegalStateException("A student  already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if (optionalStudent.isEmpty()) {
            throw new IllegalStateException(String.format("Student with id = %d not found", student.getId()));
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);

    }

    @Override
    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
