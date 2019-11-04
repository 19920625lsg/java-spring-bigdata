/***********************************************************
 * @Description : 自定义异常
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 12:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.exception;

import com.huawei.l00379880.sell.enums.ResultEnum;
import lombok.Data;

@Data
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
