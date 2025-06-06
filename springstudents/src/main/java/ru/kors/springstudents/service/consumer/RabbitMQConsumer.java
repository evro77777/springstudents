package ru.kors.springstudents.service.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQConsumer {
    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"queueLogs","queueLogs_json"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
