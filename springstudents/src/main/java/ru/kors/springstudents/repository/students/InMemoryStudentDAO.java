package ru.kors.springstudents.repository.students;

import org.springframework.stereotype.Repository;
import ru.kors.springstudents.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Repository
public class InMemoryStudentDAO {
    private final List<Student> STUDENTS = new ArrayList<>();


    public List<Student> findAllStudent() {
        return STUDENTS;
    }

    public Student saveStudent(Student student) {
        if (STUDENTS.stream().anyMatch(elem -> elem.getEmail().equals(student.getEmail()))){
            System.out.printf("A student with this email %s already exists", student.getEmail());
            return null;
        }
        STUDENTS.add(student);
        return student;
    }


    public Student findByEmail(String email) {
        return STUDENTS.stream()
                .filter(elem -> elem.getEmail().equals(email))
                .findFirst()
                .orElse(null);

    }


    public Student updateStudent(Student student) {
        var studentIndex = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getEmail().equals(student.getEmail()))
                .findFirst()
                .orElse(-1);
        System.out.println("studentIndex="+studentIndex);
        if (studentIndex > -1) {
            STUDENTS.set(studentIndex, student);
            return student;
        }
        return null;
    }


    public String deleteStudent(String email) {
        var student = findByEmail(email);
        if (student != null) {
            STUDENTS.remove(student);
            return String.format("Student %s deleted", email);
        }
        return "Student not found";
    }
}
