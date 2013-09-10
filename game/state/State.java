package game.state;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Game;
import game.units.Drawable;
import game.units.Wall;

public enum State implements Drawable{
	START_LEVEL,
	PLAY_LEVEL,
	END_LEVEL,
	GAME_OVER;

	public void start() {
		switch(this){
		case START_LEVEL:
			Animation.START_LEVEL_ANIMATION.start();
			Game.gameField.setWalls(new ArrayList<Wall>());
			Game.gameField.setBalls(Game.level.getBalls());
			break;
		case PLAY_LEVEL:
			break;
		case END_LEVEL:
			Animation.END_LEVEL_ANIMATION.start();
			break;
		case GAME_OVER:
			Animation.GAME_OVER_ANIMATION.start();
			break;
		}
	}
	
	@Override
	public void update() {
		switch(this){
		case START_LEVEL:
			if(Game.level == null){
				//game over...
				System.out.println("game over");
			}
			Animation.START_LEVEL_ANIMATION.update();
			break;
		case PLAY_LEVEL:
			Game.gameField.update();
			break;
		case END_LEVEL:
			//keep the level playing in background
			Game.gameField.update();
			//update complete animation
			Animation.END_LEVEL_ANIMATION.update();
			break;
		case GAME_OVER:
			Animation.GAME_OVER_ANIMATION.update();
			break;
		}
	}
	
	@Override
	public void draw(Graphics g){
		switch(this){
		case START_LEVEL:
			Animation.START_LEVEL_ANIMATION.draw(g);
			break;
		case PLAY_LEVEL:
			Game.gameField.draw(g);
			break;
		case END_LEVEL:
			//draw the completed level in background
			Game.gameField.draw(g);
			Animation.END_LEVEL_ANIMATION.draw(g);
			break;
		case GAME_OVER:
			Animation.GAME_OVER_ANIMATION.draw(g);
			break;
		}
	}

}
