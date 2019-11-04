package com.huawei.l00379880.serviceorder.exception;

import com.huawei.l00379880.serviceorder.enums.ResultEnum;


public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
