package com.github.mcoder.verticles;

import com.github.mcoder.service.EmployeeService;
import com.github.mcoder.service.StudentService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Slf4j
@Component
public class ServerVerticle extends AbstractVerticle {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StudentService studentService;


// this method is called by vertx instance when this verticle is deployed
    public void start(Promise<Void> startPromise) throws Exception {
        log.info("deploying {} verticle ", getClass().getName());
//        this is actual router setup , something that DispatcherServlet or a web.xml does mapping servlet routes to servlet
        Router router = Router.router(vertx);
        router.get("/api/employee/:id")
                .handler(employeeService::getEmployeeById);

        router.get("/api/student/:id")
                .handler(studentService::getStudentById);


        vertx.createHttpServer()
                .requestHandler(httpServerRequest -> router.handle(httpServerRequest))
                .listen(9090, result -> {
                    if (result.succeeded()) {
                        log.info("verticle deployment successfull");
                        startPromise.complete();
                    }
                    else {
                        log.info("verticle deployment failed , error : {}", result.cause());
                        startPromise.fail(result.cause());
                    }
                });


    }



    public void stop(Promise<Void> stopPromise) throws Exception {
        log.info("Shutting down verticle :  "+ getClass().getName());
    }


   /* private  void getEmployeeById(RoutingContext routingContext) {
        String employeeId = routingContext.request().getParam("id");
        Employee employee = new Employee(employeeId, "Lalu");
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(employee));
    }*/

   /* @Getter
    static class Employee {
        private String id;
        private String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

    }*/
}
