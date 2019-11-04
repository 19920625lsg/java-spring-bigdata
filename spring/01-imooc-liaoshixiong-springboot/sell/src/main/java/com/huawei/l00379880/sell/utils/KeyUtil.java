/***********************************************************
 * @Description : 生成随机字符串作为表的主键
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 15:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一地主键,注意方法要同步防止生成相同的key
     *
     * @return 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        // 生成6位随机数
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
