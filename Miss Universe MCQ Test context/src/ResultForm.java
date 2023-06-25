import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ResultForm extends Stage {

	private int attemptedQues = 0; // total number of attmepted questions
	private int correctQues = 0; // total number of questions correct
	private int selectCont = -1; // selected contestant
	private boolean attempt = false;
	private String arr[] = new String[25];

	private Analysis statisticsForm; 

	public ResultForm(LinkedList<Contestant> cL, LinkedList<Question> qL, int totCont) {
		this.setTitle("Result");

		Font font = Font.font("verdana", /* FontWeight.BOLD, */ FontPosture.REGULAR, 25);
		Label nameLabel = new Label("Contestant Name:");
		nameLabel.setLayoutX(30);
		nameLabel.setLayoutY(20);
		nameLabel.setFont(font);

		// dropdown for contestant name
		ComboBox<String> contestantDropDown = new ComboBox<String>();
		contestantDropDown.setPromptText("Select contestant");
		ObservableList<String> contestantDropDownList = contestantDropDown.getItems();
		for (int i = 0; i < totCont; ++i) {
			contestantDropDownList.add(cL.get(i).getName());
		}
		contestantDropDown.setLayoutX(280);
		contestantDropDown.setLayoutY(20);
		contestantDropDown.setPrefSize(250, 15);
		contestantDropDown.setStyle("-fx-font: 20px \"Verdana\";");

		Label warning = new Label();
		updateWarning(cL, warning);
		warning.setLayoutX(30);
		warning.setLayoutY(730);
		GridPane answers = new GridPane();
		answers.setLayoutX(30);
		answers.setLayoutY(300);
		answers.setMinSize(1100, 380);
		answers.setPadding(new Insets(5, 5, 5, 5));
		answers.setStyle("-fx-background-color: yellow;");
		answers.setVgap(50);
		answers.setHgap(80);
		Label q1 = new Label("Q1");
		Label a1 = new Label(" ");
		q1.setFont(font);
		a1.setFont(font);
		Label q2 = new Label("Q2");
		Label a2 = new Label(" ");
		q2.setFont(font);
		a2.setFont(font);
		Label q3 = new Label("Q3");
		Label a3 = new Label(" ");
		q3.setFont(font);
		a3.setFont(font);
		Label q4 = new Label("Q4");
		Label a4 = new Label(" ");
		q4.setFont(font);
		a4.setFont(font);
		Label q5 = new Label("Q5");
		Label a5 = new Label(" ");
		q5.setFont(font);
		a5.setFont(font);

		Label q6 = new Label("Q6");
		Label a6 = new Label(" ");
		q6.setFont(font);
		a6.setFont(font);
		Label q7 = new Label("Q7");
		Label a7 = new Label(" ");
		q7.setFont(font);
		a7.setFont(font);
		Label q8 = new Label("Q8");
		Label a8 = new Label(" ");
		q8.setFont(font);
		a8.setFont(font);
		Label q9 = new Label("Q9");
		Label a9 = new Label(" ");
		q9.setFont(font);
		a9.setFont(font);
		Label q10 = new Label("Q10");
		Label a10 = new Label(" ");
		q10.setFont(font);
		a10.setFont(font);

		Label q11 = new Label("Q11");
		Label a11 = new Label(" ");
		q11.setFont(font);
		a11.setFont(font);
		Label q12 = new Label("Q12");
		Label a12 = new Label(" ");
		q12.setFont(font);
		a12.setFont(font);
		Label q13 = new Label("Q13");
		Label a13 = new Label(" ");
		q13.setFont(font);
		a13.setFont(font);
		Label q14 = new Label("Q14");
		Label a14 = new Label(" ");
		q14.setFont(font);
		a14.setFont(font);
		Label q15 = new Label("Q15");
		Label a15 = new Label(" ");
		q15.setFont(font);
		a15.setFont(font);

		Label q16 = new Label("Q16");
		Label a16 = new Label(" ");
		q16.setFont(font);
		a16.setFont(font);
		Label q17 = new Label("Q17");
		Label a17 = new Label(" ");
		q17.setFont(font);
		a17.setFont(font);
		Label q18 = new Label("Q18");
		Label a18 = new Label(" ");
		q18.setFont(font);
		a18.setFont(font);
		Label q19 = new Label("Q19");
		Label a19 = new Label(" ");
		q19.setFont(font);
		a19.setFont(font);
		Label q20 = new Label("Q20");
		Label a20 = new Label(" ");
		q20.setFont(font);
		a20.setFont(font);

		Label q21 = new Label("Q21");
		Label a21 = new Label(" ");
		q21.setFont(font);
		a21.setFont(font);
		Label q22 = new Label("Q22");
		Label a22 = new Label(" ");
		q22.setFont(font);
		a22.setFont(font);
		Label q23 = new Label("Q23");
		Label a23 = new Label(" ");
		q23.setFont(font);
		a23.setFont(font);
		Label q24 = new Label("Q24");
		Label a24 = new Label(" ");
		q24.setFont(font);
		a24.setFont(font);
		Label q25 = new Label("Q25");
		Label a25 = new Label(" ");
		q25.setFont(font);
		a25.setFont(font);

		answers.add(q1, 0, 0);
		answers.add(a1, 1, 0);
		answers.add(q2, 0, 1);
		answers.add(a2, 1, 1);
		answers.add(q3, 0, 2);
		answers.add(a3, 1, 2);
		answers.add(q4, 0, 3);
		answers.add(a4, 1, 3);
		answers.add(q5, 0, 4);
		answers.add(a5, 1, 4);

		answers.add(q6, 2, 0);
		answers.add(a6, 3, 0);
		answers.add(q7, 2, 1);
		answers.add(a7, 3, 1);
		answers.add(q8, 2, 2);
		answers.add(a8, 3, 2);
		answers.add(q9, 2, 3);
		answers.add(a9, 3, 3);
		answers.add(q10, 2, 4);
		answers.add(a10, 3, 4);

		answers.add(q11, 4, 0);
		answers.add(a11, 5, 0);
		answers.add(q12, 4, 1);
		answers.add(a12, 5, 1);
		answers.add(q13, 4, 2);
		answers.add(a13, 5, 2);
		answers.add(q14, 4, 3);
		answers.add(a14, 5, 3);
		answers.add(q15, 4, 4);
		answers.add(a15, 5, 4);

		answers.add(q16, 6, 0);
		answers.add(a16, 7, 0);
		answers.add(q17, 6, 1);
		answers.add(a17, 7, 1);
		answers.add(q18, 6, 2);
		answers.add(a18, 7, 2);
		answers.add(q19, 6, 3);
		answers.add(a19, 7, 3);
		answers.add(q20, 6, 4);
		answers.add(a20, 7, 4);

		answers.add(q21, 8, 0);
		answers.add(a21, 9, 0);
		answers.add(q22, 8, 1);
		answers.add(a22, 9, 1);
		answers.add(q23, 8, 2);
		answers.add(a23, 9, 2);
		answers.add(q24, 8, 3);
		answers.add(a24, 9, 3);
		answers.add(q25, 8, 4);
		answers.add(a25, 9, 4);

		Label attemptLabel = new Label("Questions Attempted:");
		attemptLabel.setLayoutX(30);
		attemptLabel.setLayoutY(100);
		attemptLabel.setFont(font);

		Label attemptedNum = new Label(attemptedQues + "/25");
		attemptedNum.setLayoutX(320);
		attemptedNum.setLayoutY(100);
		attemptedNum.setFont(font);

		Label correctLabel = new Label("Questions Answered Correctly:");
		correctLabel.setLayoutX(30);
		correctLabel.setLayoutY(180);
		correctLabel.setFont(font);

		Label correctPercentage = new Label("" + correctQues + "/25 (" + calculateCorrectPercentage(correctQues) + "%)");
		correctPercentage.setLayoutX(430);
		correctPercentage.setLayoutY(180);
		correctPercentage.setStyle("-fx-font: 25px \"Verdana\"; -fx-text-fill: red");

		Button checkResultBtn = new Button("Check");
		checkResultBtn.setLayoutX(550);
		checkResultBtn.setLayoutY(20);
		checkResultBtn.setStyle("-fx-font: 20px \"Verdana\";");
		checkResultBtn.setPrefHeight(15);
		checkResultBtn.setOnAction(e -> {
			selectCont = getContestantIndex(cL, totCont, contestantDropDown.getValue()); // get contestant index
			FileSys.readAnswerFile(cL.get(selectCont).getAnswer(), selectCont);
			arr = cL.get(selectCont).getAnswer();
			int noTry = 0;
			for (int i = 0; i < 25; ++i) {
				if (arr[i].equalsIgnoreCase("X")) 
					noTry++;
			}
			attemptedQues = 25 - noTry;
			if (noTry == 25) {
				attempt = false;
			} else attempt = true;
			updateWarning(cL, warning); // update warning
			updateAnswers(a1, cL, qL, selectCont, 0);
			updateAnswers(a2, cL, qL, selectCont, 1);
			updateAnswers(a3, cL, qL, selectCont, 2);
			updateAnswers(a4, cL, qL, selectCont, 3);
			updateAnswers(a5, cL, qL, selectCont, 4);
			updateAnswers(a6, cL, qL, selectCont, 5);
			updateAnswers(a7, cL, qL, selectCont, 6);
			updateAnswers(a8, cL, qL, selectCont, 7);
			updateAnswers(a9, cL, qL, selectCont, 8);
			updateAnswers(a10, cL, qL, selectCont, 9);
			updateAnswers(a11, cL, qL, selectCont, 10);
			updateAnswers(a12, cL, qL, selectCont, 11);
			updateAnswers(a13, cL, qL, selectCont, 12);
			updateAnswers(a14, cL, qL, selectCont, 13);
			updateAnswers(a15, cL, qL, selectCont, 14);
			updateAnswers(a16, cL, qL, selectCont, 15);
			updateAnswers(a17, cL, qL, selectCont, 16);
			updateAnswers(a18, cL, qL, selectCont, 17);
			updateAnswers(a19, cL, qL, selectCont, 18);
			updateAnswers(a20, cL, qL, selectCont, 19);
			updateAnswers(a21, cL, qL, selectCont, 20);
			updateAnswers(a22, cL, qL, selectCont, 21);
			updateAnswers(a23, cL, qL, selectCont, 22);
			updateAnswers(a24, cL, qL, selectCont, 23);
			updateAnswers(a25, cL, qL, selectCont, 24);
			attemptedNum.setText(attemptedQues + "/25");
			correctQues = getCorrect(cL, qL, selectCont);
			correctPercentage.setText("" + correctQues + "/25 (" + calculateCorrectPercentage(correctQues) + "%)");
			updatePass(correctPercentage, correctQues);
		});

		Button statsBtn = new Button("View Statistics");
		statsBtn.setLayoutX(1000);
		statsBtn.setLayoutY(730);
		statsBtn.setOnAction(e -> {
			this.hide();
			statisticsForm.showStage();
		});

		Pane resultPane = new Pane();
		resultPane.getChildren().add(nameLabel);
		resultPane.getChildren().add(contestantDropDown);
		resultPane.getChildren().add(checkResultBtn);
		resultPane.getChildren().add(attemptLabel);
		resultPane.getChildren().add(attemptedNum);
		resultPane.getChildren().add(correctLabel);
		resultPane.getChildren().add(correctPercentage);
		resultPane.getChildren().add(answers);
		resultPane.getChildren().add(warning);
		resultPane.getChildren().add(statsBtn);

		this.setScene(new Scene(resultPane, MUKT.SWIDTH, MUKT.SHEIGHT));
		statisticsForm = new Analysis(cL, qL);
	}

	public void showStage() {
		this.show();
	}

	public void updateWarning(LinkedList<Contestant> cL, Label w) {
		if (selectCont == -1) {
			w.setText("Please select a contestant to view result");
			w.setStyle("-fx-text-fill: blue");
		} else {
			if (!attempt) {
				w.setText(cL.get(selectCont).getName() + " has not attempted the test");
				w.setStyle("-fx-text-fill: red");
			} else if (attempt) {
				w.setText("Result shown above");
				w.setStyle("-fx-text-fill: blue");
			}
		}
	}

	public int getContestantIndex(LinkedList<Contestant> cL, int totCont, String name) {
		for (int i = 0; i < totCont; ++i) {
			if (cL.get(i).getName() == name)
				return i;
		}
		return -1;
	}

	public void updateAnswers(Label label, LinkedList<Contestant> cL, LinkedList<Question> qL, int contIndex, int answerIndex) {
		label.setText(cL.get(contIndex).getAnswer()[answerIndex]);
		boolean correct = checkCorrect(cL, qL, contIndex, answerIndex);
		label.setStyle(updateAnswerFont(label, correct));
	}

	public boolean checkCorrect(LinkedList<Contestant> cL, LinkedList<Question> qL, int contIndex, int answerIndex) {
		String contestantAnswer;
		char correctAnswer;
		contestantAnswer = cL.get(contIndex).getAnswer()[answerIndex];
		correctAnswer = qL.get(answerIndex).getAnswer();
		if (contestantAnswer.charAt(0) == correctAnswer) return true;
		else return false;
	}

	public String updateAnswerFont(Label l, boolean correct) {
		if (correct) return ("-fx-font: 25px \"Verdana\"; -fx-text-fill: green");
		else return ("-fx-font: 25px \"Verdana\"; -fx-text-fill: red");
	}

	public int getCorrect(LinkedList<Contestant> cL, LinkedList<Question> qL, int index) {
		int correct = 0;
		boolean check;
		for (int i = 0; i < 25; ++i) {
			check = checkCorrect(cL, qL, index, i);
			if (check) correct++;
		}
		return correct;
	}

	public boolean checkPass(int n) {
		return calculateCorrectPercentage(n) > 50 ? true : false;
	}

	public void updatePass(Label l, int n) {
		boolean pass = checkPass(n);
		if (pass)
			l.setStyle("-fx-font: 25px \"Verdana\"; -fx-text-fill: green");
		else
			l.setStyle("-fx-font: 25px \"Verdana\"; -fx-text-fill: red");
	}

	public double calculateCorrectPercentage(int correctAnswers) {
		double percentage = (correctAnswers/25.00) * 100;
		String format = String.format("%.2f", percentage);
		return Double.parseDouble(format);
	}

}