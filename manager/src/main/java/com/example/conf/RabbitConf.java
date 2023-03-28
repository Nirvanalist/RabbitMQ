package com.example.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitConf {

    private String host;

    private Integer port;

    private String username;

    private String password;
}
