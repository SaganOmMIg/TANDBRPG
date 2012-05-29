package kod;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public abstract class Sprite {
	protected Vector2 position = new Vector2();
	protected Vector2 topPosition = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected Vector2 acceleration = new Vector2();
	protected Vector2 startPosition = new Vector2();
	protected Vector2 positionOnScreen = new Vector2();

	protected int width;
	protected int height;
	protected Image image;

	protected GameCanvas gc;
	protected int nextLevel;
	protected boolean canIntersect;
	protected int direction;
	protected StopWatch movingStopWatch = new StopWatch();
	protected int movingDelay;
	protected float moveSpeed;

	public int getWidth() {
		return this.width;
	}

	Image getImage() {
		return image;
	}

	Rectangle getRect() {

		return new Rectangle((int) this.position.x, (int) this.position.y,
				image.getWidth(null), image.getHeight(null));

	}

	/**
	 * @return the xdir
	 */

	public void update(int dt) {

		// a=F/m
		// v=v+a*dt
		// r=r+v*dt

		float deltaTime = (dt * 0.001f);

		  velocity = Vector2.add(velocity, Vector2.multiply(acceleration,
		  deltaTime)); 
		  startPosition = Vector2.add(startPosition,
		  Vector2.multiply(velocity, deltaTime));
	

	}

	public abstract void render(Graphics2D g);

	public int getHeigth() {
		return height;
	}

	public void setHeigth(int heigth) {
		this.height = heigth;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}

	public int getDirection() {
		return direction;
	}

	public Vector2 getTopPosition() {
		return topPosition;
	}

	public StopWatch getMovingStopWatch() {
		return movingStopWatch;
	}

	public boolean isCanIntersect() {
		return canIntersect;
	}

	public void setCanIntersect(boolean canIntersect) {
		this.canIntersect = canIntersect;
	}

	public Line2D getBottomline() {

		return new Line2D.Float((int) this.getPosition().x,
				(int) this.getPosition().y + this.getImage().getHeight(null),
				(int) this.getPosition().x + this.getImage().getWidth(null),
				(int) this.getPosition().y + this.getImage().getHeight(null));

	}

	public Rectangle getTopline() {
		return new Rectangle((int) this.getPosition().x,
				(int) this.getPosition().y, this.getImage().getWidth(null),
				10);

	}

}
