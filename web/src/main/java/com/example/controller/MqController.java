package com.example.controller;

import com.example.domain.CommonMessageBo;
import com.example.service.RabbitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class MqController {

    @Resource
    private RabbitService rabbitService;

    @PostMapping("send")
    public void send(@RequestBody CommonMessageBo messageBo) {
        rabbitService.send(messageBo);
    }

}
