package game.calculations;

import game.Game;
import game.units.Ball;
import game.units.GameField;
import game.units.Wall;
import game.units.Wall.Orientation;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Calculations {
	
	//probes the walls from the given point to find the surrounding rectangle quadrant
		public static Rectangle2D.Double getQuadrant(Point2D.Double point, List<Wall> walls){
			Rectangle2D.Double result = null;
			//boundary variables for the resulting rectangle
			double minX = 0, minY = 0, maxX = GameField.WIDTH, maxY = GameField.HEIGHT;
			//probe walls to find the nearest to the argument point for each direction
			for(Wall wall : walls){
//				if(wall.isGrowing())
//					continue;
				switch(wall.getOrientation()){
				case HORIZONTAL:
					//possible quadrant north boundary
					if(wall.getShape().getMaxY() > minY && wall.getShape().getMaxY() < point.y
							&& point.x >= wall.getShape().getMinX() && point.x <= wall.getShape().getMaxX())
						minY = wall.getShape().getMaxY();
					//possible quadrant south boundary
					if(wall.getShape().getMinY() < maxY && wall.getShape().getMinY() > point.y
							&& point.x >= wall.getShape().getMinX() && point.x <= wall.getShape().getMaxX())
						maxY = wall.getShape().getMinY();
					break;
				case VERTICAL:
					//possible quadrant west boundary
					if(wall.getShape().getMaxX() > minX && wall.getShape().getMaxX() < point.x
							&& point.y >= wall.getShape().getMinY() && point.y <= wall.getShape().getMaxY())
						minX = wall.getShape().getMaxX();
					//possible quadrant east boundary
					if(wall.getShape().getMinX() < maxX && wall.getShape().getMinX() > point.x
							&& point.y >= wall.getShape().getMinY() && point.y <= wall.getShape().getMaxY())
						maxX = wall.getShape().getMinX();
					break;
				}
			}
			result = new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
			return result;
		}
	
	public static Ball probe(Ball ball, int boxSize, int shiftIncrement, Rectangle2D.Double acceptQuadrant){
		Cardinal direction = Cardinal.NORTH;
		Ball probe = new Ball(new Point2D.Double(ball.getCenterPoint().getX(), ball.getCenterPoint().getY()), ball.getVelocity(), ball.getSpeed(), MyColor.BLACK);
		//displace the invalid ball outside the game field so the probe can find a closer valid position
		ball.setCenterX(-Ball.RADIUS);
		ball.setCenterY(-Ball.RADIUS);
		for(int i = 1; i <= boxSize; i++){
			for(int j = 0; j < 2; j++){
				for(int k = 0; k < i; k++){
					switch(direction){
					case WEST:
						probe.move(-shiftIncrement, 0);
						break;
					case NORTH:
						probe.move(0, -shiftIncrement);
						break;
					case EAST:
						probe.move(shiftIncrement, 0);
						break;
					case SOUTH:
						probe.move(0, shiftIncrement);
						break;
					}
				}
				boolean b1 = acceptQuadrant.contains(probe.getCenterPoint().getX(), probe.getCenterPoint().getY()); 
				boolean b2 = probe.isPositionValid();
				if(b1 && b2){
					return probe;
				}
				direction = direction.nextClockwise();
			}
		}
		return null;
	}
	
}
