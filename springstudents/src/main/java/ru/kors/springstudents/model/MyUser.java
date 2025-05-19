package ru.kors.springstudents.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String roles;

}
