package game.units;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Grid {

	public enum COORDINATE_STATE{
		FREE,
		BALL,
		WALL;
	}
	
	public Grid(int width, int height){
		grid = new COORDINATE_STATE[width][height];
		Ellipse2D circle = new Ellipse2D.Double(0,0,0,0);
	}
	
	private COORDINATE_STATE[][] grid;
	
	
	
}
