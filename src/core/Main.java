package core;

/**
 *
 * @author Michael Topsom
 */
public class Main {
	private Main() {
	}
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		
		Thread gameThread = new Thread(game);
		
		gameThread.start();
	}
}
