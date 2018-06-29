package com.example.demo.service;

import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import com.jarvis.cache.serializer.FastjsonSerializer;
import com.jarvis.cache.serializer.HessianSerializer;
import com.jarvis.cache.serializer.ISerializer;
import com.jarvis.cache.serializer.JdkSerializer;
import com.jarvis.cache.type.CacheOpType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description： redis  注解方式 使用demo
 *
 * @author: zhaomeinan
 * date: 2018/6/29 9:59
 */
@Service
public class RedisService {

    /**
     * description： 如果缓存中有数据，则使用缓存中的数据，如果缓存中没有数据，则加载数据，并写入缓存。
     * @author: zhaomeinan
     * date: 2018/6/29 14:15
     */
    @Cache(expire = 0,  key = "'test:' + #args[0]")
    public String get(String param) {
        return "这是一个查询，您输入的内容是：" + param;
    }

    /**
     * description： 如果缓存中有数据，则使用缓存中的数据，如果缓存中没有数据，则加载数据，并写入缓存，10s后数据从缓存中自动删除
     * @author: zhaomeinan
     * date: 2018/6/29 14:44
     */
    @Cache(expire = 10, key = "'test:' + #args[0]")
    public String getWithExpire(String param) {
        return "这是一个查询，您输入的内容是：" + param;
    }

    /**
     * description： 从数据源中加载最新的数据，并写入缓存
     * @author: zhaomeinan
     * date: 2018/6/29 14:46
     */
    @Cache(expire = 0,  key = "'test:' + #args[0]", opType = CacheOpType.WRITE)
    public String save(String param) {
        return "1";
    }

    /**
     * description：从缓存中删除数据
     * @author: zhaomeinan
     * date: 2018/6/29 14:46
     */
    @CacheDelete({ @CacheDeleteKey(value = "'test:' + #args[0]", condition = "#retVal > 0") })
    public int remove(String param) {
        return 1;
    }

    /**
     * description： 自动重新加载数据，autoload = true时，expire时间不能小于120，会在数据到达过期时间前的某个时间重新自动加载数据到缓存
     * @author: zhaomeinan
     * date: 2018/6/29 16:59
     */
    @Cache(expire = 121, key = "'test:' + #args[0]", autoload = true)
    public Long autoload(int param){
        System.out.println(this.getTime() + " autoload 正在自动加载数据...");
        System.out.println("param：" + param);
        return System.currentTimeMillis();
    }

    /**
     * description： 缓存数据在 requestTimeout 秒之内没有使用了，就不进行自动加载数据,如果requestTimeout为0时，会一直自动加载
     * @author: zhaomeinan
     * date: 2018/6/29 16:59
     */
    @Cache(expire = 121, key = "'test:' + #args[0]", autoload = true, requestTimeout = 300)
    public int autoloadWiteRequestTimeout(int param){
        System.out.println(this.getTime() + " autoloadWiteRequestTimeout 正在自动加载数据...");
        System.out.println("param：" + param);
        return param + 1;
    }

    public String getTime(){
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String str = null;
        try {
            str = sDateFormat.format(new Date());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
