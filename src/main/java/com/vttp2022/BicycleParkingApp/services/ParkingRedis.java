/*
package com.vttp2022.BicycleParkingApp.services;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

@Service
public class ParkingRedis implements ParkingRepo{
  
  private static final Logger logger = LoggerFactory.getLogger(ParkingRedis.class);

  @Autowired
  @Qualifier("value")
  RedisTemplate<String, Value> redisTemplate;

  @Override
  public int save(final Value val){
    logger.info("Save value > "+ logger);
    redisTemplate.opsForValue().set(val.getId(), val);
    Value result = (Value) redisTemplate.opsForValue().get(val.getId());
    if(result != null)
      return 1;
    return 0;
  }

  @Override
  public Value findById(final String valId){
    logger.info("Find value by id > "+valId);
    Value result = (Value) redisTemplate.opsForValue().get(valId);
    return result;

  }
  
  @Override
  public int update(final Value val){
    
    logger.info("Save value > "+logger);
    if(val.isUpsert())
      redisTemplate.opsForValue().setIfAbsent(val.getId(), val);
    else
      redisTemplate.opsForValue().setIfPresent(val.getId(), val);
    Value result = (Value) redisTemplate.opsForValue().get(val.getId());
    if(result != null)
      return 1;
      
    return 0;
  }
  
  
}
*/
