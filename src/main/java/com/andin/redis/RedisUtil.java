package com.andin.redis;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public final class RedisUtil {
	
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	@Autowired
    private RedisTemplate<Object, Object> redisTemplate;
	
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 3600;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    
    public void set(String key, Object value, long expire) {
        try {
        	redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        } catch (Exception e) {
        	logger.error("RedisUtil.set method execute is error: ", e.getMessage());
        }
    }

    public void set(String key, Object value) {
        try {
        	redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
        	logger.error("RedisUtil.set method execute is error: ", e.getMessage());
        }
    }
 
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        if(clazz.isInstance(value)){
            return clazz.cast(value);
        }else {
        	return null;        	
        }
    }
    
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
    
    public void delete(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    
}
