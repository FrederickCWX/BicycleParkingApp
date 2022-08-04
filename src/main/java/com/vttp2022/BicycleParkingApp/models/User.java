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

  public User(){
    this.id = generateId(8);
  }

  public User(String username){
    this.id = generateId(8);
    this.username = username;
  }
  
  public User(String username, String id){
    this.id = id;
    this.username = username;
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

  
}
