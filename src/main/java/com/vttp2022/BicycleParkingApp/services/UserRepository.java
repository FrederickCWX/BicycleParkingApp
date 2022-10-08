package com.vttp2022.BicycleParkingApp.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Value;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
public class UserRepository {
  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @Autowired
  @Qualifier("redis")
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  User usr;

  public void saveFav(){

    logger.info("Save to REPO");
    logger.info("REPO username: "+usr.getUsername());
    ListOperations<String, String> listOps = redisTemplate.opsForList();

    if(!redisTemplate.hasKey(usr.getUsername())){
      listOps.leftPush(usr.getUsername(), toJson(usr.getFavourites()).toString());
      logger.info("User does not exist");
      return;
    }

    logger.info("Saving user");
    redisTemplate.delete(usr.getUsername());
    listOps.leftPush(usr.getUsername(), toJson(usr.getFavourites()).toString());
  }

  public Optional<User> getFavourites(String username){
    if(!redisTemplate.hasKey(username))
      return Optional.empty();
    
    
    ListOperations<String, String> listOps = redisTemplate.opsForList();
    String haul = "";
    long size = listOps.size(username);
    for(long i=0; i<size; i++){
      haul = listOps.index(username, i);
    }
    
    List<JsonObject> joList = getParking(haul);
    List<Value> favList = new LinkedList<>();

    for(JsonObject jo: joList){
      favList.add(Value.createFavJson(jo));
    }

    User user = User.createUser(username, favList);

    return Optional.of(user);
  }

  private List<JsonObject> getParking(String haul){

    Reader stringReader = new StringReader(haul);
    JsonReader jReader = Json.createReader(stringReader);
    JsonObject haulObject = jReader.readObject();
    JsonArray jArray = haulObject.getJsonArray("value");

    List<JsonObject> joList = new LinkedList<>();
    jArray.forEach(jo -> joList.add((JsonObject) jo));

    return joList;
  }

  private JsonObject toJson(List<Value> valueList){

    JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
    for(Value value: valueList)
      jaBuilder.add(value.toJson());

    JsonObject haul = Json.createObjectBuilder()
        .add("value", jaBuilder.build())
        .build();

    return haul;
  }

}
