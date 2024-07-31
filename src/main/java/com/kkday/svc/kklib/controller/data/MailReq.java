package com.kkday.svc.kklib.controller.data;

import com.kkday.sdk.controller.RequestJson;
import com.kkday.svc.kklib.entity.Book;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class MailReq extends RequestJson {
    private Book book;

    private String userName;
}
