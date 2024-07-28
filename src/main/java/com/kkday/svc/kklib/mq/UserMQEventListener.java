package com.kkday.svc.kklib.mq;

import com.kkday.sdk.mq.AbstractMQMessageListener;
import com.kkday.sdk.mq.MQTopic;
import com.kkday.svc.kklib.entity.User;
import com.kkday.svc.kklib.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定義 MQ Listener 來接收特定 MQ 訊息
 */
@Component
@Slf4j
public class UserMQEventListener extends AbstractMQMessageListener<User> {
    @Autowired
    private UserService userService;

    @Override
    @SneakyThrows
    protected void handleCb(String topic, User user) {
        log.info(user.toString());
        userService.save(user);
        Thread.sleep(1000);
    }

    @Override
    protected MQTopic getMQTopic(){
        return UserMQTopic.USER;
    }
}
