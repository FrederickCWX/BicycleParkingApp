package com.vttp2022.BicycleParkingApp.models;

import java.math.BigDecimal;

public class Query {

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
  
}
