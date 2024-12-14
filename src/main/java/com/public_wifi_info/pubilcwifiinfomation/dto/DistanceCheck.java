package com.public_wifi_info.pubilcwifiinfomation.dto;

public class DistanceCheck {
  public double EARTH_RADIUS = 6371;

  public double getDistance(double curLat, double curLon, double targetLat, double targetLon) {
    double dLat = Math.toRadians(targetLat - curLat);
    double dLon = Math.toRadians(targetLon - curLon);

    double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(curLat))* Math.cos(Math.toRadians(targetLat))* Math.sin(dLon/2)* Math.sin(dLon/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    return this.EARTH_RADIUS * c;
  }
}
