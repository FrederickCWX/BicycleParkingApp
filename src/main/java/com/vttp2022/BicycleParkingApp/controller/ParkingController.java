package com.vttp2022.BicycleParkingApp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @GetMapping
  public String parking(@RequestParam(value = "PostalCode", required = true) String postal, @RequestParam(value = "Dist", required = false) String radius, Model model){
    Query q = new Query();
    PostalQuery pq = new PostalQuery();
    pq.setPostalCode(Integer.parseInt(postal));
    pq.setReturnGeom("Y");
    pq.setGetAddrDetails("N");
    Optional<Postal> optPostal = postalSvc.findLatLong(pq);

    if(optPostal.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "BicycleParking";
    }
    List<Results> results = Postal.getResults();
    if(results.size() == 1){
      for(Results result: results){
        q.setLat(result.getLatitude());
        q.setLng(result.getLongitude());
      }
    }
    q.setRadius(Double.parseDouble(radius));
    logger.info(String.valueOf(q.getRadius()));
    logger.info(postal+","+radius);
    Optional<Parkings> optParking = parkingSvc.findParking(q);

    if(optParking.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "BicycleParking";
    }
    logger.info("<<<<<"+q.getLat()+", "+q.getLng()+"****"+q.getRadius());

    List<Value> value = Parkings.getValue();
    //logger.info("Number of bicycle bay(s): "+String.valueOf(response.size()));
    String info = "There are "+value.size()+" bicycle parking bay(s) within "+q.getRadius()+"km of Singapore "+pq.getPostalCode();
    logger.info(info);
    model.addAttribute("respDetails", info);
    if(value.size() > 0){
      model.addAttribute("details", value);
    }

    model.addAttribute("Lat", q.getLat());
    model.addAttribute("Lng", q.getLng());
    model.addAttribute("Radius", q.getRadius());

    return "BicycleParking";
  }

}
