package kod;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.lang.invoke.SwitchPoint;

public class Hero extends Sprite {
	private int safety = 5;

	public Hero(int x, int y) {
		image = Content.getImage("hero");
		this.width = this.getImage().getWidth(null);

		this.height = this.getImage().getHeight(null);
		this.position.x = x - this.getImage().getWidth(null) / 2;
		this.position.y = y - this.getImage().getHeight(null);
		this.positionOnScreen.x = 0;
		this.positionOnScreen.y = 0;
		this.topPosition.x = x;
		this.topPosition.y = y;
		this.moveSpeed = 0.1f;

		this.acceleration.x = 0;
		this.acceleration.y = 0;
		this.velocity.y = 0;
		this.velocity.x = 0;

	}

	public void update(Controller controller, int dt, GameCanvas gc) {

		move(controller, gc, dt);
		checkOutOfBounds(gc);
		checkCollision(gc);

	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.x, (int) position.y, null);
		

	}

	public void move(Controller controller, GameCanvas gc, int dt) {

		if (controller.keys[KeyEvent.VK_W]) {
			gc.getBackGroundimageVector().y += moveSpeed * dt;
		}

		if (controller.keys[KeyEvent.VK_S]) {
			gc.getBackGroundimageVector().y -= moveSpeed * dt;

		}

		if (controller.keys[KeyEvent.VK_A]) {
			gc.getBackGroundimageVector().x += moveSpeed * dt;

		}
		if (controller.keys[KeyEvent.VK_D]) {
			gc.getBackGroundimageVector().x -= moveSpeed * dt;

		}
		this.positionOnScreen.x = this.position.x
				+ this.getImage().getWidth(null) / 2
				- gc.getBackGroundimageVector().x;
		this.positionOnScreen.y = this.position.y
				+ this.getImage().getHeight(null)
				- gc.getBackGroundimageVector().y;

	}

	public void checkCollision(GameCanvas gc) {
		for (Sprite sprite : gc.getSprites()) {
			if(!sprite.canIntersect){
				
			
			
			//Left side of sprite
			if (this.positionOnScreen.x + this.getImage().getWidth(null) / 2 > sprite.startPosition.x
					&& this.positionOnScreen.x + this.getImage().getWidth(null)
							/ 2 < sprite.startPosition.x + safety

					&& this.positionOnScreen.y > sprite.startPosition.y
					&& this.positionOnScreen.y < sprite.startPosition.y
							+ sprite.getImage().getHeight(null)) {
				
				gc.getBackGroundimageVector().x = -(sprite.startPosition.x
						- Commons.WIDTH / 2 - this.getImage().getWidth(null) / 2);
				
			}
			
			//Right side of sprite
			if (this.positionOnScreen.x - this.getImage().getWidth(null) / 2 < sprite.startPosition.x
					+ sprite.getImage().getWidth(null) 
					&& this.positionOnScreen.x - this.getImage().getWidth(null)
							/ 2 > sprite.startPosition.x +sprite.getImage().getWidth(null) - safety

					&& this.positionOnScreen.y > sprite.startPosition.y
					&& this.positionOnScreen.y < sprite.startPosition.y
							+ sprite.getImage().getHeight(null)) {
				gc.getBackGroundimageVector().x = -(sprite.startPosition.x
						+ sprite.getImage().getWidth(null) - Commons.WIDTH / 2 + this
						.getImage().getWidth(null) / 2) ;
	
			}
			
			//Top side of sprite
			if (this.positionOnScreen.y  > sprite.startPosition.y
					&& this.positionOnScreen.y  < sprite.startPosition.y +safety

					&& this.positionOnScreen.x+this.getImage().getWidth(null)/2 > sprite.startPosition.x
					&& this.positionOnScreen.x-this.getImage().getWidth(null)/2 < sprite.startPosition.x
							+ sprite.getImage().getWidth(null)) {
				
				gc.getBackGroundimageVector().y = -(sprite.startPosition.y
						- Commons.HEIGTH / 2 );
				
			}
			
			//Bottom side of sprite
			if (this.positionOnScreen.y  < sprite.startPosition.y + sprite.getImage().getHeight(null)
					&& this.positionOnScreen.y  > sprite.startPosition.y + sprite.getImage().getHeight(null)-safety

					&& this.positionOnScreen.x+this.getImage().getWidth(null)/2 > sprite.startPosition.x
					&& this.positionOnScreen.x-this.getImage().getWidth(null)/2 < sprite.startPosition.x
							+ sprite.getImage().getWidth(null)) {
				
				gc.getBackGroundimageVector().y = -(sprite.startPosition.y + sprite.getImage().getHeight(null)
						- Commons.HEIGTH / 2 );
		
			}
		}
		}
	}

	public void checkOutOfBounds(GameCanvas gc) {
		if (gc.getBackGroundimageVector().y > -20) {
			gc.getBackGroundimageVector().y = -20;
		}
		if (gc.getBackGroundimageVector().x > -20) {
			gc.getBackGroundimageVector().x = -20;
		}
		if (gc.getBackGroundimageVector().x < -gc.getBackgroundImage()
				.getWidth(null) + Commons.WIDTH + 20) {
			gc.getBackGroundimageVector().x = -gc.getBackgroundImage()
					.getWidth(null) + Commons.WIDTH + 20;
		}
		if (gc.getBackGroundimageVector().y < -gc.getBackgroundImage()
				.getHeight(null) + Commons.HEIGTH) {
			gc.getBackGroundimageVector().y = -gc.getBackgroundImage()
					.getHeight(null) + Commons.HEIGTH;
		}

	}

}
