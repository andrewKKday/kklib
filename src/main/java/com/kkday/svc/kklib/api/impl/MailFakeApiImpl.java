package com.kkday.svc.kklib.api.impl;

import com.kkday.sdk.api.BaseApiImpl;
import com.kkday.svc.kklib.api.MailFakeApi;
import com.kkday.svc.kklib.controller.data.MailResp;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MailFakeApiImpl extends BaseApiImpl implements MailFakeApi {
    @Override
    protected String getRootUrl(){ return "http://fakeapi.sit.kkday.com/"; }

    @Override
    public MailResp getMail() throws Exception{
        return doGet("/api/mail/template5", null, MailResp.class);
    }

}
