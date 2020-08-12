package com.github.mcoder.service;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void getStudentById(RoutingContext routingContext) {
        String studentId = routingContext.request().getParam("id");
        Student student = new Student(studentId, "Lalu", 56);
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(student));
    }


    @Getter
    static class Student {
        private String id;
        private String name;
        private int age;

        public Student(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

    }
}
