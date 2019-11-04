/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/22 23:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section6.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {
    private final StringRedisTemplate template;

    @Autowired
    public RedisRepository(StringRedisTemplate template) {
        this.template = template;
    }

    public void setKey(String key, String val) {
        ValueOperations<String, String> ops = template.opsForValue();
        // 1分钟超时
        ops.set(key, val, 1, TimeUnit.MINUTES);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
}
