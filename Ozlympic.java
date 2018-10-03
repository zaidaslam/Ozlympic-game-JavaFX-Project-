import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Zaid (s3590683) 
 * 		   Ozlympic class is a startup class which inherits
 *         Application class for this Project and contains start() method and
 *         deals with user interaction
 */
public class Ozlympic extends Application {

	public String selected = "Swimmer";

	Driver obj = new Driver();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * 
	 * start() method override the Application class start() method and is used
	 * to set primary stage
	 */
	@Override
	public void start(Stage primaryStage) {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 450, 450);

		ToggleGroup group = new ToggleGroup();

		Label title = new Label("Select a Game to Play");
		title.setTextFill(Color.BLACK);
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		RadioButton swim = new RadioButton("Swimming");
		RadioButton sprint = new RadioButton("Sprinting");
		RadioButton cycle = new RadioButton("Cycling");
		swim.setToggleGroup(group);
		swim.setUserData("Swimmer");
		swim.setSelected(true);
		sprint.setToggleGroup(group);
		sprint.setUserData("Sprinter");
		cycle.setToggleGroup(group);
		cycle.setUserData("Cyclist");

		grid.add(title, 0, 0, 2, 1);
		grid.add(swim, 1, 2);
		grid.add(sprint, 1, 3);
		grid.add(cycle, 1, 4);

		Button next = new Button("Next");
		HBox hbbtn = new HBox(10);
		hbbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbbtn.getChildren().add(next);
		grid.add(hbbtn, 2, 6);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					selected = group.getSelectedToggle().getUserData().toString();
				}
			}
		});

		try {

			next.setOnAction(event -> {

				try {
					obj.secondScene(selected);
					primaryStage.close();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				primaryStage.close();

			});

		} catch (Exception e) {
			// TODO: handle exception
		}

		primaryStage.setTitle("Ozlympic Games");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
