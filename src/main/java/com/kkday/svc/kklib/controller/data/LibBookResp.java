package com.kkday.svc.kklib.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkday.sdk.controller.ResponseJson;

import com.kkday.svc.kklib.entity.LibBook;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibBookResp extends ResponseJson<LibBook> {

    @JsonIgnore
    private LibBook book;
}
