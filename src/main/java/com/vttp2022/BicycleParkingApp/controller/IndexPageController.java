package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vttp2022.BicycleParkingApp.models.Parkings;
import com.vttp2022.BicycleParkingApp.models.Query;

@Controller
public class IndexPageController {

  @GetMapping("/")
  public String showIndexPage(Model model){
    Parkings p = new Parkings();
    Query q = new Query();
    p.setQuery(q);
    model.addAttribute("parkings", p);
    return "index";
  }
  
}
