package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.svc.kklib.entity.libBook;
import com.kkday.svc.kklib.repository.libBookRepository;
import com.kkday.svc.kklib.service.libBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class libBookServiceImpl extends BaseDomainServiceImpl<Integer, libBook> implements libBookService {

    private libBookRepository libRepository;

    @Override
    public libBook findById(Integer bookOid) {
        return libRepository.findById(bookOid).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<libBook> findAll() {
        return libRepository.findAll();
    }

    @Override
    public void deleteById(Integer bookOid) {
        libRepository.deleteById(bookOid);
    }
    @Override
    protected JpaRepository<libBook, Integer> getJpaRepository() {
        return null;
    }
}
