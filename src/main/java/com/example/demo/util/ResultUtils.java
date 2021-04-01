package com.example.demo.util;

import com.example.demo.pojo.base.BaseResponse;

public class ResultUtils {

    public static <T> BaseResponse<T> ok() {
        return ok(null);
    }

    public static <T> BaseResponse<T> ok(T content) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setCode(200);
        response.setMessage("成功");
        response.setData(content);
        return response;
    }

    public static <T> BaseResponse<T> failed(int code, String message) {
        String errMsg = message == null ? String.valueOf(code) : message;

        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(errMsg);
        return response;

    }

}
