package com.kkday.svc.kklib.repository;

import com.kkday.svc.kklib.entity.LibBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookRepository extends JpaRepository<LibBook, Integer> {
    LibBook findByBookOid(Integer bookOid);
}
