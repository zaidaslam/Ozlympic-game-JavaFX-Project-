import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hsqldb.server.Server;

/**
 * @author zaid (s3590683) Swapnil (s3587683) Database class is used to
 *         implement database for this project
 *
 */
public class Database {

	public static ArrayList<Athlete> participantDb = new ArrayList<Athlete>();
	public static ArrayList<Participant> participant = new ArrayList<Participant>();
	public static ArrayList<String> result = new ArrayList<String>();

	/**
	 * @return
	 * @throws IOException
	 *             getParticipantList() is used to fetch participants list from
	 *             the database
	 */
	public ArrayList<Athlete> getParticipantList() throws IOException {

		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		// making a connection

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");

			// connection.prepareStatement("drop table participants if
			// exists;").execute();
			connection
			.prepareStatement(
					"create table IF NOT EXISTS participants (Id varchar(20), type varchar(20), Name varchar(20), Age varchar(20), State varchar(20), Points Integer not null);")
			.execute();

			rs = connection.prepareStatement("select * from participants;").executeQuery();

			while (rs.next()) {
				Participant part = new Participant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				participant.add(part);
			}

			if (participant.size() < 1) {
				connection
				.prepareStatement("insert into participants (Id, type, Name, Age, State, Points)"
						+ "values ('Oz1101','swimmer','Shane','21','VIC',0) , ('Oz1102','swimmer','Ian','35','NSW',0) , ('Oz1103','swimmer','Dawn','24','QLD',0) , ('Oz1104','swimmer','Gould','30','WA',0) , ('Oz1105','swimmer','Thorpe','30','SA',0),('Oz1106','swimmer','Fraser','30','TAS',0),('Oz1107','swimmer','Murray','30','VIC',0),('Oz1108','swimmer','Rose','30','NSW',0),('Oz1109','swimmer','Grant','0','QLD',0),('Oz1201','sprinter','Beck','26','TAS',0),('Oz1202','sprinter','James','19','VIC',0),('Oz1203','sprinter','Andrew','19','WA',0),('Oz1204','sprinter','Kim','19','SA',0),('Oz1205','sprinter','Philip','19','QLD',0),('Oz1206','sprinter','Sam','19','NT',0),('Oz1207','sprinter','Brian','19','NSW',0),('Oz1208','sprinter','Andrew','19','ACT',0),('Oz1209','sprinter','Joe','19','WA',0),('Oz1301','super','Stuart','21','VIC',0) , ('Oz1302','super','Grady','35','NSW',0) , ('Oz1303','super','Phil','24','QLD',0),('Oz1304','super','Anderson','30','WA',0),('Oz1305','super','Robbie','30','SA',0),('Oz1306','super','McEwan','30','TAS',0),('Oz1307','super','Simon','30','VIC',0),('Oz1308','super','Gerrans','30','NSW',0),('Oz1309','super','Michael','0','QLD',0),('Oz1401','cyclist','Amanda','26','TAS',0),('Oz1402','cyclist','Carel','19','VIC',0),('Oz1403','cyclist','Bush','19','WA',0),('Oz1404','cyclist','Billy','19','SA',0),('Oz1405','cyclist','Sandra','19','QLD',0),('Oz1406','cyclist','Arnold','19','NT',0),('Oz1407','cyclist','Broad','19','NSW',0),('Oz1408','cyclist','Albert','19','ACT',0),('Oz1409','cyclist','Joey','19','WA',0),('Oz1501','officer','Broad','19','NSW',0),('Oz1502','officer','Albert','19','ACT',0),('Oz1503','officer','Joey','19','WA',0);")
				.execute();

			}

			participant.clear();

			rs = connection.prepareStatement("select * from participants;").executeQuery();
			// rs.next();
			while (rs.next()) {
				Participant part = new Participant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				participant.add(part);
			}

			for (int i = 0; i < participant.size(); i++) {

				Athlete athlete = new Athlete(i, participant.get(i).getID(), participant.get(i).getType(),
						participant.get(i).getName(), participant.get(i).getAge(), participant.get(i).getState(),
						participant.get(i).getPoints());
				participantDb.add(athlete);
			}

			connection.commit();
			hsqlServer.stop();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return participantDb;
	}

	/**
	 * @param insertResult
	 *            writeToDB() method is used to store the result of each game in
	 *            database
	 */
	public void writeToDB(ArrayList<String> insertResult) {

		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");

			connection.prepareStatement("CREATE TABLE IF NOT EXISTS result (GameID varchar(200) not null);").execute();

			for (int i = 0; i < insertResult.size(); i++) {
				connection.prepareStatement("insert into result (GameID)" + "values ('" + insertResult.get(i) + "')")
				.execute();

			}

			connection.commit();
			hsqlServer.stop();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		// end of stub code for in/out stub

	}

	/**
	 * @return getResult() method is used to fetch game related information for
	 *         each game being played from database
	 */
	public ArrayList<String> getResult() {

		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
			// connection.prepareStatement("drop table result if
			// exists;").execute();
			connection.prepareStatement("CREATE TABLE IF NOT EXISTS result (GameID varchar(200) not null);").execute();
			rs = connection.prepareStatement("select * from result;").executeQuery();
			result.clear();
			while (rs.next()) {

				result.add(rs.getString(1));
			}

			connection.commit();
			hsqlServer.stop();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return result;

	}

	/**
	 * @param participant
	 *            updateParticipantDB() method is used to update the participant
	 *            database with the latest athlete points
	 */
	public void updateParticipantDB(ArrayList<Athlete> participant) {

		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		// hsqlServer.start();

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");

			for (int i = 0; i < participant.size(); i++) {
				connection.prepareStatement("UPDATE participants SET Points = '" + participant.get(i).getPoints()
						+ "' WHERE id = '" + participant.get(i).getID() + "'").execute();
			}

			connection.commit();
			hsqlServer.stop();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * @param gameType
	 * @return
	 * @throws FileNotFoundException
	 *             getGameidDB() method is used to compute the game ID of the
	 *             game
	 */
	public int getGameidDB(String gameType) throws FileNotFoundException {

		int gameID = 0;
		getResult();

		String searchStr = null;
		switch (gameType) {
		case "Swimmer":
			searchStr = "S";
			break;

		case "Sprinter":
			searchStr = "R";
			break;

		case "Cyclist":
			searchStr = "C";
			break;

		default:
			break;
		}

		String find = null;
		// System.out.println("to find "+searchStr);
		// System.out.println("size------------------------ "+result.size());
		for (int i = 0; i < result.size(); i++) {

			String line = result.get(i);

			if (line.contains(searchStr)) {

				find = line;
				int index = find.indexOf(',');
				String subString;
				subString = find.substring(1, index);
				gameID = Integer.parseInt(subString);
				// System.out.println(line);
			}

			if (!(line.contains(searchStr))) {

				find = line;
				gameID = 0;
			
			}
			

			

		}
		// System.out.println("Game id DB "+gameID);

		result.clear();

		return gameID;

	}

	/**
	 * @param GameID
	 * @param officialID
	 * @param selected
	 * @return setGameID() method is used to set game ID and other essential
	 *         info about the game
	 */
	public String setGameID(int GameID, String officialID, String selected) {

		String strr = null;

		int gameID = ++GameID;

		// System.out.println(gameID);

		String gID = Integer.toString(gameID);

		String oID = officialID;

		String str;

		switch (selected) {
		case "Swimmer":
			if (gameID < 10) {
				strr = "S0" + gID;
			} else {
				strr = "S" + gID;
			}
			break;

		case "Sprinter":
			if (gameID < 10) {
				strr = "R0" + gID;
			} else {
				strr = "R" + gID;
			}
			break;

		case "Cyclist":
			if (gameID < 10) {
				strr = "C0" + gID;
			} else {
				strr = "C" + gID;
			}
			break;

		default:
			break;
		}

		String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		strDate = strDate.substring(0, strDate.length() - 1);

		str = strr + ", " + oID + ", " + strDate;

		return str;

	}

}