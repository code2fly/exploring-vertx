package com.github.mcoder.verticles;

import com.github.mcoder.SampleVertxApplication;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Logger;

public class HelloWorldVerticle extends AbstractVerticle {

    private static final Logger logger = Logger.getLogger(HelloWorldVerticle.class.getName());


// this method is called by vertx instance when this verticle is deployed
    public void start(Promise<Void> startPromise) throws Exception {
        logger.info("Welcome to vertx");
        System.out.println("Hello world from Verticle");

        Router router = Router.router(vertx);
        router.get("/api/employee/:id")
                .handler(this::getEmployeeById);


        vertx.createHttpServer()
                .requestHandler(httpConnection -> router.handle(httpConnection))
                .listen(9090, result -> {
                    if (result.succeeded()) {
                        startPromise.complete();
                    }
                    else {
                        startPromise.fail(result.cause());
                    }
                });


    }



    public void stop(Promise<Void> stopPromise) throws Exception {
        logger.info("Shutting down verticle :  "+ getClass().getName());
    }


    private  void getEmployeeById(RoutingContext routingContext) {
        String employeeId = routingContext.request().getParam("id");
        Employee employee = new Employee(employeeId, "Lalu");
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(employee));
    }

    static class Employee {
        private String id;
        private String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
