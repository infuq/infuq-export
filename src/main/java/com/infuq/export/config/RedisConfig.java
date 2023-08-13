package com.infuq.export.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

@Configuration
@Slf4j
public class RedisConfig {


    @Autowired
    private Environment environment;


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        printRedisConnInfo(redisConnectionFactory);


        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // key 和 hashKey 采用 string 序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());

        // value 和 hashValue 采用 JSON 序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        return redisTemplate;


    }

    private void printRedisConnInfo(RedisConnectionFactory connectionFactory) {
        try {
            String env = environment.getProperty("spring.profiles.active");

            if (StringUtils.isEmpty(env)) {
                return;
            }

            if (env.contains("dev") || env.contains("test") || env.contains("pre")) {
                RedissonConnectionFactory connfactory = ((RedissonConnectionFactory) connectionFactory);
                Field field = connfactory.getClass().getDeclaredField("redisson");
                field.setAccessible(true);
                Redisson redisson = (Redisson) field.get(connfactory);
                Config config = redisson.getConfig();

                readFieldValue(config, "singleServerConfig");
                readFieldValue(config, "clusterServersConfig");
                readFieldValue(config, "masterSlaveServersConfig");
            }
        } catch (Throwable ignored) { }
    }


    private void readFieldValue(Config config, String fieldName) {
        try {
            Class<? extends Config> clazz = config.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(config);
            log.info("Redis连接信息({}):{}", fieldName, JSON.toJSONString(value));
        } catch (Exception ignored) {}
    }


}
