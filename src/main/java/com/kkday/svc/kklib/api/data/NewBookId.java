package com.kkday.svc.kklib.api.data;

import com.kkday.svc.kklib.entity.Book;
import lombok.Data;

@Data
public class NewBookId {
    private Integer id;

    private Integer bookOid;

    private Book book;
}
