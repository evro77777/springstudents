package ru.kors.springstudents.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person  {
    private Integer id;
    private String firstName;
    private String lastName;
}
