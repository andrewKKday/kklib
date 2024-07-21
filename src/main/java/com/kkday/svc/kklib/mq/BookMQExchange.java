package com.kkday.svc.kklib.mq;

import com.kkday.sdk.mq.MQExchange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Exchange 對應的是發送 MQ 訊息的 exchanger.
 */
@Getter
@RequiredArgsConstructor
public enum BookMQExchange implements MQExchange {
    BOOK("BOOK");

    private final String value;
}
