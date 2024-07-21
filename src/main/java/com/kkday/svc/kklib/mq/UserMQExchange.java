package com.kkday.svc.kklib.mq;

import com.kkday.sdk.mq.MQExchange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Exchange 對應的是發送 MQ 訊息的 exchanger.
 */
@Getter
@RequiredArgsConstructor
public enum UserMQExchange implements MQExchange {
    USER("USER");

    private final String value;
}
