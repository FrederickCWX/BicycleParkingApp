package com.vttp2022.BicycleParkingApp.models;

import org.springframework.stereotype.Component;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

@Component(value = "user")
public class User implements Serializable{
  private static final Logger logger = LoggerFactory.getLogger(User.class);

  private static String username;
  private static List<Value> favourites;
  private static int favFound;

  private int insertCount;
  private int updateCount;
  private boolean upsert;

  public User(){
    logger.info("new user");
    this.favourites = new ArrayList<>();
    this.favFound = 0;
  }

  public User(String username){
    logger.info("new user > "+username);
    this.username = username;
    this.favourites = new ArrayList<>();
    this.favFound = 0;
  }

  public User(String username, List<Value> favourites){
    this.username = username;
    this.favourites = favourites;
    this.favFound = favourites.size();
  }

  public static boolean addFavourite(Value value){
    boolean found = false;
    for(Value favourite: favourites){
      if(favourite.getDescription().equals(value.getDescription()))
        found = true;
    }
    if(found == false){
      favourites.add(value);
      favFound++;
      return true;
    }else
      return false;
  }

  public static boolean removeFavourite(Value value){
    boolean found = false;
    for(int x=0; x < favourites.size(); x++){
      if(favourites.get(x).getDescription().equals(value.getDescription())){
        found = true;
        favourites.remove(x);
        favFound--;
      }
    }
    if(found == true)
      return true;
    else
      return false;
  }



  public static String getUsername() {
    return username;
  }

  public static void setUsername(String username) {
    User.username = username;
  }

  public static List<Value> getFavourites() {
    return favourites;
  }

  public static void setFavourites(List<Value> favourites) {
    User.favourites = favourites;
  }

  public static int getFavFound() {
    return favFound;
  }

  public static void setFavFound(int favFound) {
    User.favFound = favFound;
  }

  
  public int getInsertCount() {
    return insertCount;
  }

  public void setInsertCount(int insertCount) {
    this.insertCount = insertCount;
  }

  public int getUpdateCount() {
    return updateCount;
  }

  public void setUpdateCount(int updateCount) {
    this.updateCount = updateCount;
  }

  public boolean isUpsert() {
    return upsert;
  }

  public void setUpsert(boolean upsert) {
    this.upsert = upsert;
  }
  
}
