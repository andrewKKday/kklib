package com.kkday.svc.kklib.repository;

import com.kkday.svc.kklib.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByBookOid(Integer bookOid);
    Book findBookByBookTitle(String bookTitle);
    boolean existsByBookTitleAndBookAuthorAndBookCategory(String bookTitle, String bookAuthor, String bookCategory);
}
