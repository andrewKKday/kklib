package com.kkday.svc.kklib;

import com.kkday.sdk.data.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 定義 KKlib 應用的錯誤代碼
 */
@Getter
@RequiredArgsConstructor
public enum KKlibResultCode implements ResultCode {

    BOOK_ERROR("E001"),

    USER_ERROR("E002");

    private final String value;
}
