//package com.kkday.svc.kklib.exception;
//
//import java.sql.SQLException;
//
//import jakarta.validation.constraints.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.kkday.sdk.data.SdkResultCode;
//import com.kkday.sdk.exception.ErrorResponseResolver;
//import com.kkday.sdk.exception.SvcException;
//import com.kkday.sdk.impl.handler.AbstractExceptionHandler;
//
//@ControllerAdvice
//public class KKlibExceptionHandler extends AbstractExceptionHandler {
//
//    @Override
//    public ResponseEntity<@NotNull String> handleSvcException(SvcException svcException) {
//        return super.handleSvcException(svcException);
//    }
//
//    @Autowired
//    private ErrorResponseResolver errorResponseResolver;
//
//    // @ExceptionHandler(SvcException.class)
//    public ResponseEntity<String> handleException(SvcException e) {
//        return toResponseEntity(errorResponseResolver.resolve("T" + e.getResultCode(), e.getMessage(), e));
//    }
//
//    //	@ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        return toResponseEntity(errorResponseResolver.resolve(SdkResultCode.SYSTEM_ERROR.getValue(), e));
//    }
//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<String> handleException(SQLException e) {
//        return toResponseEntity(errorResponseResolver.resolve(SdkResultCode.SYSTEM_ERROR.getValue(), e));
//    }
//
//}