package game.state;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import game.calculations.MyColor;
import game.calculations.Vector2D;
import game.units.Ball;


public enum Level {

	ONE(1, Balls.L1_BALLS),
	TWO(2, Balls.L2_BALLS),
	THREE(3, Balls.L3_BALLS),
	FOUR(4, Balls.L4_BALLS),
	FIVE(5, Balls.L5_BALLS);

	Level(int num, Ball... balls){
		this.num = num;
		ballList = new ArrayList<Ball>();
		for(Ball ball : balls){
			ballList.add(ball);
		}
	}

	private List<Ball> ballList;
	private int num;

	public List<Ball> getBalls(){
		return ballList;
	}

	public int getNum(){	
		return num;
	}
	
	public Level next(){
		switch(this){
		case ONE:
			return TWO;
		case TWO:
			return THREE;
		case THREE:
			return FOUR;
		case FOUR:
			return FIVE;
		case FIVE:
			return null;
		default:
			return null;
		}
	}

	public boolean isLast(){
		return Level.values()[Level.values().length - 1] == this;
	}
}

final class Balls{
	public static final Ball[] L1_BALLS = {
		new Ball(new Point2D.Double(100, 100), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 300), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 200), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 400), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 100), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 300), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 200), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 400), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 100), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 300), new Vector2D(Math.random(), Math.random()), 5, MyColor.GREEN)
	};

	public static final Ball[] L2_BALLS = {
		new Ball(new Point2D.Double(100, 100), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 200), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 300), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 200), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 400), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 100), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 200), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 300), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 200), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 400), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 100), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 300), new Vector2D(Math.random(), Math.random()), 7, MyColor.GREEN)
	};

	public static final Ball[] L3_BALLS = {
		new Ball(new Point2D.Double(100, 100), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 200), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 300), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 200), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 400), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 100), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 200), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 300), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 200), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 300), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 400), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 100), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 200), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 300), new Vector2D(Math.random(), Math.random()), 9, MyColor.GREEN)
	};

	public static final Ball[] L4_BALLS = {
		new Ball(new Point2D.Double(100, 100), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 200), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 300), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 200), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 300), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 400), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 100), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 200), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 300), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 200), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 300), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 400), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 100), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 200), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 300), new Vector2D(Math.random(), Math.random()), 11, MyColor.GREEN)
	};

	public static final Ball[] L5_BALLS = {
		new Ball(new Point2D.Double(100, 100), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 200), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		/*new Ball(new Point2D.Double(100, 300), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(100, 400), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 100), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 200), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 300), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(200, 400), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 100), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 200), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 300), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 400), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 100), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 200), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 300), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(400, 400), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 100), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(300, 200), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 300), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN),
		new Ball(new Point2D.Double(500, 400), new Vector2D(Math.random(), Math.random()), 12, MyColor.GREEN)*/
	};

}