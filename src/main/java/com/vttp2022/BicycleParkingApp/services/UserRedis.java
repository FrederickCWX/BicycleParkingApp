
package com.vttp2022.BicycleParkingApp.services;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.vttp2022.BicycleParkingApp.models.User;

@Service
public class UserRedis implements UserRepo{
  
  private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

  //@Autowired
  //@Qualifier("user")
  //RedisTemplate<String, User> redisTemplate;

  @Autowired
  RedisTemplate<String, User> redisTemplate;

  @Override
  public int save(final User user){
    logger.info("Save value > "+ logger);
    redisTemplate.opsForValue().set(user.getId(), user);
    User result = (User) redisTemplate.opsForValue().get(user.getId());
    if(result != null)
      return 1;
    return 0;
  }

  @Override
  public User findById(final String userId){
    logger.info("Find value by id > "+userId);
    User result = (User) redisTemplate.opsForValue().get(userId);
    return result;

  }
  
  
}

