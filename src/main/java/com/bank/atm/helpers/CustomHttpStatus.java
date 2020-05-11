package com.bank.atm.helpers;

public enum CustomHttpStatus {
    INVALID_DATA(440),
    UNACHIEVABLE(441);

    private Integer code;

    private CustomHttpStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
