package com.vttp2022.BicycleParkingApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String redisHost;

  @Value("${spring.redis.port}")
  private Integer redisPort;

  @Value("${spring.redis.database}")
	private Integer redisDB;

	@Value("${spring.redis.username}")
	private String redisUser;

  private String redisPassword = System.getenv("BIKE_PARKING_REDIS_PASSWORD");


  @Bean("redis")
  public RedisTemplate<String, String> redisTemplate(){
    final RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());

    return template;
  }

  @Bean
  public JedisConnectionFactory jedisFactory(){
    final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    config.setHostName(redisHost); 
    config.setPort(redisPort);
    config.setDatabase(redisDB);
		config.setUsername(redisUser);
    config.setPassword(redisPassword);

    final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
    final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
    jedisFac.afterPropertiesSet();

    return jedisFac;

  }
  
}
