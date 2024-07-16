package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.libBook;

import java.util.List;

public interface libBookService extends BaseDomainService<Integer, libBook> {
    libBook findById(Integer id);
    List<libBook> findAll();
    void deleteById(Integer id);
}
