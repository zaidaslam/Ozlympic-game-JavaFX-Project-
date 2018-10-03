import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hsqldb.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author my
 *
 */
public class DatabaseTest {

	/**
	 * Test method for {@link Database#getParticipantList()}.
	 * @throws IOException 
	 */
	
	Connection connection = null;
	
	Server hsqlServer = null;
	 @Before
	    public void before() throws SQLException {
		 connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
	    }

	 @After
	    public void after() throws SQLException {
		 connection.commit();
			hsqlServer.stop();
	    }
	
	@Test
	public void testGetParticipantList() throws IOException {
		
		
		Database test = new Database();
		ArrayList<Athlete> testCheck = new ArrayList<Athlete>();
		testCheck = test.getParticipantList();
		int actuals = 0;
		if(testCheck.size()>1){
			actuals = 1;
		}else{
			actuals = 0;
		}
		assertEquals(1,actuals);
		
	}
	@Test
	public void testGetParticipantListOne() throws IOException {
		
		
		Database test = new Database();
		ArrayList<Athlete> testCheck = new ArrayList<Athlete>();
		testCheck = test.getParticipantList();
		int actuals = 0;
		if(testCheck.size()==0){
			actuals = 1;
		}else{
			actuals = 0;
		}
		assertEquals(0,actuals);
		
	}

}
