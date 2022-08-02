package com.vttp2022.BicycleParkingApp.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.vttp2022.BicycleParkingApp.models.Parking;
import com.vttp2022.BicycleParkingApp.models.Parkings;
import com.vttp2022.BicycleParkingApp.models.Query;
import com.vttp2022.BicycleParkingApp.models.Value;
import com.vttp2022.BicycleParkingApp.services.ParkingAPIService;

@Controller
@RequestMapping(path="/BicycleParking")
public class ParkingController {
  private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

  @Autowired
  private ParkingAPIService parkingSvc;

  @GetMapping
  public String parkings(@RequestParam(required = true) String lat, @RequestParam(required = true) String lng, @RequestParam(required = false) String radius, Model model){
    Query q = new Query();
    q.setLat(new BigDecimal(lat));
    q.setLng(new BigDecimal(lng));
    if(radius != null){
      q.setRadius(Double.parseDouble(radius));
    }else{
      //TODO
      //change back default to 0.5
      q.setRadius(0.16);
    }
    Optional<Parkings> optParking = parkingSvc.findParking(q);

    

    if(optParking.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "BicycleParking";
    }
    logger.info("<<<<<"+q.getLat()+", "+q.getLng()+"****"+q.getRadius());

    List<Value> response = Parkings.getValue();
    //logger.info("Number of bicycle bay(s): "+String.valueOf(response.size()));
    //String info = "There are "+response.size()+" bicycle parking bay(s) within "+q.getRadius()+"km of "+q.getLat()+", "+q.getLng();
    //logger.info(info);
    if(response.size() > 0){
      //TODO
      //Send data back to html page for user
    }

    model.addAttribute("Lat", q.getLat());
    model.addAttribute("Lng", q.getLng());
    model.addAttribute("Radius", q.getRadius());

    return "BicycleParking";
  }


  
}
