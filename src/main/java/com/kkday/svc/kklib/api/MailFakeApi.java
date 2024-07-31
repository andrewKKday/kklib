package com.kkday.svc.kklib.api;

import com.kkday.sdk.api.KKApi;
import com.kkday.svc.kklib.controller.data.MailResp;

public interface MailFakeApi extends KKApi {
    MailResp getMail() throws Exception;
}
