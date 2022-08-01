package com.vttp2022.BicycleParkingApp.models;

import java.math.BigDecimal;

import org.slf4j.*;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class Query {
  private static final Logger logger = LoggerFactory.getLogger(Query.class);

  private BigDecimal lat;
  private BigDecimal lng;
  private double dist;

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

  public double getRadius() {
    return dist;
  }

  public void setRadius(double dist) {
    this.dist = dist;
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
  
}
