package com.github.mcoder;

import com.github.mcoder.verticles.HelloWorldVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SampleVertxApplication {

    public static void main(String[] args) {
        Vertx vertx = Vertx.factory.vertx();
        vertx.deployVerticle(new HelloWorldVerticle());


    }


}
