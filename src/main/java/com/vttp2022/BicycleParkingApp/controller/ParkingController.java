/* 
package com.vttp2022.BicycleParkingApp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Query;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.PostalQuery;
import com.vttp2022.BicycleParkingApp.models.postal.Results;
import com.vttp2022.BicycleParkingApp.services.ParkingAPIService;
import com.vttp2022.BicycleParkingApp.services.PostalAPIService;

@Controller
@RequestMapping(path="/BicycleParking")
public class ParkingController {
  private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

  @Autowired
  private ParkingAPIService parkingSvc;

  @Autowired
  private PostalAPIService postalSvc;

  @Autowired
  User usr;

  @GetMapping
  public String parking(@RequestParam(value = "PostalCode", required = true) String postal, @RequestParam(value = "Dist", required = false) String radius, Model model/*, @ModelAttribute User user){

    //User user = new User(usr.getUsername(), usr.getId());

    Query q = new Query();
    PostalQuery pq = new PostalQuery();
    pq.setPostalCode(Integer.parseInt(postal));
    pq.setReturnGeom("Y");
    pq.setGetAddrDetails("N");
    Optional<Postal> optPostal = postalSvc.findLatLong(pq);

    if(optPostal.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "BicycleParkingResults";
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
      return "BicycleParkingResults";
    }
    logger.info("<<<<<"+q.getLat()+", "+q.getLng()+"****"+q.getRadius());

    List<Value> val = Parkings.getValue();
    //logger.info("Number of bicycle bay(s): "+String.valueOf(response.size()));

    String info = "There are "+val.size()+" bicycle parking bay(s) within "+((int) (q.getRadius()*1000))+" metres of Singapore "+pq.getPostalCode();
    logger.info(info);
    logger.info("parking get username");
    logger.info(usr.getUsername());
    logger.info(usr.getId());
    model.addAttribute("username", usr.getUsername());
    model.addAttribute("respDetails", info);
    if(val.size() > 0){
      model.addAttribute("details", val);
    }

    model.addAttribute("Radius", q.getRadius());

    return "BicycleParkingResults";
  }

  /*
  @GetMapping("/add/{valueId}")
  public String addFavourite(/*@ModelAttribute User user, @ModelAttribute Value value, Model model, @PathVariable String valueId, @RequestParam String username){

    logger.info("add favourite location : " + valueId);
    logger.info("add favourite location : " + username);

    List<Value> val = Parkings.getValue();
    //logger.info("Number of bicycle bay(s): "+String.valueOf(response.size()));

    //String info = "There are "+val.size()+" bicycle parking bay(s) within "+((int) (q.getRadius()*1000))+" metres of Singapore "+pq.getPostalCode();
    //logger.info(info);
    logger.info("parking get username");
    logger.info(usr.getUsername());
    logger.info(usr.getId());
    model.addAttribute("username", usr.getUsername());
    //model.addAttribute("respDetails", info);
    if(val.size() > 0){
      model.addAttribute("details", val);
    }

    //model.addAttribute("Radius", q.getRadius());
    return "BicycleParkingResults";
  }
  
}
*/