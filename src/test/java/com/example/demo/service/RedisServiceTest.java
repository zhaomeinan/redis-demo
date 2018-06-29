package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description： 测试
 *
 * @author: zhaomeinan
 * date: 2018/6/29 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void get() throws Exception {
        System.out.println(redisService.get("zhaomeinan"));
    }

    @Test
    public void getWithExpire() throws Exception {
        System.out.println(redisService.getWithExpire("zhaomeinan2"));
    }

    @Test
    public void save() throws Exception {
        redisService.save("zhaomeinan3");
    }

    @Test
    public void remove() throws Exception {
        redisService.remove("zhaomeinan3");
    }

}