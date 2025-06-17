package ru.kors.springstudents.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    //высчитываем age на лету
    @Transient
    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }


    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


}
