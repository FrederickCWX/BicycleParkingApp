package com.vttp2022.BicycleParkingApp.models.postal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Postal {
  private static final Logger logger = LoggerFactory.getLogger(Postal.class);

  private Integer found;
  private Integer totalNumPages;
  private Integer pageNum;
  private static List<Results> results = new ArrayList<>();

  private PostalQuery postalQuery;

  public Integer getFound() {
    return found;
  }

  public void setFound(Integer found) {
    this.found = found;
  }

  public Integer getTotalNumPages() {
    return totalNumPages;
  }

  public void setTotalNumPages(Integer totalNumPages) {
    this.totalNumPages = totalNumPages;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public static List<Results> getResults() {
    return results;
  }

  public static void setResults(List<Results> results) {
    Postal.results = results;
  }

  public PostalQuery getPostalQuery() {
    return postalQuery;
  }

  public void setPostalQuery(PostalQuery postalQuery) {
    this.postalQuery = postalQuery;
  }

  public static Postal createJson(String json) throws IOException{
    logger.info("createJson postal");
    Postal p = new Postal();
    //logger.info(json);

    try(InputStream in = new ByteArrayInputStream(json.getBytes())){
      JsonReader jr = Json.createReader(in);
      
      JsonObject jo = jr.readObject();
      logger.info(">>>>> " + jo.getJsonArray("results"));;
      JsonArray ja = jo.getJsonArray("results");

      if(ja != null){
        List<Results> results = new ArrayList<>();
        /*
        if(results.size() > 0){
          JsonObject joValue = (JsonObject) ja.get(0);
          results.add(Results.createJson(joValue));
        }
        */
        
        for(Object jv: ja){
          JsonObject joValue = (JsonObject) jv;
          results.add(Results.createJson(joValue));
        }
        
        logger.info("createJson value");
        Postal.results = results;
      }
      logger.info(">>>>> " + p.toString());
    }
    return p;
  }
  
}
