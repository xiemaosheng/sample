package com.nd.common.exception;

import com.nd.gaea.WafException;
import org.springframework.http.HttpStatus;

/**
 * Created by newtonk on 2016/8/18 0018.
 */
public class NiceException extends WafException {
    public NiceErrorCodes getCode() {
        return code;
    }

    private NiceErrorCodes code;

    public NiceException(NiceErrorCodes code) {
        super("FEP/" + code.name(), code.getMsg(), code.getStatus());
        this.code = code;
    }

    public NiceException(String code, String msg) {
        super("FEP/" + code, msg);
    }

    public NiceException(NiceErrorCodes code, Object... args) {
        super("FEP/" + code, String.format(code.getMsg(),args), HttpStatus.BAD_REQUEST);
    }

    public NiceException(String code, String msg, HttpStatus status) {
        super("FEP/" + code, msg, status);
    }
}
