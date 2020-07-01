package com.myself.shiroproject.config.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @ClassName RedisConfig
 * @Description
 * @Author vici
 * @Date 2020/4/26 15:10
 * @Version V2.0
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport{


    private static final Log log = LogFactory.getLog(RedisConfig.class);

    /*@Value("${spring.redis.host}")
    private String host;  // Redis服务器地址
    @Value("${spring.redis.port}")
    private int port;  // Redis服务器连接端口
    @Value("${spring.redis.password}")
    private String password;  // Redis服务器连接密码（默认为空）
    @Value("${spring.redis.timeout}")
    private int timeout;  // 连接超时时间（毫秒）
    @Value("${spring.redis.database}")
    private int database;  // 连接超时时间（毫秒）
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;  // 连接池最大连接数（使用负值表示没有限制）
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;  // 连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;  // 连接池中的最大空闲连接
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;  // 连接池中的最小空闲连接*/


    /**
     * 配置jedispoolConfig
     * 连接池的配置信息
     * @return
     */
    /*@Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        log.info("初始化JedisPoolConfig");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.maxTotal);  //  连接池最大连接数（使用负值表示没有限制）
        jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);  // 连接池最大阻塞等待时间（使用负值表示没有限制）
        jedisPoolConfig.setMaxIdle(this.maxIdle);  // 连接池中的最大空闲连接
        jedisPoolConfig.setMinIdle(this.minIdle);  // 连接池中的最小空闲连接
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnCreate(true);
//        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }*/

    /**
     * 实例化 RedisConnectionFactory 对象
     * 直接连接redis的配置，每次连接都会创建新的连接,当并发令剧增时，这会带来性能上的开销，同时由于没有对连接数的限制，
     * 则可能使服务器崩溃导致无法响应，所以还要配置建立连接池，实现初始化一组连接，供需要redis连接的线程取用，
     * springboot2。0就不支持直接显示连接信息了一方面为了使配置信息与建立连接工厂解耦，另一方面
     * 抽象出Standalone,Sentinel和RedisCluster三种模式的环境配置类
     * 和一个统一的jedis客户端连接配置类（用于配置连接池和SSL连接），
     * 使得我们可以更加灵活方便根据实际业务场景需要来配置连接信息。
     * @param poolConfig
     * @return
     */
   /* @Bean(name = "jedisConnectionFactory")
    public RedisConnectionFactory jedisConnectionFactory(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig) {
        log.info("初始化RedisConnectionFactory");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);

        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setPort(this.port);
        jedisConnectionFactory.setDatabase(this.database);
        return jedisConnectionFactory;
    }*/

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式

        template.setKeySerializer(stringRedisSerializer);

        // hash的key也采用String的序列化方式

        template.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }



    @Bean
    @SuppressWarnings("all")
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

        //common信息缓存配置
        RedisCacheConfiguration userCacheConfiguration = defaultCacheConfig
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).disableCachingNullValues();
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //entryTtl设置缓存失效时间，单位是秒
        redisCacheConfigurationMap.put("common", userCacheConfiguration.entryTtl(Duration.ofSeconds(30)));


        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("common");
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(defaultCacheConfig).initialCacheNames(cacheNames).withInitialCacheConfigurations(redisCacheConfigurationMap).build();
        return cacheManager;
    }

}
