package core;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author Michael Topsom
 */
public class Constants {
	private static Constants instance;

	private String title;
	private Dimension windowSize;

	private double timeStep;

	private double thrustSpeed;

	private Random rand;

	public enum SpriteType {
		DEFAULT, SMALL, MEDIUM, LARGE, ALIEN;
	}

	private Constants() {
		title = "Vector Blaster";
		windowSize = new Dimension(640, 480);

		timeStep = 1000000000.0 / 60.0;

		thrustSpeed = 2.0;

		rand = new Random();
	}

	public static Constants getInstance() {
		if (instance == null) {
			instance = new Constants();
		}

		return instance;
	}

	public String getTitle() {
		return title;
	}

	public Dimension getWindowSize() {
		return windowSize;
	}

	public double getTimeStep() {
		return timeStep;
	}

	public double getThrustSpeed() {
		return thrustSpeed;
	}

	public double getRandomDouble() {
		return rand.nextDouble();
	}

	public int getRandomInt(int num) {
		return rand.nextInt(num);
	}
}
