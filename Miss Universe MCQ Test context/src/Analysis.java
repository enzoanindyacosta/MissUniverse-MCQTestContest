import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.LinkedList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Analysis extends Stage {
	private ResultForm resultForm;
	private final int MAX_LENGTH = 900;
	private double score[] = new double[5];
	private String answer[][] = new String[5][25];

	public Analysis(LinkedList<Contestant> cL, LinkedList<Question> qL) {
		this.setTitle("Statistics");
		Font nameFont = Font.font("verdana", /* FontWeight.BOLD, */ FontPosture.REGULAR, 20);
		Font tagFont = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);
		loadScore(cL, qL);

		Label scoreLabel = new Label("Score");
		scoreLabel.setLayoutX(20);
		scoreLabel.setLayoutY(10);
		scoreLabel.setFont(tagFont);

		Rectangle rect1 = new Rectangle(20, 50, rectangleWidth(score[0]), 50);
		rect1.setFill(Color.TRANSPARENT);
		rect1.setStroke(Color.LIGHTBLUE);
		rect1.setStrokeWidth(5);
		Label name1 = new Label(cL.get(0).getName());
		name1.setLayoutX(30);
		name1.setLayoutY(60);
		name1.setFont(nameFont);

		Rectangle rect2 = new Rectangle(20, 110, rectangleWidth(score[1]), 50);
		rect2.setFill(Color.TRANSPARENT);
		rect2.setStroke(Color.MAROON);
		rect2.setStrokeWidth(5);
		Label name2 = new Label(cL.get(1).getName());
		name2.setLayoutX(30);
		name2.setLayoutY(120);
		name2.setFont(nameFont);

		Rectangle rect3 = new Rectangle(20, 170, rectangleWidth(score[2]), 50);
		rect3.setFill(Color.TRANSPARENT);
		rect3.setStroke(Color.LIGHTGREEN);
		rect3.setStrokeWidth(5);
		Label name3 = new Label(cL.get(2).getName());
		name3.setLayoutX(30);
		name3.setLayoutY(180);
		name3.setFont(nameFont);

		Rectangle rect4 = new Rectangle(20, 230, rectangleWidth(score[3]), 50);
		rect4.setFill(Color.TRANSPARENT);
		rect4.setStroke(Color.YELLOW);
		rect4.setStrokeWidth(5);
		Label name4 = new Label(cL.get(3).getName());
		name4.setLayoutX(30);
		name4.setLayoutY(240);
		name4.setFont(nameFont);

		Rectangle rect5 = new Rectangle(20, 290, rectangleWidth(score[4]), 50);
		rect5.setFill(Color.TRANSPARENT);
		rect5.setStroke(Color.PURPLE);
		rect5.setStrokeWidth(5);
		Label name5 = new Label(cL.get(4).getName());
		name5.setLayoutX(30);
		name5.setLayoutY(300);
		name5.setFont(nameFont);

		Label score1 = new Label(Double.toString(score[0]));
		score1.setLayoutX(rectangleWidth(score[0]) + 100);
		score1.setLayoutY(60);
		score1.setFont(nameFont);
		Label score2 = new Label(Double.toString(score[1]));
		score2.setLayoutX(rectangleWidth(score[1]) + 100);
		score2.setLayoutY(120);
		score2.setFont(nameFont);
		Label score3 = new Label(Double.toString(score[2]));
		score3.setLayoutX(rectangleWidth(score[2]) + 100);
		score3.setLayoutY(180);
		score3.setFont(nameFont);
		Label score4 = new Label(Double.toString(score[3]));
		score4.setLayoutX(rectangleWidth(score[3]) + 100);
		score4.setLayoutY(240);
		score4.setFont(nameFont);
		Label score5 = new Label(Double.toString(score[4]));
		score5.setLayoutX(rectangleWidth(score[4]) + 100);
		score5.setLayoutY(300);
		score5.setFont(nameFont);

		// maximum, minimum, mean, mode, median and standard deviation.
		Label maxLabel = new Label("Maximum : ");
		maxLabel.setLayoutX(30);
		maxLabel.setLayoutY(400);
		maxLabel.setFont(tagFont);
		Label maxText = new Label(findMaximum(cL));
		maxText.setLayoutX(300);
		maxText.setLayoutY(400);
		maxText.setFont(nameFont);

		Label minLabel = new Label("Minimum : ");
		minLabel.setLayoutX(30);
		minLabel.setLayoutY(450);
		minLabel.setFont(tagFont);
		Label minText = new Label(findMinimum(cL));
		minText.setLayoutX(300);
		minText.setLayoutY(450);
		minText.setFont(nameFont);

		Label meanLabel = new Label("Mean : ");
		meanLabel.setLayoutX(30);
		meanLabel.setLayoutY(500);
		meanLabel.setFont(tagFont);
		Label meanText = new Label(Double.toString(calculateMean()));
		meanText.setLayoutX(300);
		meanText.setLayoutY(500);
		meanText.setFont(nameFont);

		Label modeLabel = new Label("Mode : ");
		modeLabel.setLayoutX(30);
		modeLabel.setLayoutY(550);
		modeLabel.setFont(tagFont);
		Label modeText = new Label(Double.toString(calculateMode()));
		modeText.setLayoutX(300);
		modeText.setLayoutY(550);
		modeText.setFont(nameFont);

		Label medianLabel = new Label("Median : ");
		medianLabel.setLayoutX(30);
		medianLabel.setLayoutY(600);
		medianLabel.setFont(tagFont);
		Label medianText = new Label(Double.toString(findMedian()));
		medianText.setLayoutX(300);
		medianText.setLayoutY(600);
		medianText.setFont(nameFont);

		Label sigmaLabel = new Label("Standard Deviation : ");
		sigmaLabel.setLayoutX(30);
		sigmaLabel.setLayoutY(650);
		sigmaLabel.setFont(tagFont);
		Label sigmaText = new Label(Double.toString(calculateStandardDeviation()));
		sigmaText.setLayoutX(300);
		sigmaText.setLayoutY(650);
		sigmaText.setFont(nameFont);

		Button backBtn = new Button("Back");
		backBtn.setLayoutX(30);
		backBtn.setLayoutY(700);
		backBtn.setOnAction(e -> {
			this.hide();
			resultForm = new ResultForm(cL, qL, FileSys.getTotCont());
			resultForm.show();
		});

		Button exitBtn = new Button("Exit");
		exitBtn.setLayoutX(900);
		exitBtn.setLayoutY(700);
		exitBtn.setOnAction(e -> {
			this.close();
		});

		Pane statsPane = new Pane();
		statsPane.getChildren().add(scoreLabel);
		statsPane.getChildren().add(name1);
		statsPane.getChildren().add(name2);
		statsPane.getChildren().add(name3);
		statsPane.getChildren().add(name4);
		statsPane.getChildren().add(name5);
		statsPane.getChildren().add(rect1);
		statsPane.getChildren().add(rect2);
		statsPane.getChildren().add(rect3);
		statsPane.getChildren().add(rect4);
		statsPane.getChildren().add(rect5);
		statsPane.getChildren().add(score1);
		statsPane.getChildren().add(score2);
		statsPane.getChildren().add(score3);
		statsPane.getChildren().add(score4);
		statsPane.getChildren().add(score5);
		statsPane.getChildren().add(maxLabel);
		statsPane.getChildren().add(minLabel);
		statsPane.getChildren().add(meanLabel);
		statsPane.getChildren().add(modeLabel);
		statsPane.getChildren().add(medianLabel);
		statsPane.getChildren().add(sigmaLabel);
		statsPane.getChildren().add(maxText);
		statsPane.getChildren().add(minText);
		statsPane.getChildren().add(meanText);
		statsPane.getChildren().add(modeText);
		statsPane.getChildren().add(medianText);
		statsPane.getChildren().add(sigmaText);
		statsPane.getChildren().add(backBtn);
		statsPane.getChildren().add(exitBtn);

		this.setScene(new Scene(statsPane, 1000, 750));
	}

	public void showStage() {
		this.show();
	}

	// updates scores into a local array
	public void loadScore(LinkedList<Contestant> cL, LinkedList<Question> qL) { 
		for (int i = 0; i < 5; ++i) {
			FileSys.readAnswerFile(answer[i], i);
			for (int j = 0; j < 25; ++j) {
				String contAnswer = answer[i][j];
				char correctAnswer = qL.get(j).getAnswer();
				if (contAnswer.charAt(0) == correctAnswer) {
					score[i]++;
				}
			}
			double percentage = (score[i] / 25.00) * 100;
			String format = String.format("%.2f", percentage);
			score[i] = Double.parseDouble(format);
		}
	}

	// scaling the size of rectangle to the score
	public double rectangleWidth(double score) {
		return (score / 100) * MAX_LENGTH;
	}

	// max score
	public String findMaximum(LinkedList<Contestant> cL) { 
		double tmp = score[0];
		int index = 0;
		for (int i = 1; i < 5; ++i) {
			if (tmp < score[i]) {
				tmp = score[i];
				index = i;
			}
		}
		return score[index] + " ( " + cL.get(index).getName() + " )";
	}

	// min score 
	public String findMinimum(LinkedList<Contestant> cL) {
		double tmp = score[0];
		int index = 0;
		for (int i = 1; i < 5; ++i) {
			if (tmp > score[i]) {
				tmp = score[i];
				index = i;
			}
		}
		return score[index] + " ( " + cL.get(index).getName() + " )";
	}

	public double calculateMean() {
		double tmp = 0;
		for (int i = 0; i < 5; ++i) {
			tmp += score[i];
		}
		return tmp / 5;
	}

	public double calculateMode() {
		double mode = 0;
		double cur;
		for (int i = 0; i < 5; ++i) {
			cur = 0;
			for (int j = 0; j < 5; ++j) {
				if (score[i] == score[j])
					cur++;
			}
			if (cur > mode)
				mode = score[i];
		}
		return mode;
	}

	public double findMedian() {
		int position;
		int n = score.length;
		if (n % 2 == 1) {
			position = (n + 1) / 2;
		} else {
			position = ((n / 2) + ((n / 2) + 1)) / 2;
		}
		return score[position];
	}

	public double calculateStandardDeviation() { 
		double sigma;
		int n = score.length;
		double mean = calculateMean();
		double tmp = 0;
		for (int i = 0; i < n; ++i) {
			tmp += Math.pow((score[i] - mean), 2);
		}
		sigma = Math.sqrt(tmp / n);
		String format = String.format("%.3f", sigma);
		return Double.parseDouble(format);
	}

}