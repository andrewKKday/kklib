package com.kkday.svc.kklib.exception;

import com.kkday.sdk.exception.ErrorResponse;
import com.kkday.sdk.exception.ErrorResponseResolver;
import org.springframework.stereotype.Component;

@Component
public class KKlibErrorResponseResolver implements ErrorResponseResolver {
    @Override
    public ErrorResponse resolve(String errorCode, String message, Throwable t) {
        return ErrorResponse.builder().status(500).body(new KKlibErrorResp(errorCode, message)).build();
    }
}
