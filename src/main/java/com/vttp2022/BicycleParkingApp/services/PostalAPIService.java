package com.vttp2022.BicycleParkingApp.services;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vttp2022.BicycleParkingApp.models.Postal;
import com.vttp2022.BicycleParkingApp.models.PostalQuery;

@Service
public class PostalAPIService {
  private static final Logger logger = LoggerFactory.getLogger(PostalAPIService.class);

  private static String URL = "https://developers.onemap.sg/commonapi/search";

  public Optional<Postal> findLatLong(PostalQuery q){
    //String apiKey = System.getenv("POSTAL_CODE_API_KEY");
    //String accept = "application/json";

    String postalUrl = UriComponentsBuilder.fromUriString(URL)
      .queryParam("searchVal", q.getPostalCode())
      .queryParam("returnGeom", q.getReturnGeom())
      .queryParam("getAddrDetails", q.getGetAddrDetails())
      .toUriString();
    logger.info(postalUrl);
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = null;

    try {
      HttpHeaders headers = new HttpHeaders();
      //headers.set("AccountKey", apiKey);
      //headers.set("accept", accept);
      HttpEntity request = new HttpEntity(headers);

      resp = template.exchange(postalUrl, HttpMethod.GET, request, String.class, 1);
      //logger.info(resp.getBody());
      Postal p = Postal.createJson(resp.getBody());
      return Optional.of(p);
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }
    return Optional.empty();
  }
  
}