package com.kkday.svc.kklib.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkday.sdk.controller.ResponseJson;

import com.kkday.svc.kklib.entity.LibUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibUserResp extends ResponseJson<LibUser> {

    @JsonIgnore
    private LibUser user;
}
