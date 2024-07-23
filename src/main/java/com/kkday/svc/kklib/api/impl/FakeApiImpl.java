package com.kkday.svc.kklib.api.impl;

import com.kkday.sdk.api.BaseApiImpl;
import com.kkday.svc.kklib.api.FakeApi;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeApiImpl extends BaseApiImpl implements FakeApi {
    @Override
    protected String getRootUrl() {
        return "http://fakeapi.sit.kkday.com/";
    }
}
