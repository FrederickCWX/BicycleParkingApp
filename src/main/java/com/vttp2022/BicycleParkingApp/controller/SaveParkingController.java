package com.vttp2022.BicycleParkingApp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Parkings;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.services.UserRepository;

import jakarta.json.JsonObject;

@Controller
@RequestMapping(path = "/saveParking")
public class SaveParkingController {
  private static final Logger logger = LoggerFactory.getLogger(SaveParkingController.class);

  @Autowired
  UserRepository uRepo;
  
  @Autowired
  User usr;

  @PostMapping
  public String saveFav(@RequestBody String form, Model model){
    logger.info("/saveParking > " + usr.getUsername());

    String parkingID = form.replace("id=", "");
    logger.info("Parking ID to save > " + parkingID);

    List<Value> vals = Parkings.getValue();

    for(Value val: vals){
      if(val.getId().equals(parkingID)){
        logger.info("Parking found, description > " + val.getDescription());
        //usr.addFavourite(val);
        if(usr.getFavourites() == null){
          logger.info("User favourites is empty");
          List<Value> fList = new LinkedList<>();
          fList.add(val);
          usr.createUser(usr.getUsername(), fList);
        }else{
          logger.info("User favourites size > " + usr.getFavourites().size());
          usr.addFavourite(val);
        }
      }
    }

    uRepo.saveFav();

    List<Value> favList = usr.getFavourites();

    if(favList.size() == 0){
      String info = "You have 0 saved bicycle parking location(s)";
      model.addAttribute("info", info);
      return "favourite";
    }else{
      String info = "You have "+favList.size()+" saved bicycle parking location(s)";
      model.addAttribute("info", info);
      model.addAttribute("details", favList);
    }


    return "favourite";

  }

  /*
  @PostMapping
  public String saveFav(@RequestBody String form, Model model){
    logger.info("/saveParking > " + usr.getUsername());

    String parkingID = form.replace("id=", "");
    logger.info(parkingID);

    List<Value> vals = Parkings.getValue();

    JsonObject joToSave;

    logger.info("TEST");

    //User user;

    Value toSave = new Value();

    logger.info("TEST 2");

    for(Value val: vals){
      if(val.getId().equals(parkingID)){
        logger.info("Value to save found");
        toSave = val;
      }
      joToSave = toSave.toJson();
    }

    
    logger.info("/saveParking location description > " + toSave.getDescription());

    //User user;

    Optional<User> optUser = uRepo.getFavourites(usr.getUsername());

    if(!optUser.isEmpty())
      usr = optUser.get();
    else
      usr = User.createUser(usr.getUsername());

    usr.addFavourite(toSave);

    uRepo.saveFav();
    
    User user = optUser.get();

    List<Value> favList = user.getFavourites();

    if(favList.size() == 0){
      String info = "You have 0 saved bicycle parking location(s)";
      model.addAttribute("info", info);
      return "favourite";
    }else{
      String info = "You have "+favList.size()+" saved bicycle parking location(s)";
      model.addAttribute("info", info);
      model.addAttribute("details", favList);
    }


    return "favourite";

  }
  */
  
}
