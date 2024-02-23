package com.example.mangatranslator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${exchange.create-recipe}")
    private String exchangeCreate;
    @Value("${routing-key.create-recipe}")
    private String routingKeyCreate;

    @Value("${exchange.update-recipe}")
    private String updateExchange;
    @Value("${routing-key.update-recipe}")
    private String routingKey_update;

    @Value("${exchange.remove-recipe}")
    private String removeExchange;
    @Value("${routing-key.remove-recipe}")
    private String routingKey_remove;

    public void sendCreateMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeCreate,routingKeyCreate,message);
        log.info(" [x] Sent to create queue: '" + message + "'");
    }

    public void sendUpdateMessage(String message) {
        rabbitTemplate.convertAndSend(updateExchange,routingKey_update,"hello world! update");
        log.info(" [x] Sent to create queue: '" + message + "'");
    }

    public void sendRemoveMessage(String message) {
        rabbitTemplate.convertAndSend(removeExchange,routingKey_remove,"hello world! remove");
        log.info(" [x] Sent to create queue: '" + message + "'");
    }

}
