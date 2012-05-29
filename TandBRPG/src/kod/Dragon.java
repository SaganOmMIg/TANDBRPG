package kod;

import java.awt.Graphics2D;

public class Dragon extends Sprite {
	
	public Dragon(int x, int y, GameCanvas gc){
		this.gc = gc;
		this.image = Content.getImage("Dragonright");
		this.position.x = x ;
		this.position.y = y ;
		this.startPosition.x = x ;
		this.startPosition.y = y ; 
		this.topPosition.x = x;
		this.topPosition.y = y;
		this.movingDelay = 3000;
		this.moveSpeed = 60;
		
		this.setCanIntersect(true);
	}
	public void update(int dt){
		if(!movingStopWatch.isRunning()){
			movingStopWatch.start();
			float random = (float)Math.random();
			if(random < 0.25){
				this.velocity.x = moveSpeed;
				this.image = Content.getImage("Dragonright");
			}
			if(random> 0.25 && random < 0.50){
				this.velocity.x = -moveSpeed;
				this.image = Content.getImage("Dragonleft");
			}
			if(random> 0.50 && random < 0.75){
				this.velocity.y = -moveSpeed;
				this.image = Content.getImage("Dragonup");
			}
			if(random > 0.75){
				this.velocity.y = moveSpeed;
				this.image = Content.getImage("Dragondown");
			}	
		}
		if(movingStopWatch.getElapsedTime() > movingDelay){
			movingStopWatch.stop();
			this.velocity.y = 0;
			this.velocity.x = 0;
		}
		this.position.x = gc.getBackGroundimageVector().x + this.startPosition.x;
		this.position.y = gc.getBackGroundimageVector().y +this.startPosition.y;
		super.update(dt);
	}
	

	@Override
	public void render(Graphics2D g) {
		g.drawImage(getImage(),(int)this.position.x,(int) this.position.y,null);
		
	}

}
