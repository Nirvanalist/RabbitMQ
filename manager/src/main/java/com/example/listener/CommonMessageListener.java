package com.example.listener;

import cn.hutool.json.JSONUtil;
import com.example.service.CommonMessageService;
import com.example.domain.CommonMessageBo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(CommonMessageListener.class);

    @Resource
    private CommonMessageService commonMessageService;

    public void receiveMessage(String message) {
        logger.info("收到Common消息!, message={}", message);
        var messageBo = new CommonMessageBo();
        try {
            messageBo = JSONUtil.parse(message).toBean(CommonMessageBo.class);
        } catch (Exception e) {
            logger.error("Common消息解析失败, e={}", e.getMessage());
            return;
        }
        commonMessageService.handler(messageBo);
        logger.info("处理Common消息成功!");
    }

}
