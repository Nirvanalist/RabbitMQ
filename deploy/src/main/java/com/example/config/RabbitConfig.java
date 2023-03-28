package com.example.config;

import com.example.conf.RabbitConf;
import com.example.listener.CommonMessageListener;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.util.RabbitConstants.QUEUE_COMMON_MESSAGE;
import static com.example.util.RabbitConstants.QUEUE_PREFIX;

@Configuration
public class RabbitConfig {

    @Resource
    private RabbitConf rabbitConf;

    @Resource
    private CommonMessageListener commonMessageListener;

    @Bean
    public ConnectionFactory connectionFactory() {
        var factory = new CachingConnectionFactory();
        factory.setHost(rabbitConf.getHost());
        factory.setPort(rabbitConf.getPort());
        factory.setUsername(rabbitConf.getUsername());
        factory.setPassword(rabbitConf.getPassword());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @RabbitListener(queuesToDeclare = @Queue(value = QUEUE_PREFIX + QUEUE_COMMON_MESSAGE, durable = "true"), concurrency = "5-10")
    public void receiveFcmPushMessage(String msg) {
        commonMessageListener.receiveMessage(msg);
    }

}
