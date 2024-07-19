package com.kkday.svc.kklib.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkday.sdk.controller.RequestJson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibBookReq extends RequestJson{

    @JsonIgnore
    private Integer bookOid;

    private String bookCategory;

    private String bookTitle;

    private String bookAuthor;

    private String bookCurrency;

    private Integer bookPrice;

    private Integer bookCost;
}
