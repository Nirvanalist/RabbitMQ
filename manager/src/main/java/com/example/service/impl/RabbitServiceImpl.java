package com.example.service.impl;

import cn.hutool.json.JSONUtil;
import com.example.domain.CommonMessageBo;
import com.example.service.RabbitService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.example.util.RabbitConstants.QUEUE_COMMON_MESSAGE;
import static com.example.util.RabbitConstants.QUEUE_PREFIX;

@Service
public class RabbitServiceImpl implements RabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(CommonMessageBo messageBo) {
        String msg = JSONUtil.toJsonStr(messageBo);
        rabbitTemplate.convertAndSend(QUEUE_PREFIX + QUEUE_COMMON_MESSAGE, msg);
    }
}
