package ocean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author xieyi
 */
@Configuration
public class JedisConfig {
    @Bean(name= "jedisPool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig config,
                               @Value("${spring.redis.host}")String host,
                               @Value("${spring.redis.port}")int port,
                               @Value("${spring.redis.pool.timeout}")int timeout,
                               @Value("${spring.redis.password}")String password) {
        return new JedisPool(config, host, port,timeout,password);
    }

    @Bean(name= "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig (@Value("${spring.redis.pool.minIdle}")int minIdle,
                                            @Value("${spring.redis.pool.maxIdle}")int maxIdle,
                                            @Value("${spring.redis.pool.maxWait}")int maxWait
                                           ) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        return config;
    }
}