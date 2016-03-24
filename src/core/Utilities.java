package core;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Michael Topsom
 */
public class Utilities {
	private static Utilities instance;
	
	private Utilities() {
	}
	
	public static Utilities getInstance() {
		if(instance == null) {
			instance = new Utilities();
		}
		
		return instance;
	}
	
	public Font getFont(String filename) {
		try {
			URL url = getClass().getResource("fonts/" + filename);
			InputStream in = url.openStream();
			return Font.createFont(Font.TRUETYPE_FONT, in);
		} catch(IOException e) {
			System.out.println("Font: " + filename + " was not loaded because: " + e.toString());
			System.exit(4);
		} catch(Exception e) {
			System.out.println("Other exception: " + e.toString() + " while loading font: " + filename);
			System.exit(5);
		}
		
		return null;
	}
}
