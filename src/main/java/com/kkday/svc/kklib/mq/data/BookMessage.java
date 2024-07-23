package com.kkday.svc.kklib.mq.data;

import com.kkday.svc.kklib.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMessage {
    Integer bookOid;
    Book book;
}
