package com.vttp2022.BicycleParkingApp.services;

import com.vttp2022.BicycleParkingApp.models.parking.Parkings;

public interface ParkingRepo {
  public int save(final Parkings prk);

  public Parkings findById(final String prkId);

  public int update(final Parkings prk);
  
}
