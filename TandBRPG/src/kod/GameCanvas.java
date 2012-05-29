package kod;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameCanvas extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean running;
	private boolean switchingLevel;

	private Controller controller = new Controller();
	private Hero hero = new Hero(Commons.WIDTH/2, Commons.HEIGTH/2);
	private long previousTime;
	private Set<Sprite> sprites = new HashSet<Sprite>();
	private Set<Sprite> oversprites = new HashSet<Sprite>();
	private Image backgroundImage = Content.getImage("bakgrund");
	private float fadingTone;
	private AlphaComposite ac;
	private List<Level> levels = new ArrayList<Level>();
	private Level currentLevel;
	private int levelNumber = 0;
	private Vector2 heroSpawnPoint = new Vector2();

	
	private Vector2 backGroundimageVector = new Vector2();
	
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("TANDBRPG");
		GameCanvas game = new GameCanvas();
		myFrame.add(game);
		myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		game.start();
		myFrame.setLocationRelativeTo(null);
	}

	public GameCanvas() {
		Dimension d = new Dimension(Commons.WIDTH,Commons.HEIGTH);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		levels.add(0, new Level(0,1100,900, this));
		levels.add(1, new Level(1,1000,1000, this));
		levels.add(2, new Level(2,1000,1000, this));
		currentLevel = levels.get(levelNumber);
		heroSpawnPoint.x = currentLevel.getHeroX()+20;
		heroSpawnPoint.y = currentLevel.getHeroY()+20;
		backGroundimageVector.x =Commons.WIDTHBOUNDS - heroSpawnPoint.x;
		backGroundimageVector.y = Commons.HEIGTHBOUNDS - heroSpawnPoint.y ;

		
		oversprites.addAll(currentLevel.getOversprites());
		sprites.addAll(currentLevel.getSprites());

		addMouseListener(controller);
		addMouseMotionListener(controller);
		addKeyListener(controller);

	}

	@Override
	public void run() {
		previousTime = System.currentTimeMillis();
		while (running) {
			update();
			render();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
		}
	}

	public void start() {
		if (!running) {
			Thread t = new Thread(this);
			createBufferStrategy(3);
			running = true;
			t.start();
		}
	}

	private void render() {
		BufferStrategy strategy = getBufferStrategy();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
		g.setComposite(ac);
		
		g.drawImage(backgroundImage, (int)getBackGroundimageVector().x, (int)getBackGroundimageVector().y, null);
		
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
		hero.render(g);
		for (Sprite oversprite : oversprites) {
			oversprite.render(g);
		}
		
		
		strategy.show();
	}

	private void update() {
		long now = System.currentTimeMillis();
		int dt = (int) (now - previousTime);

		
		
		hero.update(controller, dt, this);
		
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		
		}
		for (Sprite oversprite : oversprites) {
			oversprite.update(dt);
		}

		
		previousTime = System.currentTimeMillis();
	}

	
	



	/**
	 * @return the switchingLevel
	 */
	public boolean isSwitchingLevel() {
		return switchingLevel;
	}

	/**
	 * @param switchingLevel
	 *            the switchingLevel to set
	 */
	public void setSwitchingLevel(boolean switchingLevel) {
		this.switchingLevel = switchingLevel;
		this.fadingTone = 0.5f;
	}

	public Controller getController() {
		return controller;
	}

	public Hero getHero() {
		return hero;
	}

	public Set<Sprite> getSprites() {
		return sprites;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}



	public Vector2 getBackGroundimageVector() {
		return backGroundimageVector;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

}
