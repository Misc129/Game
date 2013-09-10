package game.calculations;

import java.awt.Color;

public enum MyColor {
	WHITE(Color.WHITE,"WHITE"),
	BLACK(Color.BLACK,"BLACK"),
	GREEN(Color.GREEN,"GREEN"),
	RED(Color.RED,"RED"),
	BLUE(Color.BLUE,"BLUE"),
	CYAN(Color.CYAN,"CYAN");
	
	MyColor(Color color, String name){
		_color = color;
		_name = name;
	}
	
	private Color _color;
	private String _name;
	
	public Color getColor(){
		return _color;
	}
	
	public String getName(){
		return _name;
	}
}
