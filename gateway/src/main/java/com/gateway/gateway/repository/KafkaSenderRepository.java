package com.gateway.gateway.repository;

import com.gateway.gateway.models.dto.MessageModel;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface KafkaSenderRepository {
    void postMessage(MessageModel messageModel);

    void getMessageLog(MessageModel messageModel);

    ResponseEntity<String> getMessageModel(MessageModel messageModel) throws ExecutionException
            , InterruptedException, IOException;
}
