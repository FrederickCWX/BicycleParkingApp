package com.vttp2022.BicycleParkingApp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Query;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.Results;
import com.vttp2022.BicycleParkingApp.services.ParkingAPIService;
import com.vttp2022.BicycleParkingApp.services.PostalAPIService;
import com.vttp2022.BicycleParkingApp.utilities.SortByDistance;

@Controller
@RequestMapping(path = "/result")
public class ResultController {
  private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

  @Autowired
  private ParkingAPIService parkingSvc;

  @Autowired
  private PostalAPIService postalSvc;
  
  @Autowired
  User usr;

  @GetMapping
  public String searchParking(@RequestParam(value = "PostalCode", required = true) String postal, @RequestParam(value = "Dist", required = false) String radius, Model model){

    logger.info("Search parking html, user > " + usr.getUsername());
    Query q = new Query();
    Optional<Postal> optPostal = postalSvc.getPostalDetails(Integer.parseInt(postal));

    if(Postal.getFound() == 0){
      String info = "Postal code invalid, please key in a valid postal code";
      model.addAttribute("respDetails", info);
      model.addAttribute("parkings", new Parkings());
      return "result";
    }

    List<Results> results = Postal.getResults();
    if(results.size() >= 1){
      q.setLat(results.get(0).getLatitude());
      q.setLng(results.get(0).getLongitude());
    }
    q.setRadius(Double.parseDouble(radius));
    Optional<Parkings> optParking = parkingSvc.findParking(q);

    if(optParking.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "error";
    }

    Collections.sort(Parkings.getValue(), new SortByDistance());
    List<Value> val = Parkings.getValue();

    StringBuilder sb = new StringBuilder();
    sb.append("There are ");
    sb.append(val.size());
    sb.append(" bicycle parking bay(s) within ");
    sb.append((int) (q.getRadius()*1000));
    sb.append(" metres of ");
    sb.append(results.get(0).getAddress());
    sb.append(", Singapore ");
    sb.append(postal);
    Parkings.setInfo(sb.toString());

    model.addAttribute("respDetails", Parkings.getInfo());
    if(val.size() > 0){
      model.addAttribute("details", val);
    }

    model.addAttribute("Radius", q.getRadius());

    logger.info("Search successful, results > " + Parkings.getInfo());

    return "result";
  }
  
}
