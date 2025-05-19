package ru.kors.springstudents.service.students.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.MyUser;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.repository.students.InMemoryStudentDAO;
import ru.kors.springstudents.repository.myusers.UserRepository;
import ru.kors.springstudents.service.students.StudentService;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryStudentService implements StudentService {
    private final InMemoryStudentDAO inMemoryStudentDAO;


    @Override
    public List<Student> findAllStudent() {
        return inMemoryStudentDAO.findAllStudent();
    }

    @Override
    public Student saveStudent(Student student) {
        return inMemoryStudentDAO.saveStudent(student);
    }

    @Override
    public Student findByEmail(String email) {
        return inMemoryStudentDAO.findByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        return inMemoryStudentDAO.updateStudent(student);
    }

    @Override
    public void deleteStudent(String email) {
        inMemoryStudentDAO.deleteStudent(email);

    }

}



