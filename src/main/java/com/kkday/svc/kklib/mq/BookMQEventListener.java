package com.kkday.svc.kklib.mq;

import com.kkday.sdk.mq.AbstractMQMessageListener;
import com.kkday.sdk.mq.MQTopic;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.service.BookService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定義 MQ Listener 來接收特定 MQ 訊息
 */
@Component
// MQ Listener 一定要宣告為 Component.
// 這樣才能在 Spring 啟動時一併啟動
@Slf4j
public class BookMQEventListener extends AbstractMQMessageListener<Book> {
    @Autowired
    private BookService bookService;

    @Override
    @SneakyThrows
    protected void handleCb(String topic, Book book) {
        log.info(book.toString());
        bookService.save(book);
        Thread.sleep(1 * 1000);
    }

    @Override
    protected MQTopic getMQTopic(){
        return BookMQTopic.ALL;
    }
}
