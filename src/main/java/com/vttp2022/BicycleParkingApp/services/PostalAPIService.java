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

import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.PostalQuery;

@Service
public class PostalAPIService {
  private static final Logger logger = LoggerFactory.getLogger(PostalAPIService.class);

  private static String URL = "https://developers.onemap.sg/commonapi/search";

  public static Optional<Postal> getPostalDetails(PostalQuery q){

    String postalUrl = UriComponentsBuilder.fromUriString(URL)
      .queryParam("searchVal", q.getPostalCode())
      .queryParam("returnGeom", q.getReturnGeom())
      .queryParam("getAddrDetails", q.getGetAddrDetails())
      .toUriString();
    //logger.info(postalUrl);
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = null;

    try {
      HttpHeaders headers = new HttpHeaders();
      HttpEntity request = new HttpEntity(headers);

      resp = template.exchange(postalUrl, HttpMethod.GET, request, String.class, 1);
      Postal p = Postal.createJson(resp.getBody());
      return Optional.of(p);
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }
    return Optional.empty();
  }
  
}