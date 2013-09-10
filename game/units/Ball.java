package game.units;

import game.Game;
import game.calculations.Calculations;
import game.calculations.Cardinal;
import game.calculations.Maths;
import game.calculations.MyColor;
import game.calculations.Vector2D;
import game.units.Wall.Orientation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.List;

public class Ball implements Cloneable, Drawable{

	public Ball(Point2D.Double center, Vector2D velocity, double speed, MyColor color){
		this.center = center;
		projectedCenter = new Point2D.Double();
		projectedCenter.setLocation(center);
		west = new Point2D.Double(center.getX() - RADIUS, center.getY());
		north = new Point2D.Double(center.getX(), center.getY() - RADIUS);
		east = new Point2D.Double(center.getX() + RADIUS, center.getY());
		south = new Point2D.Double(center.getX(), center.getY() + RADIUS);
		this.speed = speed;
		this.color = color;
		this.velocity = velocity.normalize();
	}

	public static final int RADIUS = 10;
	private Point2D.Double center, projectedCenter, west, north, east, south;
	private Rectangle2D.Double quadrant = GameField.FIELD;
	private boolean isAlone = false; //indicates if ball is alone inside it's quadrant
	private MyColor color;
	private double speed;
	private Vector2D velocity;
	
	public Point2D.Double getCenterPoint(){
		return center;
	}

	public Point2D.Double getWestPoint(){
		return west;
	}

	public Point2D.Double getNorthPoint(){
		return north;
	}

	public Point2D.Double getEastPoint(){
		return east;
	}

	public Point2D.Double getSouthPoint(){
		return south;
	}

	public MyColor getColor(){
		return color;
	}

	public void setColor(MyColor newValue){
		this.color = newValue;
	}

	public Vector2D getVelocity(){
		return velocity;
	}

	public void setVelocity(double x, double y){
		velocity.x = x;
		velocity.y = y;
	}
	
	public void setVelocity(Vector2D newValue){
		velocity = newValue;
	}

	public double getSpeed(){
		return speed;
	}
	
	public Rectangle2D.Double getQuadrant(){
		return quadrant;
	}
	
	public boolean isAlone(){
		return isAlone;
	}

	public void move(double dx, double dy){
		center.setLocation(center.getX() + dx, center.getY() + dy);
		west.setLocation(west.getX() + dx, west.getY() + dy);
		north.setLocation(north.getX() + dx, north.getY() + dy);
		east.setLocation(east.getX() + dx, east.getY() + dy);
		south.setLocation(south.getX() + dx, south.getY() + dy);
	}

	public void setCenterX(double nx){
		center.setLocation(nx , center.getY());
		west.setLocation(nx - RADIUS, west.y);
		north.setLocation(nx, north.y);
		east.setLocation(nx + RADIUS, east.y);
		south.setLocation(nx, south.y);
	}

	public void setCenterY(double ny){
		center.setLocation(center.x, ny);
		west.setLocation(west.x, ny);
		north.setLocation(north.x, ny - RADIUS);
		east.setLocation(east.x, ny);
		south.setLocation(south.x, ny + RADIUS);
	}
	
	public void setCenterPoint(Point2D.Double newValue){
		center.setLocation(newValue);
		west.setLocation(west.x - RADIUS, west.y);
		north.setLocation(north.x, north.y - RADIUS);
		east.setLocation(east.x + RADIUS, east.y);
		south.setLocation(south.x, south.y + RADIUS);
	}

	public void bounce(Ball other){
		double tempX = velocity.x;
		velocity.x = other.getVelocity().x;
		other.getVelocity().x = tempX;
		double tempY = velocity.y;
		velocity.y = other.getVelocity().y;
		other.getVelocity().y = tempY;
	}

	public boolean checkCollision(Ball other){
		if(other == this)
			return false;
		boolean result = false;
		if(other.center.distance(projectedCenter) < RADIUS * 2){
			bounce(other);
			result = true;
		}
		return result;
	}

	private void escape(){
		//probe around current position for a new valid position that is inside the current quadrant
		Ball result = Calculations.probe(this, (int)Math.max(GameField.HEIGHT, GameField.WIDTH), RADIUS, quadrant);
		//if result ball is null, no valid position was found in the current quadrant
		//probe again, this time accepting any valid position within the game field, this should never happen
		if(result == null)
			result = Calculations.probe(this, (int)Math.max(GameField.HEIGHT, GameField.WIDTH), 
					RADIUS, new Rectangle2D.Double(0, 0, GameField.WIDTH, GameField.HEIGHT));
		if(result == null)
			return;
		setCenterX(result.getCenterPoint().getX());
		setCenterY(result.getCenterPoint().getY());
	}

	//returns true if the ball is in a valid position
	public boolean isPositionValid(){
		//check overlapping with walls
		for(Wall wall : Game.gameField.getWalls()){
			if(wall.overlaps(center))
				return false;
		}
		//check overlapping with other balls
		for(Ball ball : Game.gameField.getBalls()){
			if(ball == this)
				continue;
			if(ball.getCenterPoint().distance(center) < RADIUS * 2)
				return false;
		}
		return GameField.FIELD.contains(center);
	}
	
	@Override
	public void update(){
		//invalid moves are reversed, so an invalid position at this point is an extreme condition
		if(!isPositionValid())
			escape();
		double dx = velocity.x * speed;
		double dy = velocity.y * speed;
		projectedCenter.setLocation(center.x + dx, center.y + dy);
		//check other ball collisions
		boolean aloneFlag = true;
		for(Ball other : Game.gameField.getBalls()){
			if(other == this)
				continue;
			checkCollision(other);
			if(quadrant.equals(other.getQuadrant())){
				aloneFlag = false;
			}
		}
		isAlone = aloneFlag;
		//check wall collisions
		for(Wall wall : Game.gameField.getWalls()){
			wall.checkCollision(this, projectedCenter);
		}
		//check boundary collisions
		Game.gameField.checkBoundaryCollision(this, projectedCenter);
		//collisions may have changed the velocity vector
		dx = velocity.x * speed;
		dy = velocity.y * speed;
		move(dx, dy);
		//check if the move is valid, if not move back
		if(!isPositionValid()){
			move(-dx, -dy);
		}
		quadrant = Calculations.getQuadrant(center, Game.gameField.getWalls());
	}

	@Override
	public void draw(Graphics g){
		g.setColor(color.getColor());
		g.fillOval((int)west.x, (int)north.y, (int) RADIUS * 2, (int) RADIUS * 2);
		//g.setColor(Color.white);
		//g.drawString("" + Game.gameField.getBalls().indexOf(this), (int)center.x, (int)center.y);
	}
}
