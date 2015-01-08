package robotmovement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import myUtil.LogTrace;
import robocode.AdvancedRobot;
import robocode.Event;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Rules;
//import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

import java.util.*;

public class TestAdvancedRoboto extends AdvancedRobot {
	static double TURN_TANK = 10;
	static double TURN_TANK_BOUNDARY = 360;
	static double TANK_VELOCITY = 30;
	
	static double TURN_GUN = 360;
	double turnRight = TURN_TANK;
	boolean movingForward;
	private LogTrace robotoPath = new LogTrace(this.getClass().getName());
	/**
	 * run: default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

	   setColors(Color.yellow,Color.blue,Color.green); // body,gun,radar
        
		// Robot main loop
		while(true) {
		  // Replace the next 4 lines with any behavior you would like
		  logTrace();
		  setAhead(TANK_VELOCITY);
		  setTurnRight(turnRight);
		  checkForTurn();
		  execute();
		}
	}
	
	 public void onPaint(Graphics2D g) {
	   // Draw Roboto trace
	   g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	   robotoPath.drawRobotoTrace(g);    
	 }

	protected void logTrace() {
	  robotoPath.addLocation(new Point((int)getX(), (int)getY()));
	}
	
	/**
	 * onHitWall:  Handle collision with wall.
	 */
	public void onHitWall(HitWallEvent e) {
	  //System.out.println("#####Hit wall at angle " + Double.toString(e.getBearing()) + "#################");
	  double angle = e.getBearing();
	  if(angle < 0) {
		back(30);
	    turnRight(90);
	  } else {
		ahead(30);
		turnRight(-90);  
	  }
	  // Bounce off!
	  //reverseDirection();
	}
	
	protected void checkForTurn() {
	  if ((TURN_TANK_BOUNDARY - getHeading()) < (Rules.MAX_TURN_RATE)) {
		turnRight = -1*turnRight;
		turnRight(turnRight);
	  }
	}

	/**
	 * reverseDirection:  Switch from ahead to back & vice versa
	 */
	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fireMethod(e);
	}

	protected void fireMethod(ScannedRobotEvent e) {
	  if(e.getDistance()<100) {
		fire(3);
      } else {
		fire(1);
	  }
	}
	/**
	 * onHitRobot:  Back up!
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
}
