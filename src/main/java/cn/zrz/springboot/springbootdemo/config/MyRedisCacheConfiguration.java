package cn.zrz.springboot.springbootdemo.config;

import cn.zrz.springboot.springbootdemo.entity.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration  //将类标注为配置类
@EnableCaching  //开启缓存
public class MyRedisCacheConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置默认缓存序列器为json序列化器
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        return redisTemplate;
    }

    //springboot 1.x 创建方式
    /*@Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, User> myRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(myRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }*/

    //springboot 2.x 创建方式
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //配置缓存管理器为json序列化器
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<User>(User.class)));

        //使用自定义的配置构建缓存管理器
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();

        return cacheManager;
    }
}
