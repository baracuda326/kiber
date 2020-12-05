package com.statistics.statistics.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.statistics.statistics.models.MessageModel;
import com.statistics.statistics.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumerMessage {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
    private Gson gson;
    private ObjectMapper objectMapper;
    private final static String TOPIC = "message-alpha";
    private final static String TOPIC_REQUEST = "message-alpha-request";
    private final static String MESSAGE_RETURN = "message-return";
    private final StatisticRepository statisticRepository;

    @Autowired
    public KafkaConsumerMessage(Gson gson, ObjectMapper objectMapper, StatisticRepository statisticRepository) {
        this.gson = gson;
        this.objectMapper = objectMapper;
        this.statisticRepository = statisticRepository;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_json")
    public void consume(String message) throws JsonProcessingException {
        MessageModel messageModel = objectMapper.readValue(message, MessageModel.class);
        System.out.println("Consumed message: " + messageModel);
//        messageFirstDatabaseRepository.save(MessageEntityFirstDatabase.builder()
//                .message(messageModel.getMessageContent())
//                .date(LocalDateTime.now().format(dateTimeFormatter))
//                .build());
    }
}
