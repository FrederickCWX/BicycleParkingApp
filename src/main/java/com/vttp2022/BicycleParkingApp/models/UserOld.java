/*
package com.vttp2022.BicycleParkingApp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.*;
import org.springframework.stereotype.Component;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

@Component(value = "user")
public class UserOld implements Serializable{
  private static final Logger logger = LoggerFactory.getLogger(UserOld.class);

  private static String username;
  private static String id;
  private static List<Value> data = new ArrayList<>();
  //private static List<Value> data;
  private static int found = 0;

  

  
  public UserOld(){
    //this.id = generateId(8);
    this.id = username;
    //data = new ArrayList<>();
    this.data = data;
    this.found = data.size();
  }
  

  public UserOld(String username){
    //this.id = generateId(8);
    this.id = username;
    this.username = username;
    //data = new ArrayList<>();
    this.data = data;
    this.found = data.size();
  }
  
  public UserOld(String username, String id){
    this.id = id;
    this.username = username;
    //data = new ArrayList<>();
    this.data = data;
    this.found = data.size();
  }

  public UserOld(String username, String id, List<Value> data){
    this.id = id;
    this.username = username;
    //this.data = new ArrayList<>();
    this.data = data;
    this.found = data.size();
  }

  /*
  public User(String username, int found){
    this.id = id;
    this.username = username;
    this.data = data;
    this.found = found;
  }
  
  
  public static int getFound(){
    found = data.size();
    return found;
  }


  private synchronized String generateId(int numchars){
    Random r = new Random();
    StringBuilder sb = new StringBuilder();
    while(sb.length() < numchars){
      sb.append(Integer.toHexString(r.nextInt()));
    }
    return sb.toString().substring(0, numchars);
  }

  public static String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public static String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public static List<Value> getData() {
    return data;
  }

  public void setData(List<Value> data) {
    this.data = data;
  }

  public static void addData(Value value){
    data.add(value);
  }

  public int getInsertCount(){
    return insertCount;
  }

  public void setInsertCount(int insertCount){
    this.insertCount = insertCount;
  }

  public int getUpdateCount(){
    return updateCount;
  }

  public void setUpdateCount(int updateCount){
    this.updateCount = updateCount;
  }

  public boolean isUpsert(){
    return upsert;
  }

  public void setUpsert(boolean upsert){
    this.upsert = upsert;
  }

  
}
*/