
import java.util.Random;

/**
 * @author swapnil (s3587683) Swimming class inherit the Game class and is used
 *         to compute compete time for Swimming game
 *
 */
public class Swimming extends Game {

	/*
	 * (non-Javadoc)
	 * 
	 * @see Game#compete() method gets overridden in this class according to
	 * Swimming game
	 */
	public int compete() {
		int tMax = 200;
		int tMin = 100;
		int cTime;
		Random rand = new Random();
		cTime = rand.nextInt((tMax - tMin) + 1) + tMin; // random time
		return cTime;
	}

}
