package uet.oop.bomberman.core.scenes.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Clocks extends Label {
    public static final int DEFAULT_TIME = 300;
    private int time;
    private Timeline timeline;
    private boolean done;

    public Clocks() {
        time = DEFAULT_TIME;
        setTime(DEFAULT_TIME);
        pause();
    }

    public Clocks(int time, Color color) {
        setTime(time, color);
    }

    public void pause() {
        timeline.pause();
    }

    public void play() {
        if (timeline != null) {
            timeline.play();
        }
    }

    public int getTime() {
        return time;
    }

    /**
     * Set time and play
     * @param time time in second to count down
     */
    public void setTime(int time) {
        if (this.time > time|| done || time == DEFAULT_TIME) {
            System.out.println(time);
            done = false;

            this.time = time;
            setText(getClockString());
            setFont(Font.font("Verdana", FontWeight.BOLD, 24));
            setTextFill(Color.WHITE);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), this);
            scaleTransition.setToX(1.5);
            scaleTransition.setToY(1.5);
            scaleTransition.setCycleCount(6);
            scaleTransition.setAutoReverse(true);

            if (timeline != null) {
                timeline.stop();
            }
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                this.time--;
                setText(getClockString());
                if (this.time < 10) {
                    setTextFill(Color.RED);
                    if (this.time < 4 && scaleTransition.getStatus() != Animation.Status.RUNNING) {
                        scaleTransition.play();
                    }
                }
            }));

            timeline.setCycleCount(time);
            timeline.setOnFinished(event -> done = true);
        }
    }

    /**
     *
     * @param time time in second.
     * @param color color of label
     */
    private void setTime(int time, Color color) {
        this.time = time;
        done = false;

        setText(String.format("%d", time));
        setTextFill(color);

        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            this.time -= 1;
            setText(String.format("%d", this.time));
        }));

        timeline.setCycleCount(time);
        timeline.setOnFinished(event -> done = true);
        timeline.play();
    }

    public boolean isDone() {
        return done;
    }

    private String getClockString() {
        long min = TimeUnit.SECONDS.toMinutes(time);
        long sec = time - (min * 60);
        return format(min) + ":" + format(sec);
    }

    private String format(long num) {
        if (num < 10) {
            return "0" + num;
        }
        return "" + num;
    }
}
