import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author my
 *
 */
public class RunningTest {

	@Test
	public void test() {
		
		Running test = new Running();
		int fetch = test.compete();
		int actuals = 0;
		int min = 10;
		int max = 20;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(1,actuals);		
	}
	@Test
	public void testOne() {
		
		Running test = new Running();
		int fetch = test.compete();
		int actuals = 0;
		int min = 30;
		int max = 40;
		if(fetch >= min && fetch<=max)
		{actuals = 1;}
		else
		{actuals = 0;}
		
		assertEquals(0,actuals);		
	}
	
	@Test
	public void testTwo() {
		
		Running test = new Running();
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
