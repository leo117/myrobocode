package myUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class LogTrace {
  protected String name;
  protected ArrayList<Point> path; 
  
  public LogTrace(String name) {
	this.name = name;
	path = new ArrayList<Point>();
  }
  public void addLocation(Point p) {
    if(p != null) {
      path.add(p);
    } else {
      
    }
  }
  
  public String getNmae() {
	return name;
  }
  
  public Point getLocationAt(int index) {
	if (index < path.size()) {
	  return path.get(index);
	} else {
	  return null;
	}
  }
  public int getSize() {
	return path.size();
  }
 
  public void drawRobotoTrace(Graphics2D g) {
	// Draw Roboto trace
	if(path.size() >= 2) {
	  for(int i = 0; i < path.size() - 1; i ++) {
  		Point lineStart = path.get(i);
  		Point lineEnd = path.get(i+1);
		g.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
	  }
    } 
  }
}
