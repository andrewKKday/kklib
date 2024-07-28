package com.kkday.svc.kklib.api;

import com.kkday.sdk.api.KKApi;
import com.kkday.svc.kklib.api.data.NewBookResp;

public interface FakeApi extends KKApi {
    NewBookResp getNewBook() throws Exception;
}
