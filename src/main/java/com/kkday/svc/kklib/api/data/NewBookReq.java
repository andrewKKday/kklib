package com.kkday.svc.kklib.api.data;

import com.kkday.sdk.api.KKApiRetryConfig;
import com.kkday.sdk.exception.OutboundApiException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBookReq implements KKApiRetryConfig {
    private String bookTitle;

    @Override
    public boolean shouldRetry(OutboundApiException e){
        return true;
    }

}
