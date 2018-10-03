
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author swapnil (s3587683) FileHandling class is used to implement File
 *         Handling for this project
 *
 */
public class FileHandling {

	public ArrayList<Athlete> participantList = new ArrayList<Athlete>();
	public static ArrayList<Athlete> participant = new ArrayList<Athlete>();

	/**
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 *             ParticipantFileData() is used to fetch participants list from
	 *             the File
	 */
	public static ArrayList<Athlete> participantFileData() throws NumberFormatException, IOException {

		FileHandling file = new FileHandling();
		file.readCorrectFormat();
		file.removeDuplicate();

		BufferedReader reader = new BufferedReader(new FileReader("participants.txt"));

		String line;
		int i = 0;
		int point = 0;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",");

			// System.out.println("pae"+parts.length);

			if (parts.length < 6) {
				// point = Integer.parseInt(parts[5].trim());
				point = 0;
			}
			if (parts.length == 6) {

				point = Integer.parseInt(parts[5].trim());
			}
			Athlete ath = new Athlete(i, parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
					parts[4].trim(), point);

			i++;
			participant.add(ath);

		}
		reader.close();

		return participant;
	}

	/**
	 * @param gameType
	 * @return
	 * @throws FileNotFoundException
	 *             getGameID() method is used to compute the game ID of the game
	 */
	public int getGameID(String gameType) throws FileNotFoundException {

		int gameID = 0;

		File f = new File("gameResults.txt");
		if (f.exists()) {
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
			String file = "gameResults.txt";
			Scanner scan = new Scanner(new File(file));

			if (!(file.length() == 0)) {
				while (scan.hasNext()) {
					String line = scan.nextLine().toString();

					if (line.contains(searchStr)) {
						// System.out.println(line);
						find = line;
						int index = find.indexOf(',');
						String subString;
						subString = find.substring(1, index);
						gameID = Integer.parseInt(subString);
					}

					if (!(line.contains(searchStr))) {

						find = line;
						gameID = 0;
					
					}
				
				}
			
				
			}
		} else {
			System.out.println("File Not Exist");
		}
		return gameID;

	}

	/**
	 * @param GameID
	 * @param officialID
	 * @param selected
	 * @return writeToFile() method is used to store the result of each game in
	 *         File
	 */
	public String writeToFile(int GameID, String officialID, String selected) {

		String strr = null;

		int gameID = ++GameID;

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
		// System.out.println(strDate);

		str = strr + ", " + oID + ", " + strDate;

		try (FileWriter fw = new FileWriter("gameResults.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(str);

			// System.out.println(participant);

		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
		return str;

	}

	/**
	 * @param list
	 *            writeToFile() method is used to store the result of each game
	 *            in File
	 */
	public void writeToFile(String list) {

		String str = list;

		try (FileWriter fw = new FileWriter("gameResults.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			out.println(str);

		} catch (IOException e) {

		}

	}

	/**
	 * @throws IOException
	 *             readCorrectFormat() method is used to read entries which are
	 *             in correct format from the file
	 */
	public void readCorrectFormat() throws IOException {

		String fetchLine = null;
		String filename = "participants.txt";
		ArrayList<String> temp = new ArrayList<String>();
		// Scanner scan2 = new Scanner(new File(filename));

		BufferedReader reader = new BufferedReader(new FileReader("participants.txt"));

		if (!(filename.length() == 0)) {
			String line;
			while ((line = reader.readLine()) != null) {
				fetchLine = line.toString();
				int count = 0;
				// fetchLine=line;

				char first = fetchLine.charAt(0);
				char check = ',';
				if (!(first == check)) {

					String[] parts = fetchLine.split(",");
					String blank = " ";

					for (int i = 0; i < parts.length; i++) {
						if (parts[i].equals(blank)) {
							count++;

						}
					}

					if (count == 0) {
						// try(FileWriter fw = new FileWriter("game.txt", true);
						// BufferedWriter bw = new BufferedWriter(fw);
						// PrintWriter out = new PrintWriter(bw))
						// {
						// out.println(fetchLine);
						//
						// } catch (IOException e) {
						// //exception handling left as an exercise for the
						// reader
						// }

						temp.add(fetchLine);
					}
				}

			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("participants.txt"));

			//
			for (int i = 0; i < temp.size(); i++) {

				String str = temp.get(i).toString();

				writer.write(str);
				writer.newLine();
			}
			writer.close();
		}
	}

	/**
	 * @throws IOException
	 *             removeDuplicate() method is used to remove duplicate entries
	 *             from the file
	 */
	public void removeDuplicate() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("participants.txt"));
		Set<String> lines = new HashSet<String>();
		String line;
		String readline;

		while ((line = reader.readLine()) != null) {
			readline = line.trim();
			lines.add(readline);

		}
		reader.close();

		BufferedWriter writer = new BufferedWriter(new FileWriter("participants.txt"));
		for (String unique : lines) {
			writer.write(unique);
			writer.newLine();
		}
		writer.close();
	}

	/**
	 * @param participant
	 * @throws IOException
	 *             writePoints() method is used to update the participant file
	 *             with the latest athlete points
	 */
	public void writePoints(ArrayList<Athlete> participant) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("participants.txt"));

		for (int i = 0; i < participant.size(); i++) {
			String str = participant.get(i).getID() + ", " + participant.get(i).getType() + ", "
					+ participant.get(i).getName() + ", " + participant.get(i).getAge() + ", "
					+ participant.get(i).getState() + ", " + participant.get(i).getPoints();
			writer.write(str);
			writer.newLine();
		}
		writer.close();
	}

	/**
	 * @return
	 * @throws IOException
	 *             getGameResultFile() method is used to fetch game related
	 *             information for each game being played from File
	 */
	public ArrayList<String> getGameResultFile() throws IOException {
		ArrayList<String> getResult = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader("gameResults.txt"));

		String line;

		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			getResult.add(line);
		}
		reader.close();
		return getResult;

	}

}
