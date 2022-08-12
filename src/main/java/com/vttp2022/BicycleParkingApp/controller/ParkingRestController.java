package com.vttp2022.BicycleParkingApp.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.services.UserRedis;

@RestController
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public class ParkingRestController {
  private static final Logger logger = LoggerFactory.getLogger(ParkingRestController.class);

  @Autowired
  UserRedis redisSvc;

  @Autowired
  User usr;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    logger.info("User >>>"+user.getUsername());
    int x = redisSvc.save(user);
    if(x > 0)
      user.setInsertCount(x);
    return ResponseEntity.ok(user);
  }

   
  
}
