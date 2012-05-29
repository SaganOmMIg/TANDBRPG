package kod;


import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Content {

	private static HashMap<String,Image> images = new HashMap<String, Image>();
	
	private Content() {
		
	}
	
	public static Image getImage(String s) {
		Image image = images.get(s);
		if(image == null) {
			try {
				//System.out.println(s);
				image = ImageIO.read(Content.class.getResource("/images/" + s + ".png"));
				images.put(s, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return image;
	}
}
