import java.util.Random;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

/**
 * @author zaid (s3590683) MyRunnable class is used to implement Runnable
 *         interface and to show the progress bars
 *
 */
public class MyRunnable implements Runnable {

	ProgressBar[] bar;

	int MAX = 100;

	int[] n = new int[8];

	public MyRunnable(ProgressBar[] b) {
		bar = b;
	}

	@Override
	public void run() {
		int tMax = 70;
		int tMin = 50;
		Random rand = new Random();

		for (int i = 0; i < n.length; i++) {
			n[i] = rand.nextInt((tMax - tMin) + 1) + tMin;
		}

		for (int i = 1; i <= MAX; i++) {

			final double update_i = i;

			Platform.runLater(new Runnable() {

				@Override
				public void run() {

					for (int j = 0; j < bar.length; j++) {

						bar[j].setProgress(update_i / n[j]);
					}

				}
			});

			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
			}
		}
	}

}