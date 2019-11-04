/***********************************************************
 * @Description : 结果返回的工具类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 19:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.utils;

import com.huawei.l00379880.sell.vo.ResultVO;

public class ResultVOUtil {
    /**
     * 成功时切返回对象
     *
     * @param object 外部的数据，可以使单个对象也可以是List等
     * @return
     */
    public static ResultVO success(Object object) {
        return new ResultVO(0, "成功", object);
    }

    public static ResultVO success() {
        return new ResultVO(0, "成功", null);
    }

    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
