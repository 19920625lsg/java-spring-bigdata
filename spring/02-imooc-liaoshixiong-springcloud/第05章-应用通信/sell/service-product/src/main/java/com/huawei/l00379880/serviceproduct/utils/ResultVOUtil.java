package com.huawei.l00379880.serviceproduct.utils;

import com.huawei.l00379880.serviceproduct.VO.ResultVO;


public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
