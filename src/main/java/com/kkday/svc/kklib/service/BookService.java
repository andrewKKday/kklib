package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.Book;

import java.util.List;

public interface BookService extends BaseDomainService<Integer, Book> {
    /**
     * 新增Book
      */
    Book createBook(Book book);

    /**
     * 更新Book
     */
    Book updateBook(Book book);

    /**
     * 刪除Book
     */
    void deleteById(Integer bookOid);

    /**
     *  透過Oid找Book
     */
    Book findById(Integer bookOid);

    /**
     *  列出所有Book
     */
    List<Book> findAll();
}
