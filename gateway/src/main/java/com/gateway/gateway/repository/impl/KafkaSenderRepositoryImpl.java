package com.gateway.gateway.repository.impl;

import com.gateway.gateway.models.dto.MessageModel;
import com.gateway.gateway.repository.KafkaSenderRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Repository
public class KafkaSenderRepositoryImpl implements KafkaSenderRepository {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
    private KafkaTemplate<String, String> kafkaTemplateModel;
    private KafkaTemplate<String, MessageModel> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.request.topic}")
    private String topicRequest;
    @Value("${kafka.request.message.return}")
    private String messageReturn;
    private Gson gson;

    @Autowired
    public KafkaSenderRepositoryImpl(KafkaTemplate<String, String> kafkaTemplateModel
            , KafkaTemplate<String, MessageModel> kafkaTemplate, Gson gson) {
        this.kafkaTemplateModel = kafkaTemplateModel;
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    @Override
    public void postMessage(MessageModel messageModel) {
        kafkaTemplate.send(topic, messageModel);
        System.out.println(messageModel);
    }

    @Override
    public void getMessageLog(MessageModel messageModel) {
        ListenableFuture<SendResult<String, MessageModel>> future =
                kafkaTemplate.send(topicRequest, messageModel);

        future.addCallback(new ListenableFutureCallback<SendResult<String, MessageModel>>() {

            @Override
            public void onSuccess(SendResult<String, MessageModel> stringMessageModelSendResult) {
                System.out.println("Sent message=[" + messageModel +
                        "] with offset=[" + stringMessageModelSendResult.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + "] due to : " + ex.getMessage());
            }
        });
    }

    @Override
    public ResponseEntity<String> getMessageModel(MessageModel request) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>> result = null;
        try {
            result = kafkaTemplateModel.send(messageReturn, gson.toJson(request));
        }catch (KafkaException ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
    }
}
