package com.kkday.svc.kklib.api.data;

import com.kkday.sdk.api.BaseApiResp;
import com.kkday.svc.kklib.entity.Book;

public class NewBookResp extends BaseApiResp<Book> {
    /**
     * successCodes，在處理 API 回應時，可以使用這些代碼來判斷操作是否成功。
     * 需對應API的status ex: fakeApi 317 /api/getBook
     *     "metadata": {
     *         "status": "0000",
     *         "desc": "Success"
     *     },
     * @return 方法返回一個包含成功代碼的字串陣列
     */
    @Override
    protected String[] successCodes() {
        return new String[]{"0000"}; //原為預設0001
    }

    @Override
    public boolean shouldThrow(String status){
        return true;
    }

}

