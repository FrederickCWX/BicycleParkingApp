package com.vttp2022.BicycleParkingApp.oldClasses;
/*
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.slf4j.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;;
*/
public class Parking {
  /*
  private static final Logger logger = LoggerFactory.getLogger(Parking.class);

  private String description;
  private BigDecimal lat;
  private BigDecimal lng;
  private String rackType;
  private Integer rackCount;
  private String shelter;

  private Query query;

  

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

  public Query getQuery() {
    return query;
  }

  public void setQuery(Query query) {
    this.query = query;
  }

  public static Parking createJson(String json) throws IOException{
    logger.info("createJson parking");
    Parking p = new Parking();
    

    try(InputStream in = new ByteArrayInputStream(json.getBytes())){
      JsonReader jr = Json.createReader(in);
      JsonObject jo = jr.readObject();
      logger.info(">>>>> " + jo.getJsonObject("query"));
      p.query = Query.createJson(jo.getJsonObject("query"));
      p.description = jo.getJsonString("Description").getString();
      p.lat = jo.getJsonNumber("Latitude").bigDecimalValue();
      p.lng = jo.getJsonNumber("Longitude").bigDecimalValue();
      p.rackType = jo.getJsonString("RackType").getString();
      p.rackCount = jo.getJsonNumber("RackCount").intValue();
      p.shelter = jo.getJsonString("ShelterIndicator").getString();
      logger.info(">>>>> " + p.toString());
    }
    return p;
  }
  */
}
