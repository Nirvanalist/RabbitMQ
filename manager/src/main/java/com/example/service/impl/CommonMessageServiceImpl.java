package com.example.service.impl;

import com.example.domain.CommonMessageBo;
import com.example.service.CommonMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.util.RabbitConstants.TYPE_DISABLE;
import static com.example.util.RabbitConstants.TYPE_ENABLE;

@Service
public class CommonMessageServiceImpl implements CommonMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMessageServiceImpl.class);

    @Override
    public void handler(CommonMessageBo msg) {
        switch (msg.getType()) {
            case TYPE_ENABLE -> LOGGER.info("enable user, payload={}", msg.getPayload());
            case TYPE_DISABLE -> LOGGER.info("disable user, payload={}", msg.getPayload());
            default -> LOGGER.warn("unknown type, type={}, payload={}", msg.getType(), msg.getPayload());
        }
    }

}
