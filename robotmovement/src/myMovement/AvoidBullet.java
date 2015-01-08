package myMovement;

import robocode.Robot;

public class AvoidBullet {

  private Robot robot;
  private double searchDistance = 100;
  public AvoidBullet(Robot robot) {
	  this.robot = robot;
  }
  
  public AvoidBullet(Robot robot, double searchDistance) {
	  this.robot = robot;
	  this.searchDistance = searchDistance;
  }
}
