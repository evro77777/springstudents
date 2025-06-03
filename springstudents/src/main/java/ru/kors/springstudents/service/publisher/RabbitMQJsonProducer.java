package ru.kors.springstudents.service.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.MyUser;
import ru.kors.springstudents.model.Person;
import ru.kors.springstudents.model.Student;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final AmqpTemplate amqpTemplate;

    public RabbitMQJsonProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendJsonMessage(Student student) {
        LOGGER.info(String.format("Json message sent -> %s", student.toString()));
        amqpTemplate.convertAndSend(exchange, routingJsonKey, student);
    }


}
