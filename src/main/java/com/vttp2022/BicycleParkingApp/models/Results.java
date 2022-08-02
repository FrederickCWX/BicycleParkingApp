package com.vttp2022.BicycleParkingApp.models;

import java.math.BigDecimal;

import org.slf4j.*;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

public class Results {
  private static final Logger logger = LoggerFactory.getLogger(Results.class);

  private String searchVal;
  private BigDecimal x;
  private BigDecimal y;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private BigDecimal longtitude; 

  public String getSearchVal() {
    return searchVal;
  }

  public void setSearchVal(String searchVal) {
    this.searchVal = searchVal;
  }

  public BigDecimal getX() {
    return x;
  }

  public void setX(BigDecimal x) {
    this.x = x;
  }

  public BigDecimal getY() {
    return y;
  }

  public void setY(BigDecimal y) {
    this.y = y;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public BigDecimal getLongtitude() {
    return longtitude;
  }

  public void setLongtitude(BigDecimal longtitude) {
    this.longtitude = longtitude;
  }

  public static Results createJson(JsonObject jo){
    //logger.info("createJson value");
    Results r = new Results();
    JsonString jsSearchVal = jo.getJsonString("SEARCHVAL");
    r.searchVal = jsSearchVal.getString();
    JsonNumber jnX = jo.getJsonNumber("X");
    r.x = jnX.bigDecimalValue();
    JsonNumber jnY = jo.getJsonNumber("Y");
    r.y = jnY.bigDecimalValue();
    JsonNumber jnLatitude = jo.getJsonNumber("LATITUDE");
    r.latitude = jnLatitude.bigDecimalValue();
    JsonNumber jnLongitutde = jo.getJsonNumber("LONGITUDE");
    r.longitude = jnLongitutde.bigDecimalValue();
    JsonNumber jnLongtitude = jo.getJsonNumber("LONGTITUDE");
    r.longtitude = jnLongtitude.bigDecimalValue();
    /*
    logger.info(
      "Description: "+v.description+"\n"+
      "Latitude: "+v.lat+"\n"+
      "Longitude: "+v.lng+"\n"+
      "RackType: "+v.rackType+"\n"+
      "RackCount: "+v.rackCount+"\n"+
      "ShelterIndicator: "+v.shelter
    );
    */
    return r;
  }
  
}
