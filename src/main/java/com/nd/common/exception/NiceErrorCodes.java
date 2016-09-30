package com.nd.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by newtonk on 2016/8/18 0018.
 */
public enum NiceErrorCodes {
    PASSWORD_CRYPTO_ERROR("password crypto error"),
    LOGIN_NAME_HAS_REGISTER("login name has been registered"),
    FORBIDDEN ("forbidden", HttpStatus.FORBIDDEN),
    BAD_REQUEST("argument [%s] not be null", HttpStatus.BAD_REQUEST),
    COURSE_HAS_EXIST("course has exits"),
    INVALID_ARGUMENT("invalid argument"),
    USER_HAS_EXIST("user has exist"),
    CREATE_USER_FAILD("create user faild"),
    ;


    private String msg;
    private HttpStatus status;

    public String getMsg() {
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    NiceErrorCodes(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    NiceErrorCodes(String msg) {
        this.msg = msg;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public NiceException getException() {
        return new NiceException(this);
    }
}
