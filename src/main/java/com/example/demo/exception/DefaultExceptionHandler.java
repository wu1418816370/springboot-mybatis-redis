package com.example.demo.exception;

import com.example.demo.pojo.base.BaseResponse;
import com.example.demo.util.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private <T> BaseResponse<T> convertError(Exception exception) {
        if (exception instanceof BussinessException) {
            BussinessException bussinessException = (BussinessException) exception;
            return ResultUtils.failed(bussinessException.getCode(), bussinessException.getMessage());
        } else {
            exception.printStackTrace();
        }
        return ResultUtils.failed(999999, "系统异常!");
    }

}
