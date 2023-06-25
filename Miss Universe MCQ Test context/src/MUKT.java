import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.LinkedList;

public class MUKT extends Application {

	public static final int SWIDTH = 1200;
	public static final int SHEIGHT = 800;

	private LinkedList<Contestant> contestantList = new LinkedList<Contestant>();
	private LinkedList<Question> questionList = new LinkedList<Question>();

	private static int activeContIndex = 0;
	private Landing landing;
	// private int contIndex;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Font font = Font.font("verdana", /* FontWeight.BOLD, */ FontPosture.REGULAR, 25);
		FileSys.readContestantFile(contestantList);
		FileSys.readQuestionFile(questionList);

		Label Username = new Label();
		Username.setLayoutX(175); // creating username label
		Username.setLayoutY(125);
		Username.setText("Username :");

		Label Password = new Label();
		Password.setLayoutX(175); // creating Password label
		Password.setLayoutY(175);
		Password.setText("Password :");

		Label test = new Label();
		test.setLayoutX(275); // creating Password label
		test.setLayoutY(275);

		PasswordField Password_ = new PasswordField(); // creating Password_ PasswordField
		Password_.setLayoutX(270);
		Password_.setLayoutY(175);
		Password_.setPromptText("enter your password");

		Label warning = new Label("");
		warning.setLayoutX(175);
		warning.setLayoutY(215);
		warning.setStyle("-fx-text-fill:red");

		ComboBox<String> contestantDropDown = new ComboBox<String>();
		contestantDropDown.setPromptText("Select contestant");
		ObservableList<String> contestantDropDownList = contestantDropDown.getItems();
		for (int i = 0; i < 5; ++i) {
			contestantDropDownList.add(contestantList.get(i).getName());
		}
		contestantDropDown.setLayoutX(270);
		contestantDropDown.setLayoutY(125);

		Button btnOK = new Button();
		btnOK.setLayoutX(275);
		btnOK.setLayoutY(250);
		btnOK.setText("Login");
		btnOK.setOnAction(e -> {
			// verify user login credentials
			if (Login.verifyLogin(contestantList, contestantDropDown.getValue(), Password_.getText())) {
				System.out.println("Logged in");
				primaryStage.hide();
				landing = new Landing(contestantList, activeContIndex, questionList);
				landing.showStage();
			} else {
				warning.setText("Password is incorrect");
			}
		});

		Pane root = new Pane();
		root.getChildren().add(Username);
		root.getChildren().add(btnOK);
		root.getChildren().add(Password);
		root.getChildren().add(Password_);
		root.getChildren().add(warning);
		root.getChildren().add(test);
		root.getChildren().add(contestantDropDown);

		Scene myScene = new Scene(root, 600, 400);
		primaryStage.setTitle("MISS UNIVERSE"); // stage title
		primaryStage.setScene(myScene); // place scene in stage
		primaryStage.show();
	}

	// keeps track of contestant index
	public static void setContIndex(int n) {
		activeContIndex = n;
	}

	// function to reset all contestant's score
	public void resetScore(LinkedList<Contestant> contestantList) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 25; ++j){
				contestantList.get(i).setAnswer(j, "X");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
