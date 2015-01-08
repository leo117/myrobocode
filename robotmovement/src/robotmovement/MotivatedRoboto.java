package robotmovement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import robocode.AdvancedRobot;
import robocode.Event;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
//import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.util.*;

public class MotivatedRoboto extends AdvancedRobot {
	static double TURN_TANK = 24;
	static double TURN_TANK_BOUNDARY = 360;
	static double TURN_TANK_VELOCITY = 24;
	
	static double TURN_GUN = 360;
	double turnleft = TURN_TANK;
	//List path = new ArrayList();
	ArrayList<Point> path = new ArrayList<Point>();
	/**
	 * run: default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

	   setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
		  // Replace the next 4 lines with any behavior you would like
		  moveBehaviour();
		}
	}
	// The coordinates of the last scanned robot
	 int scannedX = Integer.MIN_VALUE;
	 int scannedY = Integer.MIN_VALUE;
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
	  // Calculate the angle to the scanned robot
      double angle = Math.toRadians((getHeading() + e.getBearing()) % 360); 
	  // Calculate the coordinates of the robot
	  scannedX = (int)(getX() + Math.sin(angle) * e.getDistance());
	  scannedY = (int)(getY() + Math.cos(angle) * e.getDistance());
	     
	  // Replace the next line with any behavior you would like
	  fireMethod(e);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(20);
		doNothing();
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
	  System.out.println("######Hit wall!#############");
	  // Replace the next line with any behavior you would like
	  double angle = e.getBearing();
	  if(angle > 0) {
		turnLeft(180);
  	  } 
	}

	 public void onPaint(Graphics2D g) {
		 //Draw rada trace
	     // Set the paint color to a red half transparent color
	     g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	     //new Point((int)getX(), (int)getY());
	     // Draw a line from our robot to the scanned robot
	     g.drawLine(scannedX, scannedY, (int)getX(), (int)getY());
	     // Draw a filled square on top of the scanned robot that covers it
	     g.fillRect(scannedX - 20, scannedY - 20, 40, 40);
	     
	     // Draw Roboto trace
	     g.setColor(new Color(0x00, 0xff, 0x00, 0x80));
	     if(path.size() >= 2) {
	    	for(int i = 0; i < path.size() - 1; i ++) {
	    		Point lineStart = path.get(i);
	    		Point lineEnd = path.get(i+1);
	    		g.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
	    	}
	     } 
	 }
	protected void logTrace() {
	  path.add(new Point((int)getX(), (int)getY()));
	}
	
	protected void moveBehaviour() {
		
		turnLeft(turnleft);
		ahead(TURN_TANK_VELOCITY);
		//setTurnLeft(turnleft);
		//setAhead(TURN_TANK_VELOCITY);
		logTrace();
		waitforTurn();
		turnGunRight(TURN_GUN);
		turnGunRight(TURN_GUN);
	}

	protected void waitforTurn() {
	  System.out.println("#########Turn degree to " + Double.toString(getHeading()) + "####");
	  if ((TURN_TANK_BOUNDARY - getHeading()) < TURN_TANK) {
		turnleft = -1*turnleft;
	  }
	}

	protected void fireMethod(ScannedRobotEvent e) {
		if(e.getDistance()<100) {
			fire(3);
		} else {
			fire(1);
		}
	}
}
