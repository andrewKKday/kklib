package com.kkday.svc.kklib.controller;

import com.kkday.svc.kklib.controller.data.LibBookReq;
import com.kkday.svc.kklib.controller.data.LibBookResp;
import com.kkday.svc.kklib.entity.LibBook;
import com.kkday.svc.kklib.service.LibBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class LibBookController {

    @Autowired
    private LibBookService libBookService;

    /**
     * Create Book
     * @param req
     * @return
     */
    @PostMapping("/createBook")
    public LibBookResp createBook(@RequestBody LibBookReq req) {
        LibBook book = new LibBook();
        log.info("Create Book {}", book);
        BeanUtils.copyProperties(req,book);
        LibBook createdBook = libBookService.createBook(book);
        LibBookResp resp = new LibBookResp();
        resp.setBook(createdBook);
        return resp;
    }

    /**
     * Find Book
     * @param bookOid
     * @return
     */
    @GetMapping("/findBook/{bookOid}")
    public LibBookResp findBook(@PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        LibBookResp resp = new LibBookResp();
        resp.setBook(book);
        log.info("Find Book {}", book);
        return resp;
    }

    /**
     * Delete Book
     * @param bookOid
     */
    @DeleteMapping("/deleteBook/{bookOid}")
    public void deleteBook(@PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Delete Book {}", book);
        libBookService.deleteById(bookOid);
    }

    /**
     * Update Book
     * @param req
     * @param bookOid
     * @return
     */
    @PutMapping("updateBook/{bookOid}")
    public LibBookResp updateBook(@RequestBody LibBookReq req, @PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Update Book {}", book);
        BeanUtils.copyProperties(req,book); // copy from req to book
        LibBook updatedBook = libBookService.updateBook(book);
        LibBookResp resp = new LibBookResp();
        resp.setBook(updatedBook);
        return resp;
    }

    /**
     * List All Books
     * @return
     */
    @GetMapping("listAllBook")
    public List<LibBook> listAllBook() {
        return libBookService.findAll();
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
