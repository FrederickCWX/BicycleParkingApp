package com.vttp2022.BicycleParkingApp.models;

import org.springframework.stereotype.Component;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.*;

@Component(value = "user")
public class User implements Serializable{
  private static final Logger logger = LoggerFactory.getLogger(User.class);

  public static String username;
  private static List<Value> favourites;

  public static String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public static List<Value> getFavourites() {
    return favourites;
  }
  public void setFavourites(List<Value> favourites) {
    this.favourites = favourites;
  }

  public static User createUser(String username){
    User user = new User();
    user.setUsername(username);
    user.setFavourites(new LinkedList<>());
    
    return user;
  }

  public static User createUser(String username, List<Value> favourites){
    User user = new User();
    user.setUsername(username);
    user.setFavourites(favourites);
    
    return user;
  }

  
  public static void addFavourite(Value value){
    logger.info("Pre-save");
    favourites.add(value);
    logger.info("Post-save > " + favourites.size());
  }
  

  public static void rmvFavourite(int x){
    logger.info("Pre-rmv > " + favourites.size());
    favourites.remove(x);
    logger.info("Post-rmv > " + favourites.size());
  }

  /*
  public static void rmvFavourite(Value value){
    favourites.remove(value);
  }
  */


  /*
  public static boolean removeFavourite(Value value){
    boolean success = false;
    for(Value favourite: favourites){
      if(favourite.equals(value)){
        favourites.remove(value);
        success = true;
      }
    }

    return success;
  }
  */

  public JsonObject toJson(){
    JsonObjectBuilder joBuilder = Json.createObjectBuilder();
    joBuilder.add("username", this.username)
        .add("value", toJsonArray());
    
    return joBuilder.build();
    
  }

  private JsonArray toJsonArray(){
    JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
    for(Value v: favourites){
      jaBuilder.add(v.toJson());
    }

    return jaBuilder.build();
  }

}
