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

import com.vttp2022.BicycleParkingApp.models.Parkings;
import com.vttp2022.BicycleParkingApp.models.Postal;
import com.vttp2022.BicycleParkingApp.models.PostalQuery;
import com.vttp2022.BicycleParkingApp.models.Query;
import com.vttp2022.BicycleParkingApp.models.Results;
import com.vttp2022.BicycleParkingApp.models.Value;
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
    //Parkings p = new Parkings();
    //Postal pos = new Postal();
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
    String info = "There are "+value.size()+" bicycle parking bay(s) within "+q.getRadius()+"km of S'pore "+pq.getPostalCode();
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

  /*
  @GetMapping
  public String parking(@RequestParam(value = "Lat", required = true) String lat, @RequestParam(value = "Lng", required = true) String lng, @RequestParam(value = "Dist", required = false) String radius, Model model){
    Parkings p = new Parkings();
    Query q = new Query();
    q.setLat(new BigDecimal(lat));
    q.setLng(new BigDecimal(lng));
    q.setRadius(Double.parseDouble(radius));
    logger.info(String.valueOf(q.getRadius()));
    logger.info("test");
    logger.info(lat+","+lng+","+radius);
    Optional<Parkings> optParking = parkingSvc.findParking(q);

    

    if(optParking.isEmpty()){
      model.addAttribute("parkings", new Parkings());
      return "BicycleParking";
    }
    logger.info("<<<<<"+q.getLat()+", "+q.getLng()+"****"+q.getRadius());

    List<Value> value = Parkings.getValue();
    //logger.info("Number of bicycle bay(s): "+String.valueOf(response.size()));
    String info = "There are "+value.size()+" bicycle parking bay(s) within "+q.getRadius()+"km of "+q.getLat()+", "+q.getLng();
    logger.info(info);
    model.addAttribute("respDetails", info);
    if(value.size() > 0){
      //TODO
      //Send data back to html page for user
      model.addAttribute("details", value);
    }

    model.addAttribute("Lat", q.getLat());
    model.addAttribute("Lng", q.getLng());
    model.addAttribute("Radius", q.getRadius());

    return "BicycleParking";
  }
  */

  
}
