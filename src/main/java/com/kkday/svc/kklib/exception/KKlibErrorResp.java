//package com.kkday.svc.kklib.exception;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.kkday.sdk.api.CommonMetadata;
//import com.kkday.sdk.controller.ResponseJson;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Data
//@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
//@NoArgsConstructor
//public class KKlibErrorResp extends ResponseJson<KKlibErrorResp.OrderApiContent> {
//
//    @Data
//    public static class OrderApiContent {
//        private String result;
//        private String msg;
//    }
//
//    @JsonIgnore
//    @Override
//    public CommonMetadata getMetadata() {
//        CommonMetadata meta = new CommonMetadata();
//        meta.setStatus(content.getResult());
//        meta.setDesc(content.getMsg());
//
//        return meta;
//    }
//
//    private OrderApiContent content = new OrderApiContent();
//
//    public KKlibErrorResp(String result , String msg) {
//        content.setResult(result);
//        content.setMsg(msg);
//    }
//
//    @JsonIgnore
//    @Override
//    public OrderApiContent getData() {
//        return super.getData();
//    }
//
//}
