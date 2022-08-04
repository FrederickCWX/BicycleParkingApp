package com.vttp2022.BicycleParkingApp.services;

import com.vttp2022.BicycleParkingApp.models.User;

public interface UserRepo {
  public int save(final User user);

  public User findById(final String userId);

  //public int update(final User user);
  
}
