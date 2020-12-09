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
    private           long    time;
    private transient Timer   timer;
    private transient Text    clock;
    private           boolean stop = true;
    private           boolean timed;

    public StopwatchTimer() {
        min = new SimpleStringProperty("00");
        sec = new SimpleStringProperty("00");
        millis = new SimpleStringProperty("00");
        sspTime = new SimpleStringProperty("00:00:00");
    }

    /**
     * Run the timer thread
     */
    @Override
    public void run() {
        if (!stop) {
            setTime(time);
            time = time + 10;
        }
    }

    /**
     * Start the timer
     *
     * @param time time value where the timer must start
     */
    public void startTimer(long time) {
        this.time = time;
        if (!timed) {
            timer = new Timer();
            timer.scheduleAtFixedRate(this, 0, 10);
            timed = true;
        }
        stop = false;
    }

    /**
     * Stop the timer
     */
    public void stop() {
        this.stop = true;
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        this.cancel();
    }

    /**
     * Stop the timer and assign new time value
     *
     * @param time time value
     */
    public void stopTimer(long time) {
        this.stop = true;
        this.time = time;
        setTime(time);
    }

    /**
     * Getter for time value
     *
     * @return Time
     */
    public long getTime() {
        return time;
    }

    /**
     * set time value
     *
     * @param time time value
     */
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

    /**
     * @param time set time value of the object to the value passed in param
     */
    public void setTimel(long time) {
        this.time = time;
    }

    /**
     * @return string of the timer
     */
    public SimpleStringProperty getSspTime() {
        return sspTime;
    }

    /**
     * @param clock set text of Text object with the time value
     */
    public void setText(Text clock) {
        this.clock = clock;
        Platform.runLater((() -> clock.setText(sspTime.getValue())));
    }

    /**
     * @return true if the clock is stopped, false else.
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @return stringed time value
     */
    @Override
    public String toString() {
        return sspTime.getValue();
    }
}
