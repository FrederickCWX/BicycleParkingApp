package com.vttp2022.BicycleParkingApp.models.postal;

public class PostalQuery {

  private Integer postalCode;
  private String returnGeom;
  private String getAddrDetails;

  public Integer getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }

  public String getReturnGeom() {
    return returnGeom;
  }
  public void setReturnGeom(String returnGeom) {
    this.returnGeom = returnGeom;
  }

  public String getGetAddrDetails() {
    return getAddrDetails;
  }
  public void setGetAddrDetails(String getAddrDetails) {
    this.getAddrDetails = getAddrDetails;
  }
  
}
