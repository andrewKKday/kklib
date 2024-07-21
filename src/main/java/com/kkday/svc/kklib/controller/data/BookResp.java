package com.kkday.svc.kklib.controller.data;

import com.kkday.sdk.controller.ResponseJson;

import com.kkday.svc.kklib.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookResp extends ResponseJson<Book> {
    private Book book;
}
