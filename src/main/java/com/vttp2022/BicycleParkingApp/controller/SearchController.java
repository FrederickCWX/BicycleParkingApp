package com.vttp2022.BicycleParkingApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;

@Controller
@RequestMapping(path = "/search")
public class SearchController {
  private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
  
  @Autowired
  User usr;

  @PostMapping
  public String showSearchPage(@RequestParam(value = "Username", required = true) String username, Model model){

    usr.setUsername(username);

    model.addAttribute("postal", new Postal());
    model.addAttribute("parkings", new Parkings());

    logger.info("Post / > " + usr.getUsername());
    return "search";
  }

  @GetMapping
  public String showSearchPage(Model model){
    logger.info("/Search > " + usr.getUsername());
    return "search";
  }
  
}
