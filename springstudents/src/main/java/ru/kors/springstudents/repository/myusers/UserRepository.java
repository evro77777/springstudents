package ru.kors.springstudents.repository.myusers;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.springstudents.model.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByName(String name);
}
