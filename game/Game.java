package game;
import game.calculations.Cardinal;
import game.state.Level;
import game.state.State;
import game.units.GameField;
import game.units.Wall;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.net.URL;
import java.util.ArrayList;

/* test commit
 * TODO
 *	- walls created before other walls finish expanding causes issues
 *		+ possible solution is to have fields in Wall for projected maxX, maxY, minX, minY
 *			based on the quadrant and the time of creation
 *	- balls moving at fast speeds in small areas will stop if they run out of space
 */

public class Game extends Applet implements Runnable, KeyListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private static final int FRAME_INTERVAL = 34;//30 fps
	
	public static GameField gameField;
	public static State state;
	public static Level level;
	private static Image image;
	private static Graphics _second;
	private static URL base;
	private static long startTime,lastFrameTime,deltaTime;

	@Override
	public void init() {
		setSize(950,600);
		setBackground(Color.gray);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Game");
		addMouseListener(this);
		try{
			base = getDocumentBase();
		}catch(Exception e){
			e.printStackTrace();
		}
		gameField = new GameField();

		Thread thread = new Thread(this);
		thread.start();
		startTime = lastFrameTime = System.currentTimeMillis();
		
		level = Level.ONE;
		state = State.START_LEVEL;
		state.start();
	}

	@Override
	public void start() {
		
	}
	@Override
	public void stop() {
	}
	@Override
	public void destroy() {
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			_second = image.getGraphics();
		}

		_second.setColor(getBackground());
		_second.fillRect(0, 0, getWidth(), getHeight());
		_second.setColor(getForeground());
		paint(_second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void run() {
		while(true){
			repaint();
			deltaTime = FRAME_INTERVAL - (System.currentTimeMillis() - lastFrameTime);
			if(deltaTime > 0)
				try{
					Thread.sleep(deltaTime);
				}catch(Exception e){
					e.printStackTrace();
				}
			lastFrameTime = System.currentTimeMillis();
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {}
	@Override
	public void keyReleased(KeyEvent k) {}
	@Override
	public void keyTyped(KeyEvent k) {}

	@Override
	public void mouseClicked(MouseEvent m) {}
	@Override
	public void mouseEntered(MouseEvent m) {}
	@Override
	public void mouseExited(MouseEvent m) {}
	@Override
	public void mouseReleased(MouseEvent m) {}
	
	@Override
	public void mousePressed(MouseEvent m) {
		Wall newWall;
		if(m.getButton() == 1){//front click
			newWall = new Wall(new Point2D.Double(m.getX(), m.getY()), Wall.Orientation.HORIZONTAL);
		}
		else{
			newWall = new Wall(new Point2D.Double(m.getX(), m.getY()), Wall.Orientation.VERTICAL);
		}
		for(Wall wall : gameField.getWalls()){
			if(!wall.willFit(newWall))
				return;
		}
		if(!gameField.willFit(newWall))
			return;
		gameField.getWalls().add(newWall);
	}
	
	@Override
	public void paint(Graphics g) {
		state.update();
		state.draw(g);
	}
}
