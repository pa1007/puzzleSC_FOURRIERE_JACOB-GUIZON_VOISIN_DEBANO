package dev.pa1007.game.draw;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Text;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StopwatchTimer extends TimerTask implements Serializable {

    private final     SimpleDateFormat     sdf = new SimpleDateFormat("mm:ss:S");
    private transient SimpleStringProperty min, sec, millis, sspTime;
    private long    time;
    private Text    clock;
    private boolean stop = true;
    private boolean timed;

    public StopwatchTimer() {
        min = new SimpleStringProperty("00");
        sec = new SimpleStringProperty("00");
        millis = new SimpleStringProperty("00");
        sspTime = new SimpleStringProperty("00:00:00");
    }

    @Override
    public void run() {
        if (!stop) {
            setTime(time);
            time = time + 10;
        }
    }

    public void startTimer(long time) {
        this.time = time;
        if (!timed) {
            new Timer("Timer").scheduleAtFixedRate(this, 0, 10);
            timed = true;
        }
        stop = false;
    }

    public void stopTimer(long time) {
        this.stop = true;
        this.time = time;
        setTime(time);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        String[] split = sdf.format(new Date(time)).split(":");
        min.set(split[0]);
        sec.set(split[1]);

        if (split[2].length() == 1) {
            split[2] = "0" + split[2];
        }
        millis.set(split[2].substring(0, 2));

        sspTime.set(min.get() + ":" + sec.get() + ":" + millis.get());
        Platform.runLater((() -> clock.setText(sspTime.getValue())));
    }

    public SimpleStringProperty getSspTime() {
        return sspTime;
    }

    public void setText(Text clock) {
        this.clock = clock;
        Platform.runLater((() -> clock.setText(sspTime.getValue())));
    }

    public boolean isStop() {
        return stop;
    }
}
