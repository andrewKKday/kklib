package com.kkday.svc.kklib.mq;

import com.kkday.sdk.api.KKApiFactory;
import com.kkday.sdk.mq.AbstractMQMessageListener;
import com.kkday.sdk.mq.MQTopic;
import com.kkday.svc.kklib.api.NewBookFakeApi;
import com.kkday.svc.kklib.api.data.NewBookId;
import com.kkday.svc.kklib.api.data.NewBookResp;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookMQEventListener extends AbstractMQMessageListener<NewBookId> {
    @Autowired
    private BookService bookService;

    @Override
    protected void handleCb(String topic, NewBookId id) {
        NewBookFakeApi api = KKApiFactory.getApi(NewBookFakeApi.class);
        NewBookResp newBookResp;
        try {
            newBookResp = api.getNewBook();
            //Integer bookOid = newBookResp.getData().getBookOid();

            // 檢查書的內容是否存在
            if (bookService.existsByBookTitleAndBookAuthorAndBookCategory(
                    newBookResp.getData().getBookTitle(),
                    newBookResp.getData().getBookAuthor(),
                    newBookResp.getData().getBookCategory())) {
                log.info("Book with similar content already exists. Skipping creation.");
                return;
            }

            Book book = new Book();
            BeanUtils.copyProperties(newBookResp.getData(), book);
            Book newBook = bookService.createBook(book);
            log.info("New fakeApi book created: {}", newBook);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected MQTopic getMQTopic() {
        return BookMQTopic.BOOK;
    }
}
