package com.kkday.svc.kklib.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkday.sdk.controller.RequestJson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibUserReq extends RequestJson {

    @JsonIgnore
    private Integer userOid;

    private String userPwd;
}
