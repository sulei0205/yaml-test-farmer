package com.farmer.chicken.yamltest.exception;

/**
 * @Author: sulei
 * @Date: 2020-05-07 17:16
 * @Discription: 测试框架业务异常
 */
public class YmlException extends Exception{

    private int code;
    private String message;

    public YmlException() {
    }

    public YmlException(int code) {
        this.code = code;
    }

    public YmlException(String message) {
        this.message = message;
    }

    public YmlException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
