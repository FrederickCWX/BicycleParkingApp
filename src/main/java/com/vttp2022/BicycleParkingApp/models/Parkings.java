package com.vttp2022.BicycleParkingApp.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.*;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Parkings {
  private static final Logger logger = LoggerFactory.getLogger(Parkings.class);

  private String metadata;
  private static List<Value> details = new ArrayList<>();

  private Query query;

  public String getMetadata(){
    return metadata;
  }

  public void setMetadata(String metadata){
    this.metadata = metadata;
  }

  public static List<Value> getValue() {
    return details;
  }

  public void setValue(List<Value> details) {
    this.details = details;
  }

  public Query getQuery(){
    return query;
  }

  public void setQuery(Query query){
    this.query = query;
  }

  public static Parkings createJson(String json) throws IOException{
    logger.info("createJson parkings");
    Parkings p = new Parkings();
    logger.info(json);

    try(InputStream in = new ByteArrayInputStream(json.getBytes())){
      JsonReader jr = Json.createReader(in);
      
      JsonObject jo = jr.readObject();
      //JsonArray ja = new StringReader(jo).readArray();
      //ERROR - jo is null because resp does not include query
      logger.info(">>>>> " + jo.getJsonArray("value"));
      //p.query = Query.createJson(jo.getJsonObject("query"));
      JsonArray ja = jo.getJsonArray("value");

      if(ja != null){
        List<Value> requested = new ArrayList<>();
        //Value value;
        for(Object jv: ja){
          JsonObject joValue = (JsonObject) jv;
          requested.add(Value.createJson(joValue));
        }
        p.details = requested;
      }
      logger.info(">>>>> " + p.toString());
    }
    return p;
  }
  
}
