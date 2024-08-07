package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.sdk.mq.MQService;
import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.sdk.thread.KKThreadPoolExecutor;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.mq.BookMQTopic;
import com.kkday.svc.kklib.mq.data.BookMessage;
import com.kkday.svc.kklib.repository.BookRepository;
import com.kkday.svc.kklib.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class BookServiceImpl extends BaseDomainServiceImpl<Integer, Book> implements BookService {

    @Autowired
    private BookRepository libRepository;

    @Autowired
    private MQService mqService;

    @Autowired
    @Lazy
    private BookService selfService;

    private final Executor executor = new KKThreadPoolExecutor("batchAsync");

    /**
     * 新增Book
     */
    @Override
    @KKTxRequired
    public Book createBook(Book book){
        Book createdBook = libRepository.save(book);
        BookMessage msg = new BookMessage(book.getBookOid(),book);
        sendMessage(msg);
        return createdBook;
    }

    /**
     * 更新Book
     */
    @Override
    public Book updateBook(Book book){
        if (book.getBookOid() == null){
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        Book updatedBook = libRepository.save(book);
        BookMessage msg = new BookMessage(book.getBookOid(),book);
        sendMessage(msg);
        return updatedBook;
    }

    /**
     * 透過Oid刪除Book
     */
    @Override
    public void deleteById(Integer bookOid) {
        libRepository.deleteById(bookOid);
        BookMessage msg = new BookMessage(bookOid,null);
        sendMessage(msg);
    }

    /**
     * 透過Oid找Book
     */
    @Override
    public Book findById(Integer bookOid) {
        return libRepository.findByBookOid(bookOid);
    }

    /**
     * 列出所有Book
     */
    @Override
    public List<Book> findAll() {
        return libRepository.findAll();
    }

    @Override
    public Book findByTitle(String title) {
        return libRepository.findBookByBookTitle(title);
    }

    @Override
    public boolean existsByBookTitleAndBookAuthorAndBookCategory(String bookTitle, String bookAuthor, String bookCategory) {
        return libRepository.existsByBookTitleAndBookAuthorAndBookCategory(bookTitle, bookAuthor, bookCategory);
    }

    @Override
    protected JpaRepository<Book, Integer> getJpaRepository() {
        return null;
    }

    private void sendMessage(BookMessage book) {
        try {
            mqService.sendMessage(BookMQTopic.BOOK, book);
        } catch (Exception e) {
            log.error("Failed to send MQ message", e);
        }
    }

    @Override
    public void executeJobAsync(Book book){
        executor.execute(()->{
            log.info("executeJobAsync, book={}",book);
            selfService.save(book);
            BookMessage msg = new BookMessage(book.getBookOid(),book);
            sendMessage(msg);
        });
    }

}
