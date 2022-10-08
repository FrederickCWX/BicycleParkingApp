package com.vttp2022.BicycleParkingApp.services;

import java.util.Optional;

//import com.vttp2022.BicycleParkingApp.models.UserOld;
import com.vttp2022.BicycleParkingApp.models.User;

public interface UserRepo {
  public int save(final User user);

  //public UserOld findById(final String userId);

  public Optional<User> findByUsername(final String username);

  public int update(final User user);

  /*
   * public int save(final UserOld user);

  public UserOld findById(final String userId);

  public UserOld findByUsername(final String username);

  public int update(final UserOld user);
   */
  
}
