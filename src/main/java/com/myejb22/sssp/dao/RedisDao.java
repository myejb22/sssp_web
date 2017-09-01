package com.myejb22.sssp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/9/1
 */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 普通字符串写入redis
     *
     * @param key
     * @param value
     */
    public void writeString(String key, String value) {
        redisTemplate.execute((RedisCallback<Object>) (connection) -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            connection.set(redisKey, redisVal);
            return null;
        });
    }

    /**
     * 根据redis的Key来查询普通的String值
     *
     * @param key
     * @return
     */
    public String readStringByKey(String key) {
        return redisTemplate.execute((RedisCallback<String>) (connection) -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return redisTemplate.getStringSerializer().deserialize(connection.get(redisKey));
        });
    }

    /**
     * 根据redis的key进行删除
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            if (connection.exists(redisKey)) {
                Long count = connection.del(redisKey);
                if (count > 0) return true;
            }
            return false;
        });
    }

    /**
     * @param key
     * @param value
     */
    public void appendStr(String key, String value) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            return connection.append(redisKey, redisVal);
        });
    }

    /**
     * 普通字符串的长度
     *
     * @param key
     * @return
     */
    public Long strLength(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.strLen(redisKey);
        });
    }

    /**
     * 自增计数器
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.incr(redisKey);
        });
    }

    /**
     * 每次自增多少
     *
     * @param key
     * @param value
     * @return
     */
    public Long incrby(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.incrBy(redisKey, value);
        });
    }

    /**
     * 自减计数器
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.decr(redisKey);
        });
    }


    /**
     * 每次自减多少
     *
     * @param key
     * @param value
     * @return
     */
    public Long decrby(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return connection.decrBy(redisKey, value);
        });
    }

    /**
     * 根据index的下标，在key中插入值
     *
     * @param key
     * @param value
     * @param offset
     */
    public void setRang(String key, String value, long offset) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            connection.setRange(redisKey, redisVal, offset);
            return null;
        });
    }

    /**
     * 获取begin与end之间的数据
     *
     * @param key
     * @param begin
     * @param end
     * @return
     */
    public String getRang(String key, long begin, long end) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            return redisTemplate.getStringSerializer().deserialize(connection.getRange(redisKey, begin, end));
        });
    }

    /**
     * 设置当前key的过期时间
     *
     * @param key
     * @param seconds 过期时间（单位：秒）
     * @param value
     */
    public void setEx(String key, long seconds, String value) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            connection.setEx(redisKey, seconds, redisVal);
            return null;
        });
    }

    /**
     * 增加前判断当前KEY是否存在，如果存在不写入
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setNX(String key, String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            return connection.setNX(redisKey, redisVal);
        });
    }

    /**
     * Set multiple keys to multiple values using key-value pairs provided in {@code tuple} only if the provided key does
     * not exist.
     *
     * @param keys
     * @param values
     */
    public void mSet(String[] keys, String[] values) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            if (null != keys && keys.length > 0) {
                int count = 0;
                for (String key : keys) {
                    byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
                    byte[] redisVal = redisTemplate.getStringSerializer().serialize(values[count]);
                    Map<byte[], byte[]> tuple = new HashMap<>();
                    tuple.put(redisKey, redisVal);
                    connection.mSet(tuple);
                    count ++;
                }
            }

            return null;
        });
    }

    /**
     * Get multiple {@code keys}. Values are returned in the order of the requested keys.
     *
     * @param keys
     */
    public List<String> mGet(String... keys) {
        return redisTemplate.execute((RedisCallback<List<String>>) connection -> {
            List<String> stringList = new ArrayList<>();
            if (null != keys && keys.length > 0) {
                for (String key : keys) {
                    List<byte[]> byteList = connection.mGet(redisTemplate.getStringSerializer().serialize(key));
                    if (null != byteList && byteList.size() > 0) {
                        for (byte[] v : byteList) {
                            stringList.add(redisTemplate.getStringSerializer().deserialize(v));
                        }
                    }
                }
            }
            return stringList;
        });
    }

    /**
     * Set {@code value} of {@code key} and return its old value.
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        return redisTemplate.execute((RedisCallback<String>) connection ->{
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisVal = redisTemplate.getStringSerializer().serialize(value);
            return redisTemplate.getStringSerializer().deserialize(connection.getSet(redisKey, redisVal));
        });
    }

}
