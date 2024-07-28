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
     * @param req http request內容
     * @return http response內容
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
     * @param req http request內容
     * @return http response內容
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
     */
    @GetMapping("/findBookById/{bookOid}")
    public BookResp findBookById(@PathVariable Integer bookOid) {
        Book book = bookService.findById(bookOid);
        BookResp resp = new BookResp();
        resp.setBook(book);
        log.info("Find Book {}", book);
        return resp;
    }

    /**
     * 列出所有Books
     */
    @GetMapping("/listAllBook")
    public List<Book> listAllBook() {
        return bookService.findAll();
    }

    /**
     * 透過Title找Book
     */
    @GetMapping("/findBookByTitle/{bookTitle}")
    public BookResp findBookByTitle(@PathVariable String bookTitle) {
        Book book = bookService.findByTitle(bookTitle);
        BookResp resp = new BookResp();
        resp.setBook(book);
        log.info("Find Book {}", book);
        return resp;
    }

    /**
     * 透過Title和Category找Book
     */
    @GetMapping("/findBookByTitleAndCategory/{bookTitle}/{bookCategory}")
    public BookResp findBookByTitleAndCategory(@PathVariable String bookTitle, @PathVariable String bookCategory) {
        Book book = bookService.findByTitleAndCategory(bookTitle, bookCategory);
        BookResp resp = new BookResp();
        resp.setBook(book);
        log.info("Find Book {}", book);
        return resp;
    }

    /**
     *  批次執行. 收到請求後於背景執行，發布新書資訊
     */
    @GetMapping("/batchExecuteAsync")
    public void batchExecuteAsync(){
        List<Book> books = bookService.findAll();
        log.info("list all books {}", books);
        books.forEach(book -> bookService.executeJobAsync(book));
    }
}
