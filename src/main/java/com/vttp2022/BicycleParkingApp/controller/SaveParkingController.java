package com.vttp2022.BicycleParkingApp.controller;

import java.util.LinkedList;
import java.util.List;

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

@Controller
@RequestMapping(path = "/saveParking")
public class SaveParkingController {
  private static final Logger logger = LoggerFactory.getLogger(SaveParkingController.class);

  @Autowired
  UserRepository usrRepo;
  
  @Autowired
  User usr;

  @PostMapping
  public String saveFav(@RequestBody String form, Model model){
    logger.info("Save favourites html, user > " + usr.getUsername());

    String parkingID = form.replace("id=", "");

    List<Value> vals = Parkings.getValue();

    for(Value val: vals){
      if(val.getId().equals(parkingID)){
        if(usr.getFavourites() == null){
          List<Value> fList = new LinkedList<>();
          fList.add(val);
          usr.createUser(usr.getUsername(), fList);
        }else{
          usr.addFavourite(val);
        }
      }
    }

    usrRepo.saveUser();

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

    logger.info("Parking save to favourites succesfully");

    return "favourite";

  }
}
