package com.wusw.cloud.handler.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {
    private String msg;
    private int code = 500;

    public MyException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg) {
        this.msg = msg;
    }
}
