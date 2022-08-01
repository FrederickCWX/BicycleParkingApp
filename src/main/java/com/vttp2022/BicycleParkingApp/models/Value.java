package com.vttp2022.BicycleParkingApp.models;

import java.math.BigDecimal;

import org.slf4j.*;

import com.vttp2022.BicycleParkingApp.oldClasses.Parking;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

public class Value {
  private static final Logger logger = LoggerFactory.getLogger(Parking.class);

  private String description;
  private BigDecimal lat;
  private BigDecimal lng;
  private String rackType;
  private Integer rackCount;
  private String shelter;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getLat() {
    return lat;
  }

  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  public BigDecimal getLng() {
    return lng;
  }

  public void setLng(BigDecimal lng) {
    this.lng = lng;
  }

  public String getRackType() {
    return rackType;
  }

  public void setRackType(String rackType) {
    this.rackType = rackType;
  }

  public Integer getRackCount() {
    return rackCount;
  }

  public void setRackCount(Integer rackCount) {
    this.rackCount = rackCount;
  }

  public String getShelter() {
    return shelter;
  }

  public void setShelter(String shelter) {
    this.shelter = shelter;
  }
  /*
   * private String description;
  private BigDecimal lat;
  private BigDecimal lng;
  private String rackType;
  private Integer rackCount;
  private String shelter;
   */

  public static Value createJson(JsonObject jo){
    logger.info("createJson value");
    Value v = new Value();
    JsonString jsDesp = jo.getJsonString("Description");
    v.description = jsDesp.getString();
    JsonNumber jnLat = jo.getJsonNumber("Latitude");
    v.lat = jnLat.bigDecimalValue();
    JsonNumber jnLng = jo.getJsonNumber("Longitude");
    v.lng = jnLng.bigDecimalValue();
    JsonString jsType = jo.getJsonString("RackType");
    v.rackType = jsType.getString();
    JsonNumber jnCount = jo.getJsonNumber("RackCount");
    v.rackCount = jnCount.intValue();
    JsonString jsShelter = jo.getJsonString("ShelterIndicator");
    v.shelter = jsShelter.getString();
    logger.info(
      "Description: "+v.description+"\n"+
      "Latitude: "+v.lat+"\n"+
      "Longitude: "+v.lng+"\n"+
      "RackType: "+v.rackType+"\n"+
      "RackCount: "+v.rackCount+"\n"+
      "ShelterIndicator: "+v.shelter
    );
    return v;
  }

  /*
  public static Query createJson(JsonObject jo){
    logger.info("createJson query");
    Query q = new Query();
    JsonNumber jnLat = jo.getJsonNumber("Lat");
    q.lat = jnLat.bigDecimalValue();
    JsonNumber jnLng = jo.getJsonNumber("Long");
    q.lng = jnLng.bigDecimalValue();
    JsonNumber jnRad = jo.getJsonNumber("Dist");
    q.dist = jnRad.doubleValue();
    return q;
  }
  */

  /* 
  public static Parkings createJson(String json) throws IOException{
    logger.info("createJson parkings");
    Parkings p = new Parkings();
    

    try(InputStream in = new ByteArrayInputStream(json.getBytes())){
      JsonReader jr = Json.createReader(in);
      
      JsonObject jo = jr.readObject();
      //JsonArray ja = new StringReader(jo).readArray();
      logger.info(">>>>> " + jo.getJsonObject("query"));
      p.query = Query.createJson(jo.getJsonObject("query"));
      p.value = jo.getJsonArray("value");

      logger.info(">>>>> " + p.toString());
    }
    return p;
  }
  */
  
}
