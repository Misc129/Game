package game.calculations;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D.Double{

	public Vector2D(double x, double y){
		super(x,y);
	}
	
	public double getLength(){
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2D normalize(){
		return new Vector2D(x / getLength(), y / getLength());
	}
	
	public Vector2D derive(double dx, double dy){
		return new Vector2D(x + dx, y + dy);
	}
}
