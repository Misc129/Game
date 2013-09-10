package game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import game.units.Drawable;
import game.units.GameField;

public enum Animation implements Drawable{
	
	START_LEVEL_ANIMATION(3000),
	END_LEVEL_ANIMATION(3000),
	GAME_OVER_ANIMATION(3000);
	
	Animation(long duration){
		this.duration = duration;
	}

	public void start(){
		start = System.currentTimeMillis();
		end = start + duration;
	}
	
	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	private long start, end, duration;
	
	@Override
	public void update() {
		switch(this){
		case START_LEVEL_ANIMATION:
			if(System.currentTimeMillis() > end){
				//animation complete
				Game.state = State.PLAY_LEVEL;
				Game.state.start();
			}
			break;
		case END_LEVEL_ANIMATION:
			if(System.currentTimeMillis() > end){
				//animation complete
				Game.state = State.START_LEVEL;
				Game.state.start();
			}
			break;
		case GAME_OVER_ANIMATION:
			if(System.currentTimeMillis() > end){
				//for now restart the game
				Game.level = Level.ONE;
				Game.state = State.START_LEVEL;
				Game.state.start();
			}
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		switch(this){
		case START_LEVEL_ANIMATION:
			g.setColor(Color.BLACK);
			g.fillRect((int)GameField.FIELD.x, (int)GameField.FIELD.y, (int)GameField.FIELD.width, (int)GameField.FIELD.height);
			g.setColor(Color.CYAN);
			g.setFont(FONT);
			g.drawString("Level " + Game.level.getNum(), (int)GameField.MIDPOINT.x - 50, (int)GameField.MIDPOINT.y);
			break;
		case END_LEVEL_ANIMATION:
			//g.setColor(Color.BLACK);
			//g.fillRect((int)GameField.FIELD.x, (int)GameField.FIELD.y, (int)GameField.FIELD.width, (int)GameField.FIELD.height);
			g.setColor(Color.CYAN);
			g.setFont(FONT);
			g.drawString("Level Complete", (int)GameField.MIDPOINT.x - 95, (int)GameField.MIDPOINT.y);
			break;
		case GAME_OVER_ANIMATION:
			//g.setColor(Color.BLACK);
			//g.fillRect((int)GameField.FIELD.x, (int)GameField.FIELD.y, (int)GameField.FIELD.width, (int)GameField.FIELD.height);
			g.setColor(Color.CYAN);
			g.setFont(FONT);
			g.drawString("Game Over", (int)GameField.MIDPOINT.x - 95, (int)GameField.MIDPOINT.y);
			break;
		}
	}
	
	
	
}
