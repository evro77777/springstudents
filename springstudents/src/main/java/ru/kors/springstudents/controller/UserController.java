package ru.kors.springstudents.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kors.springstudents.model.MyUser;
import ru.kors.springstudents.service.myusers.MyUserDetailsService;

@RestController
@AllArgsConstructor
public class UserController {
    private MyUserDetailsService userDetailsService;

    @PostMapping("/api/v1/users/registration")
    public String addUser(@RequestBody MyUser user) {
        userDetailsService.addUser(user);
        return "User  is saved";
    }
}
