package com.vttp2022.BicycleParkingApp.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.services.UserRepository;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/user", 
produces = MediaType.APPLICATION_JSON_VALUE)
public class HttpRestController {
  private static final Logger logger = LoggerFactory.getLogger(HttpRestController.class);

  @Autowired
  UserRepository usrRepo;

  @GetMapping("{username}")
  public ResponseEntity<String> testing(@PathVariable String username){
    logger.info("Request user data(Rest Controller) > " + username);

    Optional<User> optUser = usrRepo.getFavourites(username);

    if(optUser.isEmpty()){
      logger.info("optUser is empty");
      JsonObject haul = Json.createObjectBuilder()
          .add("ERROR", "%s user not found".formatted(username))
          .build();
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(haul.toString());
    }
    
    User user = optUser.get();

    JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
    for(Value value: user.getFavourites())
      jaBuilder.add(value.toJson());

    JsonObject haul = Json.createObjectBuilder()
        .add("value", jaBuilder.build())
        .build();

    return ResponseEntity.ok(haul.toString());
    
  }

}
