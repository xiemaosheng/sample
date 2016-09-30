package com.nd.common.enums;

/**
 * Created by newtonk on 2016/8/18 0018.
 */
public enum StateEnum {
    ON(1),
    OFF(0);

    private int code;
    StateEnum(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
