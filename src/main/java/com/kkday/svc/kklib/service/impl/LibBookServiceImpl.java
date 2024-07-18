package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.annotation.KKLock;
import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.svc.kklib.entity.LibBook;
import com.kkday.svc.kklib.repository.LibBookRepository;
import com.kkday.svc.kklib.service.LibBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LibBookServiceImpl extends BaseDomainServiceImpl<Integer, LibBook> implements LibBookService {

    @Autowired
    private LibBookRepository libRepository;

    // 新增Book
    @KKTxRequired
    @KKLock(lockName = "createBookLock", tryLockTime = 5000L)
    @Override
    public LibBook createBook(LibBook book){
        return libRepository.save(book);
    }

    // 更新Book
    @KKTxRequired
    @KKLock(lockName = "updateBookLock", tryLockTime = 5000L)
    @Override
    public LibBook updateBook(LibBook book){
        return libRepository.save(book);
    }

    // 透過Oid刪除Book
    @KKTxRequired
    @KKLock(lockName = "deleteBookLock", tryLockTime = 5000L)
    @Override
    public void deleteById(Integer bookOid) {
        libRepository.deleteById(bookOid);
    }

    // 透過Oid找Book
    @Override
    public LibBook findById(Integer bookOid) {
        return libRepository.findByBookOid(bookOid);
    }

    // 列出所有Book
    @Override
    public List<LibBook> findAll() {
        return libRepository.findAll();
    }

    @Override
    protected JpaRepository<LibBook, Integer> getJpaRepository() {
        return null;
    }
}
