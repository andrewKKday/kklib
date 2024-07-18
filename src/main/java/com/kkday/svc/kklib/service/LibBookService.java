package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.LibBook;

import java.util.List;

public interface LibBookService extends BaseDomainService<Integer, LibBook> {
    LibBook findById(Integer bookOid);
    List<LibBook> findAll();

    // 新增Book
    LibBook createBook(LibBook book);

    // 更新Book
    LibBook updateBook(LibBook book);

    // 刪除Book
    void deleteById(Integer bookOid);
}
