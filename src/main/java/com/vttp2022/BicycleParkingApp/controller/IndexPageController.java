package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vttp2022.BicycleParkingApp.models.Parkings;
import com.vttp2022.BicycleParkingApp.models.Postal;
import com.vttp2022.BicycleParkingApp.models.PostalQuery;
import com.vttp2022.BicycleParkingApp.models.Query;

import org.slf4j.*;

@Controller
public class IndexPageController {
  private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);

  @GetMapping("/")
  public String showIndexPage(Model model){
    Parkings p = new Parkings();
    Query q = new Query();
    Postal pos = new Postal();
    PostalQuery pq = new PostalQuery();
    pos.setPostalQuery(pq);
    p.setQuery(q);
    model.addAttribute("postal", pos);
    model.addAttribute("parkings", p);
    return "index";
  }
  
}
