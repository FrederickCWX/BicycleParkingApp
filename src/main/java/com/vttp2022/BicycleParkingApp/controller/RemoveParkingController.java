package com.vttp2022.BicycleParkingApp.controller;

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
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.services.UserRepository;

@Controller
@RequestMapping(path = "/removeParking")
public class RemoveParkingController {
  private static final Logger logger = LoggerFactory.getLogger(RemoveParkingController.class);

  @Autowired
  UserRepository uRepo;

  @Autowired
  User usr;

  @PostMapping
  public String rmvFav(@RequestBody String form, Model model){
    logger.info("/removeParking > " + usr.getUsername());

    String parkingID = form.replace("id=", "");
    logger.info("Parking ID to rmv > " + parkingID);

    List<Value> favList = usr.getFavourites();

    for(int x=0; x<favList.size(); x++){
      if(favList.get(x).getId().equals(parkingID))
        usr.rmvFavourite(x);
    }

    uRepo.saveFav();

    List<Value> newFavList = usr.getFavourites();

    if(newFavList.size() == 0){
      String info = "You have 0 saved bicycle parking location(s)";
      model.addAttribute("info", info);
      return "favourite";
    }else{
      String info = "You have "+newFavList.size()+" saved bicycle parking location(s)";
      model.addAttribute("info", info);
      model.addAttribute("details", favList);
    }

    return "favourite";

  }
  
}
