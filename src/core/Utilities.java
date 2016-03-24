package core;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Topsom
 */
public class Utilities {
	private static Utilities instance;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
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
		} catch(IOException ioe) {
			log.log(Level.SEVERE, "Font: " + filename + " was not loaded", ioe);
			System.exit(4);
		} catch(Exception e) {
			log.log(Level.SEVERE, "Other exception: " + e.toString() + " while loading font.", e);
			System.exit(5);
		}
		
		return null;
	}
}
