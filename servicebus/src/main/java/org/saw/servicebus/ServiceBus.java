package org.saw.servicebus;


import io.vertx.core.Vertx;
 
import org.saw.servicebus.proxy.verticle.ProxyVerticle;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages={ServiceBus.baseBackages})
public class ServiceBus {

  public static final String baseBackages = "org.saw.servicebus" ;

  @Autowired
  private ProxyVerticle proxyVerticle ;

  public static void main(String[] args) {
    SpringApplication.run(ServiceBus.class, args);
  }

  @PostConstruct
  public void deployVerticle() { 
    Vertx.vertx().deployVerticle(proxyVerticle);
  }
  
}
