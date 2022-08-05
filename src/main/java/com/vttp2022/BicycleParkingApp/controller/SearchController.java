
package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Query;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.PostalQuery;

import org.slf4j.*;

@Controller
@RequestMapping("/BikeParking")
public class SearchController {
  private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

  @GetMapping
  public String showSearchPage(Model model){
    Parkings p = new Parkings();
    Query q = new Query();
    Postal pos = new Postal();
    PostalQuery pq = new PostalQuery();
    pos.setPostalQuery(pq);
    p.setQuery(q);
    model.addAttribute("postal", pos);
    model.addAttribute("parkings", p);
    return "BicycleParking";
  }
  
}
