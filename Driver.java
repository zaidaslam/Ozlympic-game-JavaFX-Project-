import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author  Zaid (s3590683)
 * 			swapnil (s3587683)
 * 			Driver class is used to handle different Stage for this project
 */
public class Driver {

	public String selected;
	Stage secondStage;
	Stage thridStage;
	Stage stage;
	Stage fourthStage;
	Stage displayParticipantResult;
	Stage displayResult;
	Game obj;
	boolean db = true;
	int officerIndex = 0;
	String officialid;
	public ArrayList<Athlete> participant = new ArrayList<Athlete>();
	public ArrayList<Item> tempStore = new ArrayList<>();
	int count = 0;
	String wstring;
	final int MAX = 100;

	Thread myRunnableThread;

	MyRunnable myRunnable;

	/**
	 * @param selected
	 * @throws NumberFormatException
	 * @throws IOException
	 * secondScene() is used to implement and show the list of participants which are available to compete in a game
	 * to fetch the essential information regarding game from the available storage medium (Database/File)
	 */
	public void secondScene(String selected) throws NumberFormatException, IOException {

		Button btn = new Button("Next");
		Label label = new Label("Select" + "  " + selected + "s");
		ListView<Item> listView = new ListView<>();
		System.out.println(selected+" type is selected");
		Database dbOBJ = new Database();
		participant = dbOBJ.getParticipantList();
	//	 participant.clear();

		try {
			if (participant.size() < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Look, an Error Dialog");
				alert.setContentText("Ooops, Database not found!");
				alert.showAndWait();

				participant = FileHandling.participantFileData();
				db = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (participant.size() < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Look, an Error Dialog");
				alert.setContentText("Ooops, File not found!");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		for (int i = 0; i < participant.size(); i++) {
			if (!(participant.get(i).getType().equals("officer"))) {
				String str = String.format("%-10s%-10s%10s%10s%10s", participant.get(i).getID(),
						participant.get(i).getType(), participant.get(i).getName(), participant.get(i).getAge(),
						participant.get(i).getState());

				
				Item item = new Item(participant.get(i).getIndex(), str, participant.get(i).getType(), false,
						participant.get(i).getID());

				item.onProperty().addListener((obs, wasOn, isNowOn) -> {

					String type = "super";

					if (item.isOn() == true) {
						try {
							if (!(selected.toUpperCase().equals(item.getType().toUpperCase()))
									&& !(item.getType().toUpperCase().equals(type.toUpperCase()))) {

								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error Dialog");
								alert.setHeaderText("Look, an Error Dialog");
								alert.setContentText("Ooops, Wrong Athlete Type!, Please UNCHECK");
								alert.showAndWait();

							} else {
								tempStore.add(item);
							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (item.isOn() == false)
						tempStore.remove(item);
				});

				listView.getItems().add(item);
			}

		}
		listView.setCellFactory(CheckBoxListCell.forListView(new Callback<Item, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(Item item) {
				return item.onProperty();
			}
		}));

		btn.setOnAction(event -> {

			int type = 0;
			for (int i = 0; i < tempStore.size(); i++) {
				if (!(tempStore.get(i).getType().trim().equals(selected)))
					;
				type++;
			}

			// for (int i = 0; i < tempStore.size(); i++) {
			// System.out.println(tempStore.get(i).getIndex());
			// }
			try {
				if (tempStore.size() < 4) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Look, an Error Dialog");
					alert.setContentText("Ooops, To few Athletes!");

					alert.showAndWait();
					// stage.close();
				}

				if (tempStore.size() > 8) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Look, an Error Dialog");
					alert.setContentText("Ooops, To many Athletes!");

					alert.showAndWait();
					// stage.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			if (tempStore.size() >= 4 && tempStore.size() <= 8) {
				System.out.println("List of Athlete Selected by User");
				System.out.println();
				for(int i=0;i<tempStore.size();i++) {
				System.out.println(tempStore.get(i).getID());
				}
				ThirdScene(selected);
				secondStage.close();
			}
		});
		BorderPane root = new BorderPane();
		VBox pane = new VBox();
		pane.getChildren().add(listView);
		pane.setAlignment(Pos.CENTER);
		root.setTop(label);
		root.setCenter(pane);

		root.setBottom(btn);

		// root.getChildren().add(listView);
		// root.getChildren().add(btn);
		// root.getChildren().add(label);
		// BorderPane root = new BorderPane(listView);
		Scene scene = new Scene(root, 450, 450);
		//

		secondStage = new Stage();
		secondStage.setTitle("Participants List");
		secondStage.setScene(scene);
		secondStage.show();

	}

	/**
	 * @param selected
	 * thirdScene() is used to implement and show the list of official which are available 
	 */
	public void ThirdScene(String selected) {

		Label label = new Label("Select Official");
		Button BTB2 = new Button("Next");
		ListView<Item> listView = new ListView<>();

		for (int i = 0; i < participant.size(); i++) {
			if (participant.get(i).getType().equals("officer")) {
				String str = String.format("%-10s%-10s%10s%10s%10s", participant.get(i).getID(),
						participant.get(i).getType(), participant.get(i).getName(), participant.get(i).getAge(),
						participant.get(i).getState());

				Item item = new Item(participant.get(i).getIndex(), str, participant.get(i).getType(), false,
						participant.get(i).getID());

				item.onProperty().addListener((obs, wasOn, isNowOn) -> {
					// System.out.println(item.getIndex()+item.getName() + "
					// changed on state from "+wasOn+" to "+isNowOn);

					if (item.isOn() == true) {
						tempStore.add(item);

						count++;
						// System.out.println("Count"+count);
					}
					if (item.isOn() == false) {
						tempStore.remove(item);
						count--;
						// System.out.println("Count"+count);
					}
				});
				listView.getItems().add(item);
			}
		}

		listView.setCellFactory(CheckBoxListCell.forListView(new Callback<Item, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(Item item) {
				return item.onProperty();
			}
		}));

		BTB2.setOnAction(event -> {

			for (int i = 0; i < tempStore.size(); i++) {
				// System.out.println(tempStore.get(i).getIndex());
			}
			try {
				// System.out.println("Count"+count);
				if (count < 1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Look, an Error Dialog");
					alert.setContentText("Ooops, Please Select Official!");

					alert.showAndWait();
				}

				if (count > 1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Look, an Error Dialog");
					alert.setContentText("Ooops, To many Officials!");

					alert.showAndWait();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			if (count == 1) {
				

				progressScene(selected);

				// Application.launch(JavaFX_TimerTask.class);
				thridStage.close();
			}
		});

		BorderPane root = new BorderPane();

		root.setTop(label);

		VBox pane = new VBox();

		pane.getChildren().add(listView);

		root.setCenter(pane);
		root.setBottom(BTB2);

		// BorderPane root = new BorderPane(listView);
		Scene scene = new Scene(root, 450, 450);
		thridStage = new Stage();
		thridStage.setScene(scene);
		thridStage.show();

	}

	/**
	 * @param selected
	 * progressScene() is used to implement and show the progress of game being played 
	 */
	public void progressScene(String selected) {

		switch (selected) {
		case "Swimmer":
			obj = new Swimming();

			break;
		case "Sprinter":
			obj = new Running();

			break;

		case "Cyclist":
			obj = new Cycling();

			break;

		default:
			break;
		}

		for (int i = 0; i < tempStore.size(); i++) {

			if (tempStore.get(i).getType().equals("officer")) {

				officerIndex = tempStore.get(i).getIndex();
				officialid = participant.get(officerIndex).getID();
				System.out.println(officialid+" official selected");
				tempStore.remove(i);

			}
		}

		int[] arr = new int[tempStore.size()];

		for (int i = 0; i < tempStore.size(); i++) {
			arr[i] = obj.compete();
		}

		Arrays.sort(arr);
		Collections.shuffle(tempStore);

		participant.get(tempStore.get(0).getIndex()).setPoints(5);
		participant.get(tempStore.get(1).getIndex()).setPoints(3);
		participant.get(tempStore.get(2).getIndex()).setPoints(1);

		// final Float[] values = new float[tempStore.size()];
		final Label[] labels = new Label[arr.length];
		final ProgressBar[] pbs = new ProgressBar[arr.length];
		final HBox hbs[] = new HBox[arr.length];

		Group root = new Group();

		Scene scene = new Scene(root, 450, 450);

		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Progress Controls");

		for (int i = 0; i < arr.length; i++) {
			final Label label = labels[i] = new Label();
			String show = tempStore.get(i).getID();
			System.out.println(show);
			label.setText(show);

			// ProgressBar progressBarRunnable = new ProgressBar();
			final ProgressBar pb = pbs[i] = new ProgressBar();
			pb.setProgress(0);
			myRunnable = new MyRunnable(pbs);

			final HBox hb = hbs[i] = new HBox();
			hb.setSpacing(5);
			hb.setAlignment(Pos.CENTER);
			hb.getChildren().addAll(label, pb);
		}

		Button btnStart = new Button("Start Task");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				myRunnableThread = new Thread(myRunnable);
				myRunnableThread.start();

			}
		});

		Button next = new Button("Next");
		next.setOnAction(event -> {
			try {
				// displayParticipantsResult();
				FourthScene(selected, arr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.close();

		});

		final VBox vb = new VBox();
		vb.setSpacing(5);
		vb.getChildren().addAll(hbs);
		vb.getChildren().add(btnStart);
		vb.getChildren().add(next);
		scene.setRoot(vb);
		stage.show();
	}

	/**
	 * @param selected
	 * @param arr
	 * @throws IOException
	 * fourthSecene() is used to implement and compile the result of game being played and
	 * to store the essential information regarding game in the available storage medium (Database/File)
	 * 
	 */
	public void FourthScene(String selected, int[] arr) throws IOException {

		Button pPoint = new Button("Display Points");
		Button gResult = new Button("Display Results");
		Label official = new Label("Official for this Game"+participant.get(officerIndex).getID());
		ListView<Item> listView = new ListView<>();

		

		ArrayList<String> insertResults = new ArrayList<String>();

		if (db) {
			Database dbOBJ = new Database();
			dbOBJ.updateParticipantDB(participant);
			int GameID = dbOBJ.getGameidDB(selected);
			String insert = dbOBJ.setGameID(GameID, officialid, selected);
			insertResults.add(insert);
		} else {

			FileHandling gameID = new FileHandling();
			gameID.writePoints(participant);
			int GameID = gameID.getGameID(selected);
			gameID.writeToFile(GameID, officialid, selected);
		}
		System.out.println("Game Result");
		for (int i = 0; i < tempStore.size(); i++) {

			String str = String.format("%-10s%-10s%10s%10s%10s%10d%10d",
					participant.get(tempStore.get(i).getIndex()).getID(),
					participant.get(tempStore.get(i).getIndex()).getType(),
					participant.get(tempStore.get(i).getIndex()).getName(),
					participant.get(tempStore.get(i).getIndex()).getAge(),
					participant.get(tempStore.get(i).getIndex()).getState(), arr[i],
					participant.get(tempStore.get(i).getIndex()).getPoints());

			wstring = participant.get(tempStore.get(i).getIndex()).getID() + ", " + arr[i] + ", "
					+ participant.get(tempStore.get(i).getIndex()).getPoints();
			
			System.out.println(wstring);

			Item item = new Item(participant.get(i).getIndex(), str, participant.get(i).getType(), false,
					participant.get(i).getID());

			listView.getItems().add(item);

			if (db) {
				insertResults.add(wstring);
			} else {
				FileHandling gameID = new FileHandling();
				gameID.writeToFile(wstring);
			}

		}

		if (db) {
			Database dbOBJ = new Database();
			dbOBJ.writeToDB(insertResults);
		} else {

			try (FileWriter fw = new FileWriter("gameResults.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println();

			} catch (IOException e) {

			}
		}

		pPoint.setOnAction(event -> {
			try {
				displayParticipantsResult();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fourthStage.close();

		});

		gResult.setOnAction(event -> {
			try {

				displayResult();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fourthStage.close();

		});
		BorderPane root = new BorderPane();

		root.setTop(official);

		VBox pane = new VBox();

		pane.getChildren().add(listView);

		root.setCenter(pane);
		HBox button = new HBox();

		button.getChildren().add(pPoint);
		button.getChildren().add(gResult);

		root.setBottom(button);

		// BorderPane root = new BorderPane(listView);
		Scene scene = new Scene(root, 450, 450);

		fourthStage = new Stage();
		fourthStage.setScene(scene);
		fourthStage.show();

	}

	/**
	 * @throws IOException
	 * displayParticipantsResult() is used to fetch the all Athlete points from available medium (Database/File)
	 */
	public void displayParticipantsResult() throws IOException {

		Button Exit = new Button("Exit");
		Button getResult = new Button("Show Result");
		Label label = new Label("Participant Points");
		ListView<Item> listView = new ListView<>();

		for (int i = 0; i < participant.size(); i++) {

			if (!(participant.get(i).getType().equals("officer"))) {
				String str = String.format("%-10s%-10s%10s%10s%10s%10d", participant.get(i).getID(),
						participant.get(i).getType(), participant.get(i).getName(), participant.get(i).getAge(),
						participant.get(i).getState(), participant.get(i).getPoints());

				Item item = new Item(participant.get(i).getIndex(), str, participant.get(i).getType(), false,
						participant.get(i).getID());

				listView.getItems().add(item);
			}
		}

		Exit.setOnAction(event -> {
			displayParticipantResult.close();

		});

		getResult.setOnAction(event -> {
			try {

				displayResult();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayParticipantResult.close();

		});

		BorderPane root = new BorderPane();

		root.setTop(label);

		VBox pane = new VBox();

		pane.getChildren().add(listView);

		root.setCenter(pane);

		HBox button = new HBox();

		button.getChildren().add(Exit);
		button.getChildren().add(getResult);

		root.setBottom(button);

		// BorderPane root = new BorderPane(listView);
		Scene scene = new Scene(root, 450, 450);

		displayParticipantResult = new Stage();
		displayParticipantResult.setScene(scene);
		displayParticipantResult.show();

	}

	/**
	 * @throws IOException
	 * displayResult() is used to fetch the games result from available medium (Database/File)
	 */
	public void displayResult() throws IOException {
		ArrayList<String> showResult = new ArrayList<String>();
		if (db) {
			Database dbOBJ = new Database();
			// dbOBJ.getResult();
			showResult = dbOBJ.getResult();
		} else {
			FileHandling fileOBJ = new FileHandling();
			// fileOBJ.getGameResultFile()
			showResult = fileOBJ.getGameResultFile();
		}

		Button Exit = new Button("Exit");
		Button getPoint = new Button("Show Point");
		Label label = new Label("Game Result");
		ListView<Item> listView = new ListView<>();

		for (int i = 0; i < showResult.size(); i++) {

			String str = showResult.get(i);

			Item item = new Item(i, str, "true", false, "0");

			listView.getItems().add(item);

		}

		Exit.setOnAction(event -> {
			displayResult.close();

		});

		getPoint.setOnAction(event -> {
			try {

				displayParticipantsResult();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayResult.close();

		});

		BorderPane root = new BorderPane();

		root.setTop(label);

		VBox pane = new VBox();

		pane.getChildren().add(listView);

		root.setCenter(pane);

		HBox button = new HBox();

		button.getChildren().add(Exit);
		button.getChildren().add(getPoint);

		root.setBottom(button);

		// BorderPane root = new BorderPane(listView);
		Scene scene = new Scene(root, 450, 450);

		displayResult = new Stage();
		displayResult.setScene(scene);
		displayResult.show();

	}

	/**
	 * @author zaid (s3590683)
	 *Item class is to store data in list view
	 */
	public static class Item {
		private final StringProperty name = new SimpleStringProperty();
		private final StringProperty type = new SimpleStringProperty();
		private final IntegerProperty index = new SimpleIntegerProperty();
		private final BooleanProperty on = new SimpleBooleanProperty();
		private final StringProperty id = new SimpleStringProperty();

		public Item(int index, String name, String type, boolean on, String id) {

			setIndex(index);
			setName(name);
			setType(type);
			setOn(on);
			setID(id);
		}

		public final IntegerProperty indexProperty() {
			return this.index;
		}

		public final int getIndex() {
			return this.indexProperty().get();
		}

		public final void setIndex(final int index) {
			this.indexProperty().set(index);
		}

		public final StringProperty nameProperty() {
			return this.name;
		}

		public final String getName() {
			return this.nameProperty().get();
		}

		public final void setName(final String name) {
			this.nameProperty().set(name);
		}

		public final StringProperty idProperty() {
			return this.id;
		}

		public final String getID() {
			return this.idProperty().get();
		}

		public final void setID(final String id) {
			this.idProperty().set(id);
		}

		public final StringProperty typeProperty() {
			return this.type;
		}

		public final String getType() {
			return this.typeProperty().get();
		}

		public final void setType(final String type) {
			this.typeProperty().set(type);
		}

		public final BooleanProperty onProperty() {
			return this.on;
		}

		public final boolean isOn() {
			return this.onProperty().get();
		}

		public final void setOn(final boolean on) {
			this.onProperty().set(on);
		}

		@Override
		public String toString() {
			return getName();
		}

	}

	
}
