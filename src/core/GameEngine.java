package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import states.IntroState;

/**
 *
 * @author Michael Topsom
 */
class GameEngine implements Runnable, KeyListener {
	private JFrame window;
	private Canvas canvas;
	
	private boolean running = true;
	
	private float currentTime = System.nanoTime();
	private float accumulator = 0.0f;
	
	private float newTime = 0.0f;
	private float deltaTime = 0.0f;
	
	private BufferStrategy buffer;
	private Graphics graphics;
	
	private int ticks = 0;
	
	private StateManager stateManager;
	
	private Constants constants;
	
	public GameEngine() {
		initialise();
	}
	
	private final void initialise() {
		constants = Constants.getInstance();
		
		window = new JFrame();
		window.setIgnoreRepaint(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(constants.getTitle());
		
		window.addKeyListener(this);
		
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setSize(constants.getWindowSize());
		
		window.add(canvas);
		window.pack();
		window.setVisible(true);
		
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
		
		stateManager = StateManager.getInstance();
		stateManager.push(new IntroState());
	}
	
	@Override
	public void run() {
		while(running()) {
			newTime = System.nanoTime();
			deltaTime = newTime - currentTime;
			currentTime = newTime;
			
			accumulator += deltaTime;
			
			while(accumulator >= constants.getTimeStep()) {
				accumulator -= constants.getTimeStep();
				
				if(!stateManager.isEmpty()) {
					if(stateManager.currentState().justLoaded()) {
						accumulator = 0.0f;
					}
					window.requestFocus();
					stateManager.currentState().update();
				} else {
					running = false;
				}
				
				ticks++;
				
				if(ticks > 60) {
					ticks = 0;
				}
			}
			
			render();
		}
		
		System.out.println("Exiting normally.");
		System.exit(1);
	}
	
	private boolean running() {
		return running;
	}
	
	private void render() {
		graphics = buffer.getDrawGraphics();
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, constants.getWindowSize().width, constants.getWindowSize().height);
		
		if(!stateManager.isEmpty()) {
			stateManager.currentState().render(graphics);
		}
		
		if(!buffer.contentsLost()) {
			buffer.show();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Method must be overridden for KeyListener, although the game does not use it
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!stateManager.isEmpty()) {
			stateManager.currentState().keyPressed(e);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(!stateManager.isEmpty()) {
			stateManager.currentState().keyReleased(e);
		}
	}
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		
		Thread gameThread = new Thread(game);
		
		gameThread.start();
	}
}
