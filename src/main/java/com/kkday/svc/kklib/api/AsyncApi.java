package com.kkday.svc.kklib.api;

import com.kkday.sdk.api.KKApi;
import com.kkday.svc.kklib.api.data.NewBookResp;

import java.util.concurrent.CompletableFuture;

public interface AsyncApi extends KKApi {
    CompletableFuture<NewBookResp> getNewBookAsync(String bookTitle);
}
