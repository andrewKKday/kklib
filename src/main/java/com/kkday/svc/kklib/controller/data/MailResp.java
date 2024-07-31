package com.kkday.svc.kklib.controller.data;

import com.kkday.sdk.api.BaseApiResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MailResp extends BaseApiResp<Mail> {

    @Override
    protected String[] successCodes() {
        return new String[]{"0000"};
    }
}
