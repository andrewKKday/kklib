package com.kkday.svc.kklib.mq;

import com.kkday.sdk.mq.MQExchange;
import com.kkday.sdk.mq.MQTopic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *  Topic 對應的是 MQ 訊息的 routing key
 */
@Getter
@RequiredArgsConstructor
public enum BookMQTopic implements MQTopic {
    ALL("#"),
    BOOK("BOOK");

    private final String value;

    @Override
    public MQExchange getMQExchange() {
        return BookMQExchange.BOOK;
    }
}
