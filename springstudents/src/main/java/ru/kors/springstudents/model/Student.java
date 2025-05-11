package ru.kors.springstudents.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Column(name="id", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="email",unique = true, nullable = false)
    private String email;
    @Column(name="age")
    private Integer age;


}
