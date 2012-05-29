package kod;

import java.awt.Graphics2D;

public class Tree extends Sprite{
	
	public Tree(int x, int y, GameCanvas gc){
		this.gc = gc;
		this.image = Content.getImage("house");
		this.position.x = x ;
		this.position.y = y ;
		this.startPosition.x = x ;
		this.startPosition.y = y ; 
		this.topPosition.x = x;
		this.topPosition.y = y;
		
		this.setCanIntersect(false);
	}
	
	public void update(int dt){
		this.position.x = gc.getBackGroundimageVector().x + this.startPosition.x;
		this.position.y = gc.getBackGroundimageVector().y +this.startPosition.y;
		
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(getImage(),(int)this.position.x,(int) this.position.y,null);
		
	}
	
	

}
