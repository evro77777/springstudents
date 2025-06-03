package ru.kors.springstudents.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kors.springstudents.model.MyUser;
import ru.kors.springstudents.model.Person;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private final RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Student student) {
        jsonProducer.sendJsonMessage(student);
        return ResponseEntity.ok("Json message sent to RabbitMQ...");
    }
}
