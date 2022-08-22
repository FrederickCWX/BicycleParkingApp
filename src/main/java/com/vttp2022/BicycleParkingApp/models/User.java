package com.vttp2022.BicycleParkingApp.models;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.slf4j.*;
import org.springframework.stereotype.Component;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

@Component(value = "user")
public class User implements Serializable{
  private static final Logger logger = LoggerFactory.getLogger(User.class);

  private String username;
  private String id;
  private List<Value> data;
  private int found;

  private int insertCount;
  private int updateCount;
  private boolean upsert;

  public User(){
    this.id = generateId(8);
    this.found = getFound();
  }

  public User(String username){
    this.id = generateId(8);
    this.username = username;
    this.found = getFound();
  }
  
  public User(String username, String id){
    this.id = id;
    this.username = username;
    this.found = getFound();
  }

  public User(String username, String id, List<Value> data){
    this.id = id;
    this.username = username;
    this.data = data;
    this.found = getFound();
  }
  
  public int getFound(){

    if(data == null)
      found = 0;
    else
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Value> getData() {
    return data;
  }

  public void setData(List<Value> data) {
    this.data = data;
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
