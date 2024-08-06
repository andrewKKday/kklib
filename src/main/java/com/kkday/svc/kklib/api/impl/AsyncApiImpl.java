package com.kkday.svc.kklib.api.impl;

import com.kkday.sdk.api.BaseAsyncApiImpl;
import com.kkday.svc.kklib.api.AsyncApi;
import com.kkday.svc.kklib.api.data.NewBookResp;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Primary
public class AsyncApiImpl extends BaseAsyncApiImpl implements AsyncApi {

    @Override
    protected String getRootUrl() {
        return null;
    }

    @Override
    @SneakyThrows
    public CompletableFuture<NewBookResp> getNewBookAsync(String title) {
        return doPost("/api/asyncBook", null, NewBookResp.class);
    }
}
