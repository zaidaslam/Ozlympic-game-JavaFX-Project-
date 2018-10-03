import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author my
 *
 */
public class CyclingTest {

	/**
	 * Test method for {@link Cycling#compete()}.
	 */
	@Test
	public void testCompete() {
		Cycling test = new Cycling();
		int fetch = test.compete();
		int actuals = 0;
		
		int min = 500;
		int max = 800;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(1,actuals);

	}
	@Test
	public void testCompeteOne() {
		Cycling test = new Cycling();
		int fetch = test.compete();
		int actuals = 0;
		
		int min = 800;
		int max = 1600;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(0,actuals);

	}
	@Test
	public void testCompeteTwo() {
		Cycling test = new Cycling();
		int fetch = test.compete();
		String actuals = "0";
		
		int min = 800;
		int max = 1600;
		if(fetch >= min && fetch<=max)
		{actuals = "1";}
		else
		{actuals = "0";}
		
		assertEquals(0,actuals);

	}

}
