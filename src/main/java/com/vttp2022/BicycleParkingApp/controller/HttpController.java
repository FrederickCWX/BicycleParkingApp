package com.vttp2022.BicycleParkingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.BicycleParkingApp.models.User;
//import com.vttp2022.BicycleParkingApp.models.UserOld;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Query;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.models.postal.Postal;
import com.vttp2022.BicycleParkingApp.models.postal.PostalQuery;
import com.vttp2022.BicycleParkingApp.models.postal.Results;
import com.vttp2022.BicycleParkingApp.services.ParkingAPIService;
import com.vttp2022.BicycleParkingApp.services.PostalAPIService;
import com.vttp2022.BicycleParkingApp.services.UserRedis;
import com.vttp2022.BicycleParkingApp.utilities.SortByDistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;

@Controller
public class HttpController {
  private static final Logger logger = LoggerFactory.getLogger(HttpController.class);

  @Autowired
  UserRedis redisSvc;

  @Autowired
  private ParkingAPIService parkingSvc;

  @Autowired
  private PostalAPIService postalSvc;

  /* 
  @Autowired
  User usr;
  */

  @GetMapping("/")
  public String loginPage(Model model){
    //UserOld user = new UserOld();
    User user = new User();
    model.addAttribute("user", user);
    return "index";
  }
  
  @PostMapping("/search")
  public String showSearchPage(@RequestParam(value = "Username", required = true) String username, Model model){
    //User u = new User(user.getUsername());
    /*
    usr.setUsername(user.getUsername());
    logger.info("index get username");
    logger.info(usr.getUsername());
    //redisSvc.save(user);
    logger.info(usr.getId());
    */
    /* 
    UserOld user = new UserOld(username);
    logger.info(username);
    //redisSvc.save(user);
    logger.info(user.getId());
    */
    User user = new User(username);
    //redisSvc.save(user);
    logger.info(username);

    Parkings p = new Parkings();
    Query q = new Query();
    Postal pos = new Postal();
    PostalQuery pq = new PostalQuery();
    pos.setPostalQuery(pq);
    p.setQuery(q);
    //model.addAttribute("user", usr);
    model.addAttribute("postal", pos);
    model.addAttribute("parkings", p);
    return "search";
  }

  @GetMapping("/Search")
  public String showSearchPage(Model model){
    return "search";
  }

  @GetMapping("/search")
  public String searchParking(@RequestParam(value = "PostalCode", required = true) String postal, @RequestParam(value = "Dist", required = false) String radius, Model model/*, @ModelAttribute User user*/){

    Query q = new Query();
    PostalQuery pq = new PostalQuery();
    pq.setPostalCode(Integer.parseInt(postal));
    pq.setReturnGeom("Y");
    pq.setGetAddrDetails("Y");
    Optional<Postal> optPostal = postalSvc.getPostalDetails(pq);

    if(Postal.getFound() == 0){
      String info = "Postal code invalid, please key in a valid postal code";
      //model.addAttribute("username", usr.getUsername());
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
      return "result";
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
    //String info = sb.toString();
    Parkings.setInfo(sb.toString());

    logger.info(Parkings.getInfo());

    //model.addAttribute("username", usr.getUsername());
    model.addAttribute("respDetails", Parkings.getInfo());
    if(val.size() > 0){
      model.addAttribute("details", val);
    }

    model.addAttribute("Radius", q.getRadius());

    return "result";
  }

  @GetMapping("/result/add/{detailId}")
  public String saveParking(@ModelAttribute Value value, Model model, @PathVariable String detailId){
    logger.info("Add to favourite: "+detailId);

    List<Value> val = Parkings.getValue();
    List<Value> test = User.getFavourites();

    for(Value individual: val){
      if(individual.getId().equals(detailId)){
        boolean found = false;
        while(found == false){
          for(int i=0; i<User.getFavFound(); i++){
            if(test.get(i).getDescription().equals(individual.getDescription())){
              logger.info("exist in fav");
              found = true;
              break;
            }
          }
          break;
        }
        if(found == false){
          User.addFavourite(individual);
        }
        logger.info(String.valueOf(User.getFavFound()));
        for(int x=0; x<User.getFavFound(); x++){
          logger.info(test.get(x).getDescription());
        }
      }
    }
    
    //User.setData(test);
    logger.info(User.getFavourites().get(0).getDescription());
    User u = new User(User.getUsername(), User.getFavourites());
    //redisSvc.update(u);
    

    model.addAttribute("respDetails", Parkings.getInfo());
    if(val.size() > 0){
      model.addAttribute("details", val);
    }
    return "result";
  }





  @GetMapping("/favourite")
  public String showFavourites(Model model){
    return "favourite";
  }

  
  
}
