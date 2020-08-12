package com.github.mcoder;


import com.github.mcoder.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SampleVertxApplication {

    @Autowired
    private ServerVerticle serverVerticle;

    public static void main(String[] args) {
        SpringApplication.run(SampleVertxApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx vertx = Vertx.factory.vertx();
        vertx.deployVerticle(serverVerticle);

    }

}
