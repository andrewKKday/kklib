package com.kkday.svc.kklib.controller;

import com.kkday.sdk.annotation.EnableKKdaySdkTest;
import com.kkday.sdk.api.KKApiFactory;
import com.kkday.svc.kklib.KKlibApplication;
import com.kkday.svc.kklib.controller.data.BookReq;
import com.kkday.svc.kklib.controller.data.BookResp;
import com.kkday.svc.kklib.entity.Book;
import com.kkday.svc.kklib.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = KKlibApplication.class)
@EnableKKdaySdkTest
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private Book book;
    private BookReq bookReq;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setBookOid(1);
        book.setBookTitle("Test Book");
        book.setBookCategory("Test Category");

        bookReq = new BookReq();
        BeanUtils.copyProperties(book, bookReq);
        KKApiFactory.setTestMode(true);
    }

    @Test
    public void testCreateBook() {
        Mockito.when(bookService.createBook(any(Book.class))).thenReturn(book);

        BookResp response = bookController.createBook(bookReq);
        assertEquals(book.getBookOid(), response.getBook().getBookOid());
        assertEquals(book.getBookTitle(), response.getBook().getBookTitle());
        assertEquals(book.getBookCategory(), response.getBook().getBookCategory());
    }

    @Test
    public void testUpdateBook() {
        Mockito.when(bookService.findById(anyInt())).thenReturn(book);
        Mockito.when(bookService.updateBook(any(Book.class))).thenReturn(book);

        BookResp response = bookController.updateBook(bookReq, 1);
        assertEquals(book.getBookOid(), response.getBook().getBookOid());
        assertEquals(book.getBookTitle(), response.getBook().getBookTitle());
        assertEquals(book.getBookCategory(), response.getBook().getBookCategory());
    }

    @Test
    public void testDeleteBook() {
        Mockito.when(bookService.findById(anyInt())).thenReturn(book);
        Mockito.doNothing().when(bookService).deleteById(anyInt());

        bookController.deleteBook(1);
        Mockito.verify(bookService, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void testFindBookById() {
        Mockito.when(bookService.findById(anyInt())).thenReturn(book);

        BookResp response = bookController.findBookById(1);
        assertEquals(book.getBookOid(), response.getBook().getBookOid());
        assertEquals(book.getBookTitle(), response.getBook().getBookTitle());
        assertEquals(book.getBookCategory(), response.getBook().getBookCategory());
    }

    @Test
    public void testListAllBook() {
        Mockito.when(bookService.findAll()).thenReturn(List.of(book));

        List<Book> books = bookController.listAllBook();
        assertEquals(1, books.size());
        assertEquals(book.getBookOid(), books.getFirst().getBookOid());
        assertEquals(book.getBookTitle(), books.getFirst().getBookTitle());
        assertEquals(book.getBookCategory(), books.getFirst().getBookCategory());
    }

    @Test
    public void testFindBookByTitle() {
        Mockito.when(bookService.findByTitle(anyString())).thenReturn(book);

        BookResp response = bookController.findBookByTitle("Test Book");
        assertEquals(book.getBookOid(), response.getBook().getBookOid());
        assertEquals(book.getBookTitle(), response.getBook().getBookTitle());
        assertEquals(book.getBookCategory(), response.getBook().getBookCategory());
    }

    @Test
    public void testFindBookByTitleAndCategory() {
        Mockito.when(bookService.findByTitleAndCategory(anyString(), anyString())).thenReturn(book);

        BookResp response = bookController.findBookByTitleAndCategory("Test Book", "Test Category");
        assertEquals(book.getBookOid(), response.getBook().getBookOid());
        assertEquals(book.getBookTitle(), response.getBook().getBookTitle());
        assertEquals(book.getBookCategory(), response.getBook().getBookCategory());
    }

    @Test
    public void testBatchExecuteAsync() {
        Mockito.when(bookService.findAll()).thenReturn(List.of(book));
        Mockito.doNothing().when(bookService).executeJobAsync(any(Book.class));

        bookController.batchExecuteAsync();
        Mockito.verify(bookService, Mockito.times(1)).executeJobAsync(any(Book.class));
    }
}
