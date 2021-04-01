package com.example.demo.exception;


public class BussinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BussinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BussinessException create(int code, String message){
        return new BussinessException(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
