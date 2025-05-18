package ru.kors.springstudents.repository.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kors.springstudents.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value="select * from students where email = :email", nativeQuery = true)
    Student findByEmail(String email);

    @Query(value="DELETE from students where email =:email", nativeQuery = true)
    void deleteByEmail(String email);

}
