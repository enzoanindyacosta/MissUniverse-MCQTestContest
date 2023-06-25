import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login extends Stage {

	private Landing landing;
	private static int contIndex;

	public Login(LinkedList<Contestant> contList, LinkedList<Question> quesList) {

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
			contestantDropDownList.add(contList.get(i).getName());
		}
		contestantDropDown.setLayoutX(270);
		contestantDropDown.setLayoutY(125);

		Button btnOK = new Button();
		btnOK.setLayoutX(275);
		btnOK.setLayoutY(250);
		btnOK.setText("Login");
		btnOK.setOnAction(e -> {
			// verify user login credentials
			if (verifyLogin(contList, contestantDropDown.getValue(), Password_.getText())) {
				System.out.println("Logged in");
				this.hide();
				landing = new Landing(contList, contIndex, quesList);
				landing.showStage();
			} else {
				warning.setText("Password is incorrect");
			}
		});

		Pane p1 = new Pane();
		p1.getChildren().add(Username);
		p1.getChildren().add(btnOK);
		p1.getChildren().add(Password);
		p1.getChildren().add(Password_);
		p1.getChildren().add(warning);
		p1.getChildren().add(test);
		p1.getChildren().add(contestantDropDown);

		Scene myScene = new Scene(p1, 600, 400);
		this.setTitle("MISS UNIVERSE");
		this.setScene(myScene);
	}

	public void showStage() {
		this.show();
	}

	// check if user inputted password matches with the password in contestant list
	public static boolean verifyLogin(LinkedList<Contestant> contList, String n, String p) {
		for (int i = 0; i < 5; ++i) {
			if (n.equals(contList.get(i).getName())) {
				if (p.equals(contList.get(i).getPassword())) {
					contIndex = i;
					MUKT.setContIndex(i);
					return true;
				}
			}
		}
		return false;
	}
}
