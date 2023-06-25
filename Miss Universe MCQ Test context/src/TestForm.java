import java.io.File;
// import java.io.FileNotFoundException;
import java.util.LinkedList;
// import java.util.Scanner;
// import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestForm extends Stage {

	private boolean begin = false;
	private int start = 0;
	private int totQues = 0;
	private int activeQ = 1;
	private String contAns[];
	private Label labQuesNo, labQues, labTimer, labName;
	private Label labA, labB, labC, labD;
	private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
	private Button btnPrev, btnNext, btnDone;
	private ToggleGroup grpChoices;
	private ImageView imgQuesB, imgFlag;
	private ResultForm resultForm;
	private Reminder timer;
	private Pane pMain;
	private Pane p1;
	private Scene mainScene;

	public TestForm(LinkedList<Contestant> contList, int contIndex, LinkedList<Question> quesList, boolean begin) {
		this.setTitle("M U K T");
		totQues = FileSys.getTotQues();
		contAns = new String[totQues];
		initContAns(totQues);

		// Timer
		labTimer = new Label();
		labTimer.setLayoutX(350);
		labTimer.setLayoutY(80);
		labTimer.setStyle("-fx-pref-width: 40px;-fx-border-color:red;-fx-font-size: 12pt");

		if (begin && start == 0) {
			start = 1;
			timer = new Reminder(5);
			// timer.initTime();
		}

		// Contestant Name Label
		Label labForName = new Label("Contestant Name: ");
		labForName.setLayoutX(25);
		labForName.setLayoutY(25);
		labForName.setStyle("-fx-font-size: 14pt;-fx-font-weight:bold;");

		// Actual Name
		labName = new Label(contList.get(contIndex).getName());
		labName.setLayoutX(25);
		labName.setLayoutY(50);
		labName.setStyle("-fx-font-size: 14pt;-fx-font-weight:bold;");

		// Contestant's Flag Image
		imgFlag = new ImageView();
		imgFlag.setImage(new Image(FileSys.fetchCountryImage(contIndex)));
		imgFlag.setLayoutX(450);
		imgFlag.setLayoutY(25);
		imgFlag.setFitHeight(80);
		imgFlag.setFitWidth(110);

		// separator
		Label labNum2 = new Label("---------------------------------------------------------------------------- ");
		labNum2.setLayoutX(10);
		labNum2.setLayoutY(50);

		// Label for Question No.
		labQuesNo = new Label("");
		labQuesNo.setLayoutX(30);
		labQuesNo.setLayoutY(80);
		labQuesNo.setStyle("-fx-font-size: 12pt;-fx-pref-width: 120px;-fx-border-color:black;");

		// Label for Actual Question
		labQues = new Label("");
		labQues.setLayoutX(30);
		labQues.setLayoutY(130);
		labQues.setStyle("-fx-font-size: 12pt;-fx-font-weight:bold;");

		// Layout for Image for Type B Questions
		imgQuesB = new ImageView();
		imgQuesB.setLayoutX(180);
		imgQuesB.setLayoutY(120);
		imgQuesB.setFitHeight(150);
		imgQuesB.setFitWidth(150);


		// Choice A
		labA = new Label("A");
		labA.setLayoutX(35);
		radChoice1 = new RadioButton("");
		radChoice1.setLayoutX(55);

		// Choice B
		labB = new Label("B");
		labB.setLayoutX(35);
		radChoice2 = new RadioButton("");
		radChoice2.setLayoutX(55);

		// Choice C
		labC = new Label("C");
		labC.setLayoutX(35);
		radChoice3 = new RadioButton("");
		radChoice3.setLayoutX(55);

		// Choice D
		labD = new Label("D");
		labD.setLayoutX(35);
		radChoice4 = new RadioButton("");
		radChoice4.setLayoutX(55);

		// Toggle Group
		grpChoices = new ToggleGroup();
		radChoice1.setToggleGroup(grpChoices);
		radChoice2.setToggleGroup(grpChoices);
		radChoice3.setToggleGroup(grpChoices);
		radChoice4.setToggleGroup(grpChoices);

		// Pane that changes Layout
		p1 = new Pane();
		p1.setLayoutX(10);
		p1.setLayoutY(60);

		// Actions in the Pane
		p1.getChildren().add(labA);
		p1.getChildren().add(radChoice1);
		p1.getChildren().add(labB);
		p1.getChildren().add(radChoice2);
		p1.getChildren().add(labC);
		p1.getChildren().add(radChoice3);
		p1.getChildren().add(labD);
		p1.getChildren().add(radChoice4);
		p1.getChildren().add(imgQuesB);

		// Action for the Choices ABCD
		radChoice1.setOnAction(e -> {
			quesList.get(activeQ - 1).setSelected(0, true);
			quesList.get(activeQ - 1).setSelected(1, false);
			quesList.get(activeQ - 1).setSelected(2, false);
			quesList.get(activeQ - 1).setSelected(3, false);
			// System.out.println(quesList.get(activeQ-1).getSelected(0));
		});
		radChoice2.setOnAction(e -> {
			quesList.get(activeQ - 1).setSelected(0, false);
			quesList.get(activeQ - 1).setSelected(1, true);
			quesList.get(activeQ - 1).setSelected(2, false);
			quesList.get(activeQ - 1).setSelected(3, false);
			// System.out.println(quesList.get(activeQ-1).getSelected(1));
		});
		radChoice3.setOnAction(e -> {
			quesList.get(activeQ - 1).setSelected(0, false);
			quesList.get(activeQ - 1).setSelected(1, false);
			quesList.get(activeQ - 1).setSelected(2, true);
			quesList.get(activeQ - 1).setSelected(3, false);
			// System.out.println(quesList.get(activeQ-1).getSelected(2));
		});
		radChoice4.setOnAction(e -> {
			quesList.get(activeQ - 1).setSelected(0, false);
			quesList.get(activeQ - 1).setSelected(1, false);
			quesList.get(activeQ - 1).setSelected(2, false);
			quesList.get(activeQ - 1).setSelected(3, true);
			// System.out.println(quesList.get(activeQ-1).getSelected(3));
		});

		// Buttons
		btnPrev = new Button("Previous");
		btnPrev.setLayoutX(30);
		btnPrev.setLayoutY(650);

		btnNext = new Button("Next Question");
		btnNext.setLayoutX(250);
		btnNext.setLayoutY(650);

		btnDone = new Button("Finish!");
		btnDone.setLayoutX(500);
		btnDone.setLayoutY(650);
		btnDone.setStyle("-fx-border-color:red;-fx-font-weight:bold;");

		// Disable Button
		if (totQues == 1)
			btnNext.setDisable(true);
		if (activeQ == 1)
			btnPrev.setDisable(true);

		// Make buttons functionable
		btnNext.setOnAction(e -> {
			setContAns(quesList, activeQ - 1);
			activeQ++;
			btnPrev.setDisable(false);
			if (activeQ == totQues)
				btnNext.setDisable(true);
			reloadQues(quesList);
		});
		btnPrev.setOnAction(e -> {
			setContAns(quesList, activeQ - 1);
			activeQ--;
			btnNext.setDisable(false);
			if (activeQ == 1)
				btnPrev.setDisable(true);
			reloadQues(quesList);
		});
		btnDone.setOnAction(e -> {
			setContAns(quesList, activeQ - 1);
			submitTest(this, contList, quesList, contIndex);
		});

		// Configure Main unchanging Pane
		pMain = new Pane();
		pMain.getChildren().add(labForName);
		pMain.getChildren().add(labName);
		pMain.getChildren().add(imgFlag);
		pMain.getChildren().add(labQuesNo);
		pMain.getChildren().add(labQues);
		pMain.getChildren().add(labTimer);
		pMain.getChildren().add(p1);
		pMain.getChildren().add(btnNext);
		pMain.getChildren().add(btnPrev);
		pMain.getChildren().add(btnDone);

		// Full Scene
		mainScene = new Scene(pMain, 600, 700);
		this.setScene(mainScene);
		reloadQues(quesList);

	}

	public void showStage() {
		this.show();
	}

	// Reload Question method
	public void reloadQues(LinkedList<Question> quesList) {
		labQuesNo.setText("Question " + Integer.toString(activeQ) + "/25");

		labQues.setText(quesList.get(activeQ - 1).getTheQues());
		radChoice1.setText(quesList.get(activeQ - 1).getChoice(0));
		radChoice2.setText(quesList.get(activeQ - 1).getChoice(1));
		radChoice3.setText(quesList.get(activeQ - 1).getChoice(2));
		radChoice4.setText(quesList.get(activeQ - 1).getChoice(3));

		imgQuesB.setImage(null);
		radChoice1.setGraphic(null);
		radChoice2.setGraphic(null);
		radChoice3.setGraphic(null);
		radChoice4.setGraphic(null);

		if (quesList.get(activeQ - 1).getType() == 1) {

			// Layout for Type A Questions

			labA.setLayoutY(160);
			radChoice1.setLayoutY(160);
			labB.setLayoutY(210);
			radChoice2.setLayoutY(210);
			labC.setLayoutY(260);
			radChoice3.setLayoutY(260);
			labD.setLayoutY(310);
			radChoice4.setLayoutY(310);

			// Layout for Type B Questions

		} else if (quesList.get(activeQ - 1).getType() == 2) {

			File pFile = new File("data/images/" + quesList.get(activeQ - 1).getQuesPic());
			Image img = new Image(pFile.toURI().toString());
			imgQuesB.setImage(img);

			labA.setLayoutY(335);
			radChoice1.setLayoutY(335);
			labB.setLayoutY(385);
			radChoice2.setLayoutY(385);
			labC.setLayoutY(435);
			radChoice3.setLayoutY(435);
			labD.setLayoutY(495);
			radChoice4.setLayoutY(495);

			// layout for type C Questions

		} else if (quesList.get(activeQ - 1).getType() == 3) {
			// Empty it
			radChoice1.setText(null);
			radChoice2.setText(null);
			radChoice3.setText(null);
			radChoice4.setText(null);

			// Get and display question's image
			File pFile = new File("data/images/" + quesList.get(activeQ - 1).getChoice(0));
			ImageView img1 = new ImageView(pFile.toURI().toString());
			img1.setFitHeight(80);
			img1.setFitWidth(100);
			radChoice1.setGraphic(img1);

			pFile = new File("data/images/" + quesList.get(activeQ - 1).getChoice(1));
			ImageView img2 = new ImageView(pFile.toURI().toString());
			img2.setFitHeight(80);
			img2.setFitWidth(100);
			radChoice2.setGraphic(img2);

			pFile = new File("data/images/" + quesList.get(activeQ - 1).getChoice(2));
			ImageView img3 = new ImageView(pFile.toURI().toString());
			img3.setFitHeight(80);
			img3.setFitWidth(100);
			radChoice3.setGraphic(img3);

			pFile = new File("data/images/" + quesList.get(activeQ - 1).getChoice(3));
			ImageView img4 = new ImageView(pFile.toURI().toString());
			img4.setFitHeight(80);
			img4.setFitWidth(100);
			radChoice4.setGraphic(img4);

			// Change radio buttons layout
			labA.setLayoutY(160);
			radChoice1.setLayoutY(160);
			labB.setLayoutY(250);
			radChoice2.setLayoutY(250);
			labC.setLayoutY(340);
			radChoice3.setLayoutY(340);
			labD.setLayoutY(430);
			radChoice4.setLayoutY(430);
		}

		// receive the answers
		radChoice1.setSelected(quesList.get(activeQ - 1).getSelected(0));
		radChoice2.setSelected(quesList.get(activeQ - 1).getSelected(1));
		radChoice3.setSelected(quesList.get(activeQ - 1).getSelected(2));
		radChoice4.setSelected(quesList.get(activeQ - 1).getSelected(3));
	}

	public void initContAns(int n) {
		for (int i = 0; i < n; ++i) {
			contAns[i] = "X";
		}
	}

	public void setContAns(LinkedList<Question> quesList, int i) {
		if (quesList.get(i).getSelected(0)) {
			contAns[i] = "A";
		} else if (quesList.get(i).getSelected(1)) {
			contAns[i] = "B";
		} else if (quesList.get(i).getSelected(2)) {
			contAns[i] = "C";
		} else if (quesList.get(i).getSelected(3)) {
			contAns[i] = "D";
		} else {
			contAns[i] = "X";
		}
		System.out.println(quesList.get(i).getSelected(0));
		System.out.println(quesList.get(i).getSelected(1));
		System.out.println(quesList.get(i).getSelected(2));
		System.out.println(quesList.get(i).getSelected(3));
		System.out.println(contAns[i]);
	}

	// submit test method
	public void submitTest(Stage mainStage, LinkedList<Contestant> contL, LinkedList<Question> quesL, int contIndex) {
		mainStage.hide();
		FileSys.writeAnswerFile(contAns, contIndex);
		resultForm = new ResultForm(contL, quesL, 5);
		resultForm.showStage();
	}
}