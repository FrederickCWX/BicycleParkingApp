package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vttp2022.BicycleParkingApp.models.User;

import org.slf4j.*;

@Controller
public class HttpController {
  private static final Logger logger = LoggerFactory.getLogger(HttpController.class);
  
  @Autowired
  User usr;

  @GetMapping("/")
  public String loginPage(Model model){
    usr = new User();
    model.addAttribute("user", usr);
    return "index";
  }

}
