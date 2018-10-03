
import java.util.Random;

/**
 * @author swapnil (s3587683) Cycling class inherit the Game class and is used
 *         to compute compete time for Cycling game
 *
 */
public class Cycling extends Game {

	/*
	 * (non-Javadoc)
	 * 
	 * @see Game#compete() compete() method gets overridden in this class
	 * according to cycling game
	 */
	public int compete() {
		int tMax = 800;
		int tMin = 500;
		int cTime;
		Random rand = new Random();
		cTime = rand.nextInt((tMax - tMin) + 1) + tMin; // random time

		return cTime;
	}

}
