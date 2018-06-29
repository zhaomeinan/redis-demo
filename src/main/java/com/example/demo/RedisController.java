package com.example.demo;

import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description： TODO
 *
 * @author: zhaomeinan
 * date: 2018/6/29 16:49
 */
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "test/{id}", method = RequestMethod.GET)
    public Long test(@PathVariable int id) {
        return redisService.autoload(id);
    }

    @RequestMapping(value = "test2/{id}", method = RequestMethod.GET)
    public int test2(@PathVariable int id) {
        return redisService.autoloadWiteRequestTimeout(id);
    }
}
