package game.calculations;

import game.Game;
import game.units.Ball;
import game.units.GameField;
import game.units.Wall;
import game.units.Wall.Orientation;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.math.BigDecimal;
import java.util.List;

public class Maths{

	public static double negative(double a){
		return Math.min(a, -a);
	}

	public static int negative(int a){
		return Math.min(a, -a);
	}

	public static double positive(double a){
		return Math.max(a, -a);
	}

	public static int positive(int a){
		return Math.max(a, -a);
	}

	public static double round(double unrounded, int precision, int roundingMode){
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, roundingMode);
		return rounded.doubleValue();
	}

}
