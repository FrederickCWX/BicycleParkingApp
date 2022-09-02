package com.vttp2022.BicycleParkingApp.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.BicycleParkingApp.models.User;
//import com.vttp2022.BicycleParkingApp.models.UserOld;
import com.vttp2022.BicycleParkingApp.services.UserRedis;

@RestController
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public class HttpRestController {
  private static final Logger logger = LoggerFactory.getLogger(HttpRestController.class);

  @Autowired
  UserRedis redisSvc;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    logger.info("User >>>"+user.getUsername());
    int x = redisSvc.save(user);
    if(x > 0)
      user.setInsertCount(x);
    return ResponseEntity.ok(user);
  }
  
  /*
  @GetMapping(path = "/userId={userId}")
  public ResponseEntity<UserOld> getUserById(@PathVariable String userId){
    UserOld u = redisSvc.findById(userId);
    //logger.info(u.getUsername());
    if(u == null)
      logger.info("null");
    else
      logger.info(u.getUsername());
    return ResponseEntity.ok(u);
  }
  */

  @GetMapping(path = "/username={username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username){
    User u = redisSvc.findByUsername(username);
    logger.info(String.valueOf(u.getFavFound()));
    return ResponseEntity.ok(u);
  }


  @PutMapping(path = "/{username}")
  public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username){
    int uResult = redisSvc.update(user);
    if(uResult > 0)
      user.setUpdateCount(uResult);
    return ResponseEntity.ok(user);
  }

  /*
   * @Autowired
  UserRedis redisSvc;

  @PostMapping
  public ResponseEntity<UserOld> createUser(@RequestBody UserOld user){
    logger.info("User >>>"+user.getUsername());
    int x = redisSvc.save(user);
    if(x > 0)
      user.setInsertCount(x);
    return ResponseEntity.ok(user);
  }
  
  @GetMapping(path = "/userId={userId}")
  public ResponseEntity<UserOld> getUserById(@PathVariable String userId){
    UserOld u = redisSvc.findById(userId);
    //logger.info(u.getUsername());
    if(u == null)
      logger.info("null");
    else
      logger.info(u.getUsername());
    return ResponseEntity.ok(u);
  }

  @GetMapping(path = "/username={username}")
  public ResponseEntity<UserOld> getUserByUsername(@PathVariable String username){
    UserOld u = redisSvc.findByUsername(username);
    logger.info(u.getId());
    return ResponseEntity.ok(u);
  }


  @PutMapping(path = "/{userId}")
  public ResponseEntity<UserOld> updateUser(@RequestBody UserOld user, @PathVariable String userId){
    int uResult = redisSvc.update(user);
    if(uResult > 0)
      user.setUpdateCount(uResult);
    return ResponseEntity.ok(user);
  }
   */


  
   
  
}
