package com.example.service;

import com.example.domain.CommonMessageBo;

public interface RabbitService {

    void send(CommonMessageBo messageBo);
}
