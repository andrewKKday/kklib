package com.kkday.svc.kklib.api.impl;

import com.kkday.sdk.api.BaseApiImpl;
import com.kkday.svc.kklib.api.BookFakeApi;
import com.kkday.svc.kklib.api.data.NewBookResp;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 定義 Fake API 介接的實做. 透過繼承 {@link BaseApiImpl}. 可以獲得基本的 POST/GET/PATCH 操作
 */
@Component
@Primary
public class BookFakeApiImpl extends BaseApiImpl implements BookFakeApi {
    @Override
    protected String getRootUrl() {
        return "http://fakeapi.sit.kkday.com/";
    }

    @Override
    public NewBookResp getNewBook() throws Exception {
        return doGet("/api/getBook", null, NewBookResp.class);
    }
}
