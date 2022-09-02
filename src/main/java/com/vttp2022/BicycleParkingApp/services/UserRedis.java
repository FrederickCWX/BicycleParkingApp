
package com.vttp2022.BicycleParkingApp.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

//import com.vttp2022.BicycleParkingApp.models.UserOld;
import com.vttp2022.BicycleParkingApp.models.User;

@Service
public class UserRedis implements UserRepo{
  
  private static final Logger logger = LoggerFactory.getLogger(UserRedis.class);

  /* 
  @Autowired
  @Qualifier("user")
  RedisTemplate<String, User> redisTemplate;
  */
  
  @Autowired
  RedisTemplate<String, User> redisTemplate;
  

  @Override
  public int save(final User user){
    logger.info("Save user > "+ logger);
    redisTemplate.opsForValue().set(user.getUsername(), user);
    User result = (User) redisTemplate.opsForValue().get(user.getUsername());
    if(result != null)
      return 1;
    return 0;
  }

  /*
  @Override
  public UserOld findById(final String userId){
    logger.info("Find user by id > "+userId);
    UserOld result = (UserOld) redisTemplate.opsForValue().get(userId);
    return result;
  }
  */

  @Override
  public User findByUsername(final String username){
    logger.info("Find user by username > "+username);
    User result = (User) redisTemplate.opsForValue().get(username);
    return result;
  }

  @Override
  public int update(final User user){
    if(user.isUpsert())
      redisTemplate.opsForValue().setIfAbsent(user.getUsername(), user);
    else
      redisTemplate.opsForValue().setIfPresent(user.getUsername(), user);
    User result = (User) redisTemplate.opsForValue().get(user.getUsername());
    if(result != null)
      return 1;
    return 0;
  }

  public User[] getAllUsers(){
    Set<String> allUserKeys = redisTemplate.keys("*");
    List<User> userArray = new LinkedList<User>();
    for(String userKey: allUserKeys){
      User result = (User) redisTemplate.opsForValue().get(userKey);
      userArray.add(result);
    }

    return userArray.toArray(new User[userArray.size()]);
  }

  /*
   * @Autowired
  RedisTemplate<String, UserOld> redisTemplate;
  

  @Override
  public int save(final UserOld user){
    logger.info("Save user > "+ logger);
    redisTemplate.opsForValue().set(user.getId(), user);
    UserOld result = (UserOld) redisTemplate.opsForValue().get(user.getId());
    if(result != null)
      return 1;
    return 0;
  }

  @Override
  public UserOld findById(final String userId){
    logger.info("Find user by id > "+userId);
    UserOld result = (UserOld) redisTemplate.opsForValue().get(userId);
    return result;
  }

  @Override
  public UserOld findByUsername(final String username){
    logger.info("Find user by username > "+username);
    UserOld result = (UserOld) redisTemplate.opsForValue().get(username);
    return result;
  }

  @Override
  public int update(final UserOld user){
    if(user.isUpsert())
      redisTemplate.opsForValue().setIfAbsent(user.getId(), user);
    else
      redisTemplate.opsForValue().setIfPresent(user.getId(), user);
    UserOld result = (UserOld) redisTemplate.opsForValue().get(user.getId());
    if(result != null)
      return 1;
    return 0;
  }

  public UserOld[] getAllUsers(){
    Set<String> allUserKeys = redisTemplate.keys("*");
    List<UserOld> userArray = new LinkedList<UserOld>();
    for(String userKey: allUserKeys){
      UserOld result = (UserOld) redisTemplate.opsForValue().get(userKey);
      userArray.add(result);
    }

    return userArray.toArray(new UserOld[userArray.size()]);
  }
   */
  
  
}

