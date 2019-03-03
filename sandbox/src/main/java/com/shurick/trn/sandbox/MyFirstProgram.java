package com.shurick.trn.sandbox;

public class MyFirstProgram {

  public static String printDouble(Double value){
    return String.format("%.2f", value);
  }

  public static void main(String args[]){
    Point A = new Point(12,33);
    Point B = new Point(534,235);

    Double distanceAB = A.getDistanceTo(B);
    System.out.println("Distance between A and B is " + printDouble(distanceAB));
  }

}
