package kod;

import java.util.HashSet;
import java.util.Set;

public class Level {

	private Set<Sprite> sprites = new HashSet<Sprite>();
	private Set<Sprite> oversprites = new HashSet<Sprite>();
	private int heroX;
	private int heroY;

	public Level(int number, int heroX, int heroY, GameCanvas gc) {
		int dragonx = 1000;
		int dragony= 1000;
		switch (number) {
		case 0:
			this.heroX = heroX;
			this.heroY = heroY;

			sprites.add(new Tree(1000, 1000, gc));
			sprites.add(new Dog(1200,1200,gc));
			for (int i = 0; i < 15; i++) {
				oversprites.add(new Dragon(dragonx,dragony,gc));
				
			}
			
			

			break;
		case 1:
			this.heroX = heroX;
			this.heroY = heroY;
			break;
		case 2:
			this.heroX = heroX;
			this.heroY = heroY;
			break;
		default:
			break;
		}

	}

	public Set<Sprite> getSprites() {
		return sprites;
	}

	/**
	 * @return the heroX
	 */
	public int getHeroX() {
		return heroX;
	}

	/**
	 * @param heroX
	 *            the heroX to set
	 */
	public void setHeroX(int heroX) {
		this.heroX = heroX;
	}

	/**
	 * @return the heroY
	 */
	public int getHeroY() {
		return heroY;
	}

	/**
	 * @param heroY
	 *            the heroY to set
	 */
	public void setHeroY(int heroY) {
		this.heroY = heroY;
	}

	public Set<Sprite> getOversprites() {
		return oversprites;
	}

	/**
	 * @return the startX
	 */

}
