import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Landing extends Stage {
	private String[] contestantImage;
	Login login;
	TestForm testForm;

	public Landing(LinkedList<Contestant> contList, int contIndex, LinkedList<Question>quesList) {

		// get images
		contestantImage = FileSys.fetchContestantImage(contIndex);
		Image img1 = new Image(contestantImage[0]);
		Image img2 = new Image(contestantImage[1]);
		Image img3 = new Image(contestantImage[2]);

		ImageView img_1 = new ImageView(img1);
		img_1.setImage(img1);
		img_1.setFitHeight(200);
		img_1.setFitWidth(200);
		img_1.setPreserveRatio(true);
		img_1.setSmooth(true);
		img_1.setCache(true);
		img_1.setLayoutY(0);
		img_1.setLayoutX(38);

		ImageView img_2 = new ImageView(img2);
		img_2.setImage(img2);
		img_2.setFitHeight(200);
		img_2.setFitWidth(200);
		img_2.setPreserveRatio(true);
		img_2.setSmooth(true);
		img_2.setCache(true);
		img_2.setLayoutY(0);
		img_2.setLayoutX(230);

		ImageView img_3 = new ImageView(img3);
		img_3.setImage(img3);
		img_3.setFitHeight(200);
		img_3.setFitWidth(200);
		img_3.setPreserveRatio(true);
		img_3.setSmooth(true);
		img_3.setCache(true);
		img_3.setLayoutY(0);
		img_3.setLayoutX(420);

		Label welcMsg = new Label();
		welcMsg.setLayoutX(50);
		welcMsg.setLayoutY(225);
		welcMsg.setText("Welcome to the Miss Universe Knowledge Test\n");
		welcMsg.setStyle("-fx-font-size: 25px");

		Label name = new Label();
		name.setLayoutX(270);
		name.setLayoutY(300);
		name.setText(contList.get(contIndex).getName());
		name.setStyle("-fx-font-size: 25px; -fx-underline: true; -fx-text-fill: gold; -fx-background-color: #2F2F2F");

		Button btnNext = new Button();
		btnNext.setLayoutX(500);
		btnNext.setLayoutY(350);
		btnNext.setText("Begin test!");
		btnNext.setStyle("-fx-text-fill: red;");
		btnNext.setOnAction(e -> {
			this.hide();
			boolean begin = true; // for timer purpose
			testForm = new TestForm(contList, contIndex, quesList, begin);
			// starts test
			testForm.showStage();
		});

		Button btnBack = new Button();
		btnBack.setLayoutX(25);
		btnBack.setLayoutY(350);
		btnBack.setText("This is not me(?)");
		btnBack.setStyle("-fx-text-fill: blue;");
		btnBack.setOnAction(e -> {
			// goes back to login form
			login = new Login(contList, quesList);
			this.hide();
			login.show();
		});

		Pane p1 = new Pane();
		p1.getChildren().addAll(welcMsg, name);
		p1.getChildren().addAll(btnBack, btnNext);
		p1.getChildren().addAll(img_1, img_2, img_3);

		Scene myScene = new Scene(p1, 600, 400);
		this.setTitle("Welcome");
		this.setScene(myScene);
	}

	public void showStage() {
		this.show();
	}

}
