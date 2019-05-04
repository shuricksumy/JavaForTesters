package com.shurick.trn.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {

  public double positionX;
  public double positionY;

  public Point(double positionX, double positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public double getDistanceTo(Point point) {
    return sqrt(pow((point.positionX - this.positionX), 2) + pow((point.positionY - this.positionY), 2));
  }

}
