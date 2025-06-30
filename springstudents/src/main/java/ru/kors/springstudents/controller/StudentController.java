package ru.kors.springstudents.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.students.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to start page...";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("all_students")
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("save_student")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable("email") String email) {
        return studentService.findByEmail(email);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("update_student")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete_student/{email}")
    public void deleteStudent(@PathVariable("email") String email) {
        studentService.deleteStudent(email);
    }


}
