import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.AtomicInteger;

// import javafx.beans.property.IntegerProperty;

public class Reminder {

	private int time = 0;
	private String timeLabel;
	private int minutes;
	private int seconds;
	private Timeline animation;
	// private ObjectProperty<java.time.Duration> timeLeft;
	// Thread myt;
	// private final int i= 15;
	// private final DateTimeFormatter MM_SS = DateTimeFormatter.ofPattern("mm:ss");


	// private ScheduledExecutorService executor = null;
	// private AtomicInteger atomicInteger = new AtomicInteger();

	// private Duration countdownDuration = Duration.ofMinutes(5);

	public Reminder(int t) {
		time = t;
		minutes = time / 60;
		seconds = time % 60;
		// countdownDuration = Duration.ofMinutes(t);
		// timer = new Timeline();
		// timer.getKeyFrames().add(new KeyFrame(Duration.ofSeconds(1000), ae -> updateTimer()));
		// timer.setCycleCount(Timeline.INDEFINITE);

		// timeLeft = new SimpleObjectProperty<>();
	}

	public String getTime() {
		return timeLabel;
	}

	/*
	public void initialize() {
		timeLabel.textProperty()
				.bind(Bindings.createStringBinding(() -> getTimeStringFromDuration(timeLeft.get()), timeLeft));
	}

	@FXML
	private void startTimer(ActionEvent ae) {
		timeLeft.set(Duration.ofMinutes(5)); // For example timer of 5 minutes
		timer.playFromStart();
	}

	private void updateTimer() {
		timeLeft.set(timeLeft.get().minusSeconds(1));
	}

	private static String getTimeStringFromDuration(Duration duration) {
		// Do the conversion here...
	}
	*/

	/*
	 * public void stopTimer() { if (executor != null) { executor.shutdownNow(); } }
	 * 
	 * public void countdown() { Platform.runLater(() -> timeLabel = "");
	 * atomicInteger.set(time);
	 * 
	 * setCountDown(LocalTime.ofSecondOfDay(atomicInteger.get())); executor =
	 * Executors.newScheduledThreadPool(1);
	 * 
	 * Runnable r = () -> { int j = atomicInteger.decrementAndGet(); if (j < 1) {
	 * executor.shutdown(); Platform.runLater(() -> { timeLabel = ""; });
	 * setCountDown(LocalTime.ofSecondOfDay(0)); } else {
	 * setCountDown(LocalTime.ofSecondOfDay(j)); } };
	 * executor.scheduleAtFixedRate(r, 1, 1, TimeUnit.SECONDS);
	 * System.out.println(timeLabel); }
	 * 
	 * public void setCountDown(LocalTime lt) { Platform.runLater(() -> timeLabel =
	 * lt.format(MM_SS)); }
	 */

	/*
	 * public void start() { myt.start(); }
	 * 
	 * public void joinThread() { try { myt.join(); } catch (Exception e) { } }
	 */

	public String initTime() {
		System.out.println("Started timer");
		time = time * 60; // Convert 5 min to seconds
		long delay = time * 1000;
		do {
			timeLabel = minutes + ":" + seconds;
			// text.setText(timeLabel);
			playSound(minutes, seconds);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time = time - 1;
			delay = delay - 1000;
		} while (delay != 0);
		timeLabel = "Time's Up!";
		return timeLabel;
	}

	public void playSound(int m, int s) {
		boolean whole = s == 0 ? true : false;
		if (m == 5 && whole) {
			playSound("data/sound/startTest.wav");
		} else if (m == 4 && whole) {
			playSound("data/sound/4min.wav");
		} else if (m == 3 && whole) {
			playSound("data/sound/3min.wav");
		} else if (m == 2 && whole) {
			playSound("data/sound/2min.wav");
		} else if (m == 1 && whole) {
			playSound("data/sound/1min.wav");
		}
	}

	public static void playSound(String f) {
		File soundFile = new File(f).getAbsoluteFile();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			Clip myClip = AudioSystem.getClip();
			myClip.open(ais);
			FloatControl gainControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // reduces volume by 10db
			myClip.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}