package com.kkday.svc.kklib.controller;

import com.kkday.sdk.annotation.KKLock;
import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.svc.kklib.controller.data.BookReq;
import com.kkday.svc.kklib.controller.data.BookResp;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 新增Book
     * @param req
     * @return
     */
    @KKTxRequired
    @PostMapping("/createBook")
    public BookResp createBook(@RequestBody BookReq req) {
        Book book = new Book();
        log.info("Create Book {}", book);
        BeanUtils.copyProperties(req,book);
        Book createdBook = bookService.createBook(book);
        BookResp resp = new BookResp();
        resp.setBook(createdBook);
        return resp;
    }

    /**
     * 更新Book
     * @param req
     * @param bookOid
     * @return
     */
    @KKTxRequired
    @KKLock(lockName = "updateBookLock", tryLockTime = 5000L)
    @PutMapping("/updateBook/{bookOid}")
    public BookResp updateBook(@RequestBody BookReq req, @PathVariable Integer bookOid) {
        Book book = bookService.findById(bookOid);
        log.info("Update Book {}", book);
        // 保存原始識別符
        Integer originalId = book.getBookOid();

        // 拷貝屬性，確保不改變識別符
        BeanUtils.copyProperties(req, book);

        // 恢復原始識別符
        book.setBookOid(originalId);
        Book updatedBook = bookService.updateBook(book);
        BookResp resp = new BookResp();
        resp.setBook(updatedBook);
        return resp;
    }

    /**
     * 刪除Book
     * @param bookOid
     */
    @KKTxRequired
    @KKLock(lockName = "deleteBookLock", tryLockTime = 5000L)
    @DeleteMapping("/deleteBook/{bookOid}")
    public void deleteBook(@PathVariable Integer bookOid) {
        Book book = bookService.findById(bookOid);
        log.info("Delete Book {}", book);
        bookService.deleteById(bookOid);
    }

    /**
     * 透過Oid找Book
     * @param bookOid
     * @return
     */
    @GetMapping("/findBook/{bookOid}")
    public BookResp findBook(@PathVariable Integer bookOid) {
        Book book = bookService.findById(bookOid);
        BookResp resp = new BookResp();
        resp.setBook(book);
        log.info("Find Book {}", book);
        return resp;
    }

    /**
     * 列出所有Books
     * @return
     */
    @GetMapping("/listAllBook")
    public List<Book> listAllBook() {
        return bookService.findAll();
    }

    /* @GetMapping("/findBook/{bookOid}")
    public LibBook findBook(@RequestBody LibBook libBook, @PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Find Book {}", book);
        return libBookService.findById(libBook.getBookOid());
    }

    @PostMapping("/createBook")
    public LibBook createBook(@RequestBody LibBook libBook) {
        return libBookService.createBook(libBook);
    }*/
}
