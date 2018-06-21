package ocean;

import ocean.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author xieyi
 */
@Component("redisCache")
public class JedisApi {

    @Autowired
    private JedisPool jedisPool;

    @Value("${spring.redis.key.timeout}")int timeout;

    /**
     * 从Redis缓存获取数据
     * @param redisKey
     * @return
     */
    public Object getObject(String redisKey){
        Jedis jedis = jedisPool.getResource();
        Object object = null;
        try{
            byte[] byteArray = jedis.get(redisKey.getBytes());
            if(byteArray != null){
                object = SerializeUtil.unSerialize(byteArray);
            }
        }finally {
            jedis.close();
            return object;
        }
    }
    /**
     * 保存数据到Redis
     * @param redisKey
     */
    public String saveObject(String redisKey,Object obj){
        byte[] bytes = SerializeUtil.serialize(obj);
        Jedis jedis = jedisPool.getResource();
        String code = null;
        try {
            code = jedis.set(redisKey.getBytes(), bytes);
            jedis.expire(redisKey.getBytes(),timeout);
        }finally {
            jedis.close();
            return code;
        }
    }

    public Object delObject(String redisKey){
        Jedis jedis = jedisPool.getResource();
        Long num = null;
        try {
            num = jedis.del(redisKey);
            jedis.expire(redisKey.getBytes(),timeout);
        }finally {
            jedis.close();
            return num;
        }
    }

    public Object getHashes(String key1, String key2) {
        Jedis jedis = jedisPool.getResource();
        Object object = null;
        try {
            object = jedis.hget(SerializeUtil.serialize(key1),SerializeUtil.serialize(key2));
        }finally {
            jedis.close();
            return object;
        }
    }

    public Object setHashes(String key1, String key2,Object object) {
        Jedis jedis = jedisPool.getResource();
        Long num = null;
        try {
            num = jedis.hset(SerializeUtil.serialize(key1),SerializeUtil.serialize(key2),SerializeUtil.serialize(object));
        }finally {
            jedis.close();
            return num;
        }
    }

    public Object delHashes(String key1, String key2) {
        Jedis jedis = jedisPool.getResource();
        Long num = null;
        try {
            num = jedis.hdel(key1,key2);
        }finally {
            jedis.close();
            return num;
        }
    }

}