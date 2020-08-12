package com.github.mcoder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleConsumerService extends AbstractVerticle {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void start() throws Exception {
        vertx.eventBus()
                .<String>consumer("GET_ALL_PEOPLE")
                .handler(getAllPeopleService(peopleService));
    }


    private Handler<Message<String>> getAllPeopleService(PeopleService peopleService) {
        return msg -> vertx.<String>executeBlocking( promise -> {
            try {
                promise.complete(mapper.writeValueAsString(peopleService.getAllPeople()));
            } catch (JsonProcessingException e) {
                promise.fail(e);
            }
        }, result -> {
            if (result.succeeded()){
                msg.reply(result.result());
            }
            else {
                msg.reply(result.cause().toString());
            }
        });
    }



}
