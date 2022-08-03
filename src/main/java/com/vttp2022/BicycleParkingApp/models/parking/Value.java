package com.vttp2022.BicycleParkingApp.models.parking;

import java.math.BigDecimal;

import org.slf4j.*;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

public class Value {
  private static final Logger logger = LoggerFactory.getLogger(Value.class);


  private String img;
  private String description;
  private BigDecimal lat;
  private BigDecimal lng;
  private String rackType;
  private Integer rackCount;
  private String shelter;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

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

  public static Value createJson(JsonObject jo){
    //logger.info("createJson value");
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
    v.img = createImgURL(v.lat, v.lng);
    //logger.info(v.img);
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
    return v;
  }

  public static String createImgURL(BigDecimal lat, BigDecimal lng){

    StringBuilder sb = new StringBuilder();
    sb.append("https://developers.onemap.sg/commonapi/staticmap/getStaticImage?layerchosen=default&lat=");
    sb.append(String.valueOf(lat));
    sb.append("&lng=");
    sb.append(String.valueOf(lng));
    sb.append("&zoom=17&height=350&width=350&polygons=&lines=&points=[");
    sb.append(String.valueOf(lat));
    sb.append(",");
    sb.append(String.valueOf(lng));
    sb.append(",\"255,0,0\",\"\"]&color=&fillColor=");
    String url = sb.toString();

    return url;
  }
  
}
