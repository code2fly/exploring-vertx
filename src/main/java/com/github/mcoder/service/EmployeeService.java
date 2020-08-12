package com.github.mcoder.service;

import com.github.mcoder.verticles.ServerVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public void getEmployeeById(RoutingContext routingContext) {
        String employeeId = routingContext.request().getParam("id");
        Employee employee = new Employee(employeeId, "Lalu");
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(employee));
    }


    @Getter
    static class Employee {
        private String id;
        private String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

    }
}
