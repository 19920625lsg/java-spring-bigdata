/***********************************************************
 * @Description : http请求返回的最外层对象
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 18:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.vo;

import lombok.Data;

@Data
public class ResultVO<T> {


    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO() {
    }

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg = "";

    /**
     * 具体内容
     */
    private T data;
}
