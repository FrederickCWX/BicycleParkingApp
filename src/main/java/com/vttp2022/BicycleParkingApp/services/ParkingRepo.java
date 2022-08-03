package com.vttp2022.BicycleParkingApp.services;

import com.vttp2022.BicycleParkingApp.models.parking.Value;

public interface ParkingRepo {
  public int save(final Value val);

  public Value findById(final String valId);

  public int update(final Value val);
  
}
