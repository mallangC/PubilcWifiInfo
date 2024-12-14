package com.public_wifi_info.pubilcwifiinfomation.service;

import java.util.Date;

public class WifiInfo implements Comparable<WifiInfo>{
  public double distance;
  public String mgrNo;
  public String region;
  public String name;
  public String address;
  public String addressDetail;
  public String installationFloor;
  public String installationType;
  public String installationInstitution;
  public String service;
  public String netType;
  public int installationYear;
  public String inoutDoor;
  public String connectionEnvironment;
  public double lat;
  public double lnt;
  public Date workDate;

  @Override
  public int compareTo(WifiInfo wifiInfo) {
    return (int)(this.distance*1000) - (int)(wifiInfo.distance*1000);
  }
}
