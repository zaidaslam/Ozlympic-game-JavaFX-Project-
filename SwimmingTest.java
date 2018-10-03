import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author my
 *
 */
public class SwimmingTest {

	@Test
	public void test() {
		Swimming test = new Swimming();
		int fetch = test.compete();
		int actuals = 0;
		int min = 100;
		int max = 200;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(1,actuals);	
	}
	
	@Test
	public void testOne() {
		Swimming test = new Swimming();
		int fetch = test.compete();
		int actuals = 0;
		int min = 400;
		int max = 600;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(0,actuals);	
	}
	
	@Test
	public void testTwo() {
		Swimming test = new Swimming();
		int fetch = test.compete();
		String actuals = "0";
		int min = 30;
		int max = 40;
		if(fetch >= min && fetch<=max)
		{actuals = "1";}
		else
		{actuals = "0";}
		
		assertEquals(0,actuals);	
	}

}
