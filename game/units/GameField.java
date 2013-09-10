package game.units;

import game.Game;
import game.calculations.Calculations;
import game.calculations.Maths;
import game.calculations.MyColor;
import game.calculations.Vector2D;
import game.state.Level;
import game.state.State;
import game.units.Wall.Orientation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class GameField implements Drawable {

	public GameField(){
		walls = new ArrayList<Wall>();
		balls = new ArrayList<Ball>();
		
//		final int NUM_BALLS = 15;
//		for(int ix = 30; ix < WIDTH; ix += 30){
//			for(int iy = 30; iy < HEIGHT && balls.size() < NUM_BALLS; iy += 30){
//				balls.add(new Ball(new Point2D.Double(ix,iy), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN));
//			}
//		}
		
//		Ball ball1 = new Ball(new Point2D.Double(50,50), new Vector2D(.5,.5), MyColor.GREEN);
//		Wall wall1 = new Wall(new Rectangle2D.Double(0, 200, WIDTH, 10), Orientation.HORIZONTAL);
//		Wall wall2 = new Wall(new Rectangle2D.Double(200, 0, 10, HEIGHT), Orientation.VERTICAL);
//		_walls.add(wall1);
//		_walls.add(wall2);
//		_balls.add(ball1);
	}

	private List<Ball> balls;
	private List<Wall> walls;

	public static final double X = 0, Y = 0, WIDTH = 600, HEIGHT = 500;
	public static final Rectangle2D.Double FIELD = new Rectangle2D.Double(0, 0, WIDTH, HEIGHT);
	public static final Point2D.Double MIDPOINT = new Point2D.Double(X + (WIDTH / 2), Y + (HEIGHT / 2));
	
	public static final Line2D.Double WEST_WALL = new Line2D.Double(X, Y, X, HEIGHT);
	public static final Line2D.Double NORTH_WALL = new Line2D.Double(X, Y, WIDTH, Y);
	public static final Line2D.Double EAST_WALL = new Line2D.Double(WIDTH, Y, WIDTH, HEIGHT);
	public static final Line2D.Double SOUTH_WALL = new Line2D.Double(X, HEIGHT, WIDTH, HEIGHT);

	public List<Ball> getBalls(){
		return balls;
	}
	
	public void setBalls(List<Ball> newValue){
		balls = newValue;
	}

	public List<Wall> getWalls(){
		return walls;
	}
	
	public void setWalls(List<Wall> newValue){
		walls = newValue;
	}

	public void checkBoundaryCollision(Ball ball, Point2D.Double projectedCenter){
		if(projectedCenter.x - Ball.RADIUS <= WEST_WALL.x1){
			ball.getVelocity().x = Maths.positive(ball.getVelocity().x);
		}
		if(projectedCenter.y - Ball.RADIUS <= NORTH_WALL.y1){
			ball.getVelocity().y = Maths.positive(ball.getVelocity().y);
		}
		if(projectedCenter.x + Ball.RADIUS >= EAST_WALL.x1){
			ball.getVelocity().x = Maths.negative(ball.getVelocity().x);
		}
		if(projectedCenter.y + Ball.RADIUS >= SOUTH_WALL.y1){
			ball.getVelocity().y = Maths.negative(ball.getVelocity().y);
		}
	}

	//check if the new wall will fit according to the game field borders such that no balls are being squeezed between them
	public boolean willFit(Wall newWall){
		Rectangle2D.Double quadrant = Calculations.getQuadrant(newWall.getCenterPoint(), Game.gameField.getWalls());
		if(newWall.getOrientation() == Orientation.HORIZONTAL){
			//check north border squeeze
			for(Ball ball : balls){
				double top = NORTH_WALL.y1;
				double bottom = newWall.getNorth().y1;
				if(ball.getCenterPoint().getY() > top && ball.getCenterPoint().getY() < bottom && quadrant.contains(ball.getCenterPoint())){
					if(bottom - top < Ball.RADIUS * 2 +  Wall.HEIGHT + 5)
						return false;
				}
			}
			//check south border squeeze
			for(Ball ball : balls){
				double top = newWall.getSouth().y1;
				double bottom = SOUTH_WALL.y1;
				if(ball.getCenterPoint().getY() > top && ball.getCenterPoint().getY() < bottom && quadrant.contains(ball.getCenterPoint())){
					if(bottom - top < Ball.RADIUS * 2 +  Wall.HEIGHT + 5)
						return false;
				}
			}
		}
		if(newWall.getOrientation() == Orientation.VERTICAL){
			//check west border squeeze
			for(Ball ball : balls){
				double left = WEST_WALL.x1;
				double right = newWall.getWest().x1;
				if(ball.getCenterPoint().getX() > left && ball.getCenterPoint().getX() < right && quadrant.contains(ball.getCenterPoint())){
					if(right - left < Ball.RADIUS * 2 + Wall.WIDTH + 5)
						return false;
				}
			}
			//check east border squeeze
			for(Ball ball : balls){
				double left = newWall.getEast().x1;
				double right = EAST_WALL.x1;
				if(ball.getCenterPoint().getX() > left && ball.getCenterPoint().getX() < right && quadrant.contains(ball.getCenterPoint())){
					if(right - left < Ball.RADIUS * 2 +  Wall.WIDTH + 5)
						return false;
				}
			}
		}
		return true;
	}
	
	public boolean isAnyWallGrowing(){
		for(Wall wall : walls){
			if(wall.isGrowing())
				return true;
		}
		return false;
	}

	@Override
	public void update() {
		//assume game is over only if there are walls present and the game is being played
		boolean gameOver = walls.size() > 0 && Game.state == State.PLAY_LEVEL;
		for(Ball ball : balls){
			ball.update();
			if(!ball.isAlone())
				gameOver = false;
		}
		if(gameOver && !isAnyWallGrowing()){
			if(Game.level.isLast()){
				Game.level = null;
				Game.state = State.GAME_OVER;
				Game.state.start();
				return;
			}
			Game.level = Game.level.next();
			Game.state = State.END_LEVEL;
			Game.state.start();
		}
		for(Wall wall : walls){
			wall.update();
		}
	}
	
	private static final Font FONT = new Font(Font.SANS_SERIF, -1, 12);
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect((int)X, (int)Y, (int)WIDTH, (int)HEIGHT);
		int i = 0,j = 0,y = 20;
		g.setFont(FONT);
		for(Ball ball : balls){
			ball.draw(g);
//			g.drawString("ball" + i + " cx:" + (int)ball.getCenterPoint().getX() + 
//					" cy:" + (int)ball.getCenterPoint().getY() + 
//					" vx:" + Maths.round(ball.getVelocity().x, 2, 0) +
//					" vy:" + Maths.round(ball.getVelocity().y, 2, 0) +
//					" isAlone:" + ball.isAlone(), (int)(WIDTH + 10), y);
			g.drawString("ball" + i + " quadX:" + (int)ball.getQuadrant().x + 
					" quadY:" + (int)ball.getQuadrant().y + 
					" quadW:" + (int)ball.getQuadrant().width +
					" quadH:" + (int)ball.getQuadrant().height +
					" isAlone:" + ball.isAlone(), (int)(WIDTH + 10), y);
			y += 15;i++;
		}
		for(Wall wall : walls){
			wall.draw(g);
			g.drawString("boundary" + j + ":" + 
					" x:" + (int)wall.getShape().x + 
					" y:" + (int)wall.getShape()
					.y +
					" width:" + (int)wall.getShape().getWidth() + 
					" height:" + (int)wall.getShape().getHeight(), (int)(WIDTH + 10), y);
			y += 15;j++;
		}
	}

}
