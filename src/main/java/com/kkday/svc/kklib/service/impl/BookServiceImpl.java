package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.mq.MQService;
import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.mq.BookMQTopic;
import com.kkday.svc.kklib.mq.data.BookMessage;
import com.kkday.svc.kklib.repository.BookRepository;
import com.kkday.svc.kklib.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl extends BaseDomainServiceImpl<Integer, Book> implements BookService {

    @Autowired
    private BookRepository libRepository;

    @Autowired
    private MQService mqService;

    /**
     * 新增Book
     * @param book
     * @return
     */
    @Override
    public Book createBook(Book book){
        Book createdBook = libRepository.save(book);
        BookMessage msg = new BookMessage(book.getBookOid(),book);
        sendMessage(msg);
        return createdBook;
    }

    /**
     * 更新Book
     * @param book
     * @return
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
     * @param bookOid
     */
    @Override
    public void deleteById(Integer bookOid) {
        libRepository.deleteById(bookOid);
        BookMessage msg = new BookMessage(bookOid,null);
        sendMessage(msg);
    }

    /**
     * 透過Oid找Book
     * @param bookOid
     * @return
     */
    @Override
    public Book findById(Integer bookOid) {
        return libRepository.findByBookOid(bookOid);
    }

    /**
     * 列出所有Book
     * @return
     */
    @Override
    public List<Book> findAll() {
        return libRepository.findAll();
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

}
