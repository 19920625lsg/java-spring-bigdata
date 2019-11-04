package com.huawei.l00379880.section6.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RedisRepository Tester.
 *
 * @author liangshanguang
 * @date 11/22/2018
 * @description test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisRepositoryTest {
    @Autowired
    RedisRepository redisRepository;

    @Test
    public void testMain() {
        redisRepository.setKey("name", "lsg");
        redisRepository.setKey("age", "26");
        log.info(redisRepository.getValue("name"));
        log.info(redisRepository.getValue("age"));
    }
} 
