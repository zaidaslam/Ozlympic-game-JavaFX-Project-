
import java.util.Random;

/**
 * @author swapnil (s3587683) Running class inherit the Game class and is used
 *         to compute compete time for Running game
 *
 */
public class Running extends Game {

	/*
	 * (non-Javadoc)
	 * 
	 * @see Game#compete() method gets overridden in this class according to
	 * running game
	 */
	public int compete() {
		int tMax = 20;
		int tMin = 10;
		int cTime;
		Random rand = new Random();
		cTime = rand.nextInt((tMax - tMin) + 1) + tMin; // random time
		return cTime;
	}
}
