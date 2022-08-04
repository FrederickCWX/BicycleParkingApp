package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Query;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.PostalQuery;
import com.vttp2022.BicycleParkingApp.services.UserRedis;

import org.slf4j.*;

@Controller
public class IndexController {
  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  UserRedis redisSvc;

  @GetMapping("/")
  public String login(Model model){
    //User user = new User();
    model.addAttribute("user", new User());
    return "index";
  }
  
  @PostMapping("/BicycleParking")
  public String showSearchPage(@ModelAttribute User user, Model model){
    User u = new User(user.getUsername());
    logger.info("index get username");
    logger.info(u.getUsername());
    redisSvc.save(user);
    logger.info(u.getId());

    Parkings p = new Parkings();
    Query q = new Query();
    Postal pos = new Postal();
    PostalQuery pq = new PostalQuery();
    pos.setPostalQuery(pq);
    p.setQuery(q);
    model.addAttribute("user", u);
    model.addAttribute("postal", pos);
    model.addAttribute("parkings", p);
    return "BicycleParking";
  }
  
}
