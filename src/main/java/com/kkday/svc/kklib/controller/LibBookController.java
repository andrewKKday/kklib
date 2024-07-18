package com.kkday.svc.kklib.controller;

import com.kkday.svc.kklib.entity.LibBook;
import com.kkday.svc.kklib.service.LibBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class LibBookController {

    @Autowired
    private LibBookService libBookService;

    @PostMapping("/createBook")
    public LibBook createBook(@RequestBody LibBook libBook) {
        return libBookService.createBook(libBook);
    }

    @GetMapping("/findBook/{bookOid}")
    //TODO : public LibBookResp findBook(@RequestBody LibBookReq libBook, @PathVariable Integer bookOid) {
    public LibBook findBook(@RequestBody LibBook libBook, @PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Find Book {}", book);
        return libBookService.findById(libBook.getBookOid());
    }

    @DeleteMapping("/deleteBook/{bookOid}")
    public void deleteBook(@PathVariable Integer bookOid) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Delete Book {}", book);
        libBookService.deleteById(bookOid);
    }

    @PutMapping("updateBook/{bookOid}")
    public LibBook updateBook(@PathVariable Integer bookOid, @RequestBody LibBook libBook) {
        LibBook book = libBookService.findById(bookOid);
        log.info("Update Book {}", book);
        return libBookService.updateBook(libBook);
    }

    @GetMapping("listAllBook")
    public List<LibBook> listAllBook() {
        return libBookService.findAll();
    }

}
