package com.vttp2022.BicycleParkingApp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vttp2022.BicycleParkingApp.models.User;
import com.vttp2022.BicycleParkingApp.models.parking.Value;
import com.vttp2022.BicycleParkingApp.services.UserRepository;

@Controller
@RequestMapping(path = "/favourite")
public class FavouritesController {
  private static final Logger logger = LoggerFactory.getLogger(FavouritesController.class);

  @Autowired
  private UserRepository usrRepo;
  
  @Autowired
  User usr;

  @GetMapping
  public String showFavourites(Model model){

    logger.info("Show favourites html, user > " + usr.getUsername());

    Optional<User> optUser = usrRepo.getFavourites(usr.getUsername());

    String info;

    if(optUser.isEmpty()){
      info = "You have 0 saved bicycle parking location(s)";
      model.addAttribute("info", info);
      return "favourite";
    }
    
    User user = optUser.get();

    List<Value> favList = user.getFavourites();

    if(favList.size() == 0){
      info = "You have 0 saved bicycle parking location(s)";
      model.addAttribute("info", info);
      return "favourite";
    }else{
      info = "You have "+favList.size()+" saved bicycle parking location(s)";
      model.addAttribute("info", info);
      model.addAttribute("details", favList);
    }

    return "favourite";
  }
  
}
